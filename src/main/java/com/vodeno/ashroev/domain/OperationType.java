package com.vodeno.ashroev.domain;

import java.util.stream.Stream;

public enum OperationType {
    SUMM("+") {
        public Double apply(Double x, Double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public Double apply(Double x, Double y) {
            return x - y;
        }
    },
    MULTIPLICATION("*") {
        public Double apply(Double x, Double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        public Double apply(Double x, Double y) {
            return x / y;
        }
    };

    public String getSymbol() {
        return symbol;
    }

    private final String symbol;

    OperationType(String symbol) {
        this.symbol = symbol;
    }

    /*
        Just a simple parser from string symbol
     */
    public static OperationType parseSign(String symbol) {
        return Stream.of(OperationType.values())
                .filter(v -> v.getSymbol().equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Incorrect Sign applyed"));
    }

    @Override
    public String toString() {
        return symbol;
    }

    public abstract Double apply(Double x, Double y);
}
