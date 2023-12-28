package org.example.dto;

public class EmployeeDto extends PersonDto {

    private long numOfOrders;

    public EmployeeDto(long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public EmployeeDto(long id, String firstName, String lastName, long numOfOrders) {
        this(id, firstName, lastName);
        this.numOfOrders = numOfOrders;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                super.toString() +
                ", numOfOrders= " + numOfOrders +
                '}';
    }
}
