package be.intecbrussel.MathExpressionEvaluatorTest.Service;

public interface BasicMathService {
    double add(double firstNumber, double secondNumber);
    double subtract(double firstNumber, double secondNumber);
    double multiply(double firstNumber, double secondNumber);
    double divide(double dividend, double divider);
    double divide2(double dividend, double divider);
    double modulo(double firstNumber, double secondNumber);
}

