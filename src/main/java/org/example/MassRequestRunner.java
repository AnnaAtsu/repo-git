package org.example;

import io.restassured.response.Response;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class MassRequestRunner {


    // 🔧 Настройки — замени на свои
    private static final String BASE_URI = "https://zerno.mcx.gov.ru"; // базовый URL
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String DATA_PATH = "/api/zsn/send-to-efis";

    private static final String LOGIN_BODY = """
        {
          "login": "Starkova",
          "password": "fsHxKzFpKr"
        }
        """;
    private static final String SUCCESS_REPORT_FILE = "success_report.csv";
    private static final String ERROR_REPORT_FILE = "error_report.csv";

    private static PrintWriter successWriter;
    private static PrintWriter errorWriter;

    // ⚙️ Конфигурация
    private static final int THREAD_POOL_SIZE = 10;
    private static final int MAX_RETRIES = 3;
    private static final String CSV_FILE_PATH = "pr71_part_3.csv";

    public static void main(String[] args) {
        try {
            // 📝 Инициализируем файлы отчётов
            successWriter = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(SUCCESS_REPORT_FILE, false), StandardCharsets.UTF_8));
            errorWriter = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(ERROR_REPORT_FILE, false), StandardCharsets.UTF_8));

            successWriter.println("product_monitor_id,timestamp");
            errorWriter.println("product_monitor_id,error_message,timestamp");

            System.out.println("🚀 Начинаем выполнение массовых запросов...");

            String token = login();
            System.out.println("✅ Токен получен: " + (token.length() > 15 ? token.substring(0, 15) + "..." : token));

            List<Integer> productMonitorIds = readIdsFromCsv(CSV_FILE_PATH);
            System.out.println("📄 Прочитано " + productMonitorIds.size() + " ID из файла.");

            ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

            List<CompletableFuture<Void>> futures = productMonitorIds.stream()
                    .map(id -> CompletableFuture.runAsync(() -> {
                        boolean success = false;
                        Exception lastError = null;
                        for (int attempt = 1; attempt <= MAX_RETRIES && !success; attempt++) {
                            try {
                                sendDataWithRetry(token, id, attempt);
                                success = true;
                                System.out.println("✅ Успешно отправлено для ID: " + id + " (попытка " + attempt + ")");
                                synchronized (successWriter) {
                                    successWriter.printf("%d,%s%n", id, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                                    successWriter.flush();
                                }
                            } catch (Exception e) {
                                lastError = e;
                                System.err.println("⚠️ Попытка " + attempt + " не удалась для ID " + id + ": " + e.getMessage());
                                if (attempt < MAX_RETRIES) {
                                    long delay = (long) Math.pow(2, attempt) * 1000;
                                    try {
                                        Thread.sleep(delay);
                                    } catch (InterruptedException ie) {
                                        Thread.currentThread().interrupt();
                                        return;
                                    }
                                }
                            }
                        }
                        if (!success && lastError != null) {
                            System.err.println("❌ Все попытки исчерпаны для ID " + id);
                            synchronized (errorWriter) {
                                String errorMsg = lastError.getMessage().replace("\"", "\"\"");
                                errorWriter.printf("%d,\"%s\",%s%n", id, errorMsg, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                                errorWriter.flush();
                            }
                        }
                    }, executor).exceptionally(throwable -> {
                        System.err.println("💥 Необработанная ошибка для ID: " + throwable.getMessage());
                        return null;
                    }))
                    .collect(Collectors.toList());

            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            executor.shutdown();

            // 📊 Статистика
            long successCount = Files.lines(Paths.get(SUCCESS_REPORT_FILE)).count() - 1;
            long errorCount = Files.lines(Paths.get(ERROR_REPORT_FILE)).count() - 1;

            System.out.println("\n📊 ОТЧЁТ:");
            System.out.println("✅ Успешно: " + successCount);
            System.out.println("❌ Ошибки: " + errorCount);
            System.out.println("📄 Успешные ID: " + SUCCESS_REPORT_FILE);
            System.out.println("📄 Ошибки: " + ERROR_REPORT_FILE);

            successWriter.close();
            errorWriter.close();

            System.out.println("🏁 Все запросы завершены.");

        } catch (Exception e) {
            System.err.println("💥 Критическая ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 🟢 1. Логин через RestAssured
    private static String login() {
        try {
            Response response = given()
                    .baseUri(BASE_URI)
                    .contentType("application/json")
                    .body(LOGIN_BODY)
                    .when()
                    .post(LOGIN_PATH)
                    .then()
                    .statusCode(200)
                    .extract().response();

            // Извлекаем accessToken из JSON
            String accessToken = response.jsonPath().getString("accessToken");
            if (accessToken == null || accessToken.trim().isEmpty()) {
                throw new RuntimeException("accessToken не найден в ответе: " + response.asString());
            }
            return accessToken;

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при логине", e);
        }
    }


    // 🟢 2. Отправка данных с retry
    private static void sendDataWithRetry(String token, int productMonitorId, int attempt) throws Exception {
        String body = String.format("""
            {
              "product_monitor_id": %d
            }
            """, productMonitorId);

        Response response = given()
                .baseUri(BASE_URI)
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(body)
                .when()
                .post(DATA_PATH);

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            // Успех — ничего не делаем
            return;
        } else {
            throw new RuntimeException("HTTP " + response.statusCode() + " - " + response.asString());
        }
    }

    // 📁 3. Чтение ID из CSV
    private static List<Integer> readIdsFromCsv(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.lines(path)
                .skip(1) // пропускаем заголовок
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .map(line -> {
                    try {
                        return Integer.parseInt(line);
                    } catch (NumberFormatException e) {
                        System.err.println("⚠️ Неверный формат ID в строке: '" + line + "' — пропускаем.");
                        return null;
                    }
                })
                .filter(id -> id != null)
                .collect(Collectors.toList());
    }
}
