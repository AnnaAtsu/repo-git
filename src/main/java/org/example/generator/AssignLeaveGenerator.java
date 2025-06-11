package org.example.generator;

import com.github.javafaker.Faker;
import org.example.AssignLeave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AssignLeaveGenerator {
    private static final Faker faker = new Faker();


    public static AssingLeaveVO generateAssign() {
        String employeeName = "NAME" + faker.number().numberBetween(1, 500);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fromDate = currentDate.format(formatter);

        LocalDate futureDate = currentDate.plusDays(2);

        // Форматируем дату в строку в формате "год-месяц-день"
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String toDate = futureDate.format(formatter1);
        String comments = faker.color().name();

        return new AssingLeaveVO(employeeName, fromDate, toDate, comments);

    }
}
