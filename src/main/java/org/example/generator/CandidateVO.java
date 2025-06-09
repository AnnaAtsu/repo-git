package org.example.generator;

public class CandidateVO {

    private final String firstName, middleName, lastName, vacancy, email, contractNumber, keywords, candidateNote;


    public CandidateVO(String firstName, String middleName, String lastName, String vacancy, String email, String contractNumber, String keywords, String candidateNote) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.vacancy = vacancy;
        this.email = email;
        this.contractNumber =contractNumber;
        this.keywords = keywords;
        this.candidateNote = candidateNote;

    }

    public String getFirstName() {
        return  firstName;
    }


    public String middleName() {
        return  middleName;
    }

    public String lastName() {
        return  lastName;
    }
    public String vacancy() {
        return  vacancy;
    }
    public String email() {
        return  email;
    }
    public String contractNumber() {
        return  contractNumber;
    }
    public String keywords() {
        return  keywords;
    }
    public String candidateNote() {
        return  candidateNote;
    }

}
