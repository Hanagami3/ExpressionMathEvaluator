package be.intecbrussel.MathExpressionEvaluatorTest.Utils;

public class DecimalGetter {

    public static java.math.BigDecimal getBigDecimal(double Number){
        String firstNumberAsString = String.valueOf(Number);
        java.math.BigDecimal firstDecimal = new java.math.BigDecimal(firstNumberAsString);
        return firstDecimal;
    }
}
