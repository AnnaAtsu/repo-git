package org.example.generator;

import com.github.javafaker.Faker;
import org.example.AssignLeave;

import java.time.LocalDate;

public class AssignLeaveGenerator {
    private static final Faker faker = new Faker();


    public static AssingLeaveVO generateAssign() {
        String employeeName = "NAME" + faker.number().numberBetween(1, 500);
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.now();
        String comments = faker.color().name();

        return new AssingLeaveVO(employeeName, fromDate, toDate, comments);

    }
}
