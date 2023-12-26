package org.example.dto;

public class CreatePersonDto {

    private String firstName;
    private String lastName;
    private String ssn;

    public CreatePersonDto() {
    }

    public CreatePersonDto(String firstName, String lastName, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSsn() {
        return ssn;
    }

}
