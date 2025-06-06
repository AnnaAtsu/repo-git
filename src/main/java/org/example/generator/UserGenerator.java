package org.example.generator;

import com.github.javafaker.Faker;
// Для шаблона Value Object
public class UserGenerator {
    private static final Faker faker = new Faker();

    public static UserVO generate() {
        String username = "user" + faker.number().numberBetween(1, 500);
        String password = "Pass@" + faker.number().randomNumber(5, true);
        String role = "Admin";
        String status = "Disabled";
        String employeeName = "Jobin";

        return new UserVO(username, password, role, status, employeeName);
    }



  //  String role = faker.options().option("Admin", "ESS");
   // String status = faker.options().option("Enabled", "Disabled");
}
