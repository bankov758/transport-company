package org.example.dto;

public class CreatePayloadQualificationDto {
    private String qualification;

    public CreatePayloadQualificationDto(String qualification) {
        this.qualification = qualification;
    }

    public String getQualification() {
        return qualification;
    }
}
