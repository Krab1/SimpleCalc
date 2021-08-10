package com.vodeno.ashroev.app;

import com.vodeno.ashroev.domain.OperationType;

//Just an example of calc class that uses Enum for implement logic
public class SimpleCalc {

    public Double doWork(Double value1, Double value2, OperationType type){
        return type.apply(value1, value2);
    }

    public Double doWork(Object value1, Object value2, OperationType type){
        if(value1 instanceof Number && value2 instanceof Number){
           return doWork((Double)value1, (Double)value2, type);
        }
        throw new IllegalArgumentException("Incorrect type of value(s)");
    }
}
