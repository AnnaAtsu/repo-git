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


    public String getmiddleName() {
        return  middleName;
    }

    public String getlastName() {
        return  lastName;
    }
    public String getvacancy() {
        return  vacancy;
    }
    public String getemail() {
        return  email;
    }
    public String getcontractNumber() {
        return  contractNumber;
    }
    public String getkeywords() {
        return  keywords;
    }
    public String getcandidateNote() {
        return  candidateNote;
    }

}
