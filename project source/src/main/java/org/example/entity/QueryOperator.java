package org.example.entity;

public enum QueryOperator {
    MORE_THAN(" > "), LESS_THAN(" < "),
    MORE_OR_EQUALS(" >= "), LESS_OR_EQUALS(" <= "),
    EQUALS(" = ");

    private final String symbol;

    QueryOperator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
