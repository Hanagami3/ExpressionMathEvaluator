package be.intecbrussel.MathExpressionEvaluatorTest.Service;

public class BasicMathServiceImpl implements BasicMathService{
    @Override
    public double add(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }

    @Override
    public double subtract(double firstNumber, double secondNumber) {
        return 0;
    }

    @Override
    public double multiply(double firstNumber, double secondNumber) {
        return 0;
    }

    @Override
    public double divide(double dividend, double divider) {
        return 0;
    }
}