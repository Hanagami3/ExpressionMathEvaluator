package be.intecbrussel.MathExpressionEvaluatorTest.service;

import be.intecbrussel.MathExpressionEvaluatorTest.Service.MathExpressionService;
import be.intecbrussel.MathExpressionEvaluatorTest.Service.MathExpressionServiceImpl;
import be.intecbrussel.MathExpressionEvaluatorTest.exception.InvalideExpressionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MathExpressionServiceTest {

    private MathExpressionService mathExpressionService;

    {
        mathExpressionService = new MathExpressionServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("expressionFactory")
    public void testExpression(String expression, String expectedExpression) {
        String result = mathExpressionService.evaluate(expression);
        Assertions.assertEquals(expectedExpression, result);
    }
    public static Stream<Arguments> expressionFactory(){
        return Stream.of(
                Arguments.of("4 + 2", "6"),
                Arguments.of("-4 + 2", "-2"),
                Arguments.of("2--4", "6"),
                Arguments.of("-4-2", "-6"),
                Arguments.of("1 + 0.1", "1.1"),
                Arguments.of("0.99999 + 0.000001", "0.999991"),
                Arguments.of("10/90 + 80/90", "1"),
                Arguments.of("10 * (1 + (5 - 1) + 5)", "100"),
                Arguments.of("-5", "-5"),
                Arguments.of("0", "0"),
                Arguments.of("5 - 0.5", "4.5"),
                Arguments.of("0.5 + 1", "1.5"),
                Arguments.of("6 / -2", "-3"),
                Arguments.of("50 % 3", "2"),
                Arguments.of("1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 - 1 - 1 - 1 - 1 - 1 - 1 - 1 * 2 * 2 * 2 * 4 / 4", "-16"),
                Arguments.of("100 / (1 / 10000000000000000000)", "1e+21"),
                Arguments.of("6---8", "-2"),
                Arguments.of("999999 * 999999 * 999999", "9.99997e+17"),
                Arguments.of("10 / (-2 - 2)", "-2.5"),
                Arguments.of("99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999 * 2", "2e+119"),
                Arguments.of("2 / 99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999", "2e-119")
        );
    }

    @ParameterizedTest
    @MethodSource("expressionExceptionFactory")
    public void testExpression_expected(String expression, Class<Exception> expectedExpression){
        Assertions.assertThrows(expectedExpression,
                () -> mathExpressionService.evaluate(expression));
    }

    public static Stream<Arguments> expressionExceptionFactory(){
        return Stream.of(
                Arguments.of("3/0", InvalideExpressionException.class),
                Arguments.of("potato salad", InvalideExpressionException.class),
                Arguments.of("x + y", InvalideExpressionException.class),
                Arguments.of("sqrt(9)", InvalideExpressionException.class),
                Arguments.of("5^2", InvalideExpressionException.class),
                Arguments.of("10.3.5 - 5", InvalideExpressionException.class)
        );
    }
}
