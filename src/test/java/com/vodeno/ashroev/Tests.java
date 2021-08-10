package com.vodeno.ashroev;

import com.vodeno.ashroev.app.SimpleCalc;
import com.vodeno.ashroev.domain.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
/*
        This is not all but i think this is enough to get an idea
        what i'm realising cause 1h is not enough to make it pretty and nice,
        and I don't like to make plain dummy code
 */
public class Tests {
    SimpleCalc calc = new SimpleCalc();

    @ParameterizedTest
    @MethodSource("positiveArgs")
    public void positiveOperationTests(double val1, double val2, OperationType type, double expected){
        Double result = calc.doWork(val1, val2, type);
        Assertions.assertEquals(result, expected);
    }

    private static Stream<Arguments> positiveArgs() {
        return Stream.of(
                Arguments.of(10, 2, OperationType.DIVIDE, 5),
                Arguments.of(-10, 10, OperationType.DIVIDE, -1.0),
                Arguments.of(10, 8, OperationType.DIVIDE, 1.25),
                Arguments.of(10, 2, OperationType.MULTIPLICATION, 20),
                Arguments.of(10, 2, OperationType.MINUS, 8),
                Arguments.of(-10, 2, OperationType.MINUS, -12.0),
                Arguments.of(10, 2, OperationType.SUMM, 12),
                Arguments.of(-10, 2, OperationType.SUMM, -8),
                Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE, OperationType.SUMM, 4.294967294E9),
                Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE, OperationType.DIVIDE, 1),
                Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE, OperationType.MULTIPLICATION, 4.6116860141324206E18)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeArgs")
    public void negativeOperationTests(Object val1, Object val2, OperationType type){
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.doWork(val1, val2, type));
    }

    private static Stream<Arguments> negativeArgs() {
        return Stream.of(
                Arguments.of(10, null, OperationType.DIVIDE),
                Arguments.of(null, null, OperationType.DIVIDE),
                Arguments.of("123 ", "-AWEQ", OperationType.DIVIDE)
        );
    }

    @ParameterizedTest
    @MethodSource("parserValues")
    public void operationTypeParserTest(String symbol, OperationType type){
        Assertions.assertEquals(OperationType.parseSign(symbol), type);
    }

    private static Stream<Arguments> parserValues() {
        return Stream.of(
                Arguments.of("-" , OperationType.MINUS),
                Arguments.of("+", OperationType.SUMM),
                Arguments.of("*", OperationType.MULTIPLICATION),
                Arguments.of("/", OperationType.DIVIDE)
        );
    }

    @ParameterizedTest
    @MethodSource("parserNegativeValues")
    public void operationTypeParserTest(String symbol){
        Assertions.assertThrows(IllegalArgumentException.class, () -> OperationType.parseSign(symbol));
    }

    private static Stream<Arguments> parserNegativeValues() {
        return Stream.of(
                Arguments.of("1"),
                Arguments.of("@"),
                Arguments.of(" "),
                Arguments.of("* ")
        );
    }

    @ParameterizedTest
    @MethodSource("parserValuesOf")
    public void operationValueOfTest(String symbol, OperationType type){
        Assertions.assertEquals(OperationType.valueOf(symbol), type);
    }

    private static Stream<Arguments> parserValuesOf() {
        return Stream.of(
                Arguments.of("MINUS" , OperationType.MINUS),
                Arguments.of("SUMM", OperationType.SUMM),
                Arguments.of("MULTIPLICATION", OperationType.MULTIPLICATION),
                Arguments.of("DIVIDE", OperationType.DIVIDE)
        );
    }

    @ParameterizedTest()
    @MethodSource("parserNegativeValues")
    public void operationNegativeValueOfTest(String symbol){
        Assertions.assertThrows(IllegalArgumentException.class, () -> OperationType.valueOf(symbol));
    }
}

