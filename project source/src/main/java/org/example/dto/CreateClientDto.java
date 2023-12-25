package org.example.dto;

public class CreateClientDto extends CreatePersonDto{
    public CreateClientDto(String firstName, String lastName, String ssn) {
        super(firstName, lastName, ssn);
    }
}
