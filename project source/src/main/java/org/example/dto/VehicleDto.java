package org.example.dto;

public class VehicleDto {

    private int id;

    private String type;

    private String registrationNumber;

    private String companyName;

    public VehicleDto(int id, String type, String registrationNumber, String companyName) {
        this.id = id;
        this.type = type;
        this.registrationNumber = registrationNumber;
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "VehicleDto{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }

}
