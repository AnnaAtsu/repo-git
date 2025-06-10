package org.example.generator;

import com.github.javafaker.Faker;

import java.time.LocalDate;

public class CandidateGenerator {


    private static final Faker faker = new Faker();

    public static CandidateVO generateCandidate() {
        String firstName = "Natasha" + faker.number().numberBetween(1, 500);
        String middleName = "Middle " + faker.number().randomNumber(2, true);
        LocalDate today = LocalDate.now();
        String lastName = "Last " + today;
        String vacancy = "account assistant";
        String email = "Pass" + faker.number().randomNumber(5, true) + "@" + faker.expression("yander") + ".ru";
        String contractNumber = String.valueOf(faker.number().randomNumber(3, true));
        String keywords = "Keywords " + faker.address();
        String candidateNote = "This note is" + faker.expression(" about the job.");

        return new CandidateVO(firstName, middleName, lastName, vacancy, email, contractNumber, keywords, candidateNote);
    }
}
