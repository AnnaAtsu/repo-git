package org.example.generator;

import com.github.javafaker.Faker;

public class JobTitleGenerator {


    private static final Faker faker = new Faker();

    public static JobVO generateTitle() {
        String jobTitle = "Job" + faker.number().numberBetween(1, 500);
        String jobDescription = "This job is " + faker.number().randomNumber(8, true);
        String jobNote = "This note is" + faker.expression(" about the job.");

        return new JobVO(jobTitle, jobDescription, jobNote);
    }
}
