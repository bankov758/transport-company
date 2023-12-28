package org.example.dto;

public class EmployeeDto extends PersonDto {

    private long numOfOrders;
    private double incomeFormOrders;

    public EmployeeDto(long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public EmployeeDto(long id, String firstName, String lastName, long numOfOrders) {
        this(id, firstName, lastName);
        this.numOfOrders = numOfOrders;
    }

    public EmployeeDto(long id, String firstName, String lastName, long numOfOrders, double incomeFormOrders) {
        this(id, firstName, lastName, numOfOrders);
        this.incomeFormOrders = incomeFormOrders;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                super.toString() +
                ", numOfOrders= " + numOfOrders +
                ", incomeFormOrders= " + incomeFormOrders +
                '}';
    }
}
