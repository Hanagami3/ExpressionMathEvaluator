package be.intecbrussel.MathExpressionEvaluatorTest.Service;

import be.intecbrussel.MathExpressionEvaluatorTest.Utils.DecimalGetter;

import java.math.BigDecimal;

public class BasicMathServiceImpl implements BasicMathService{
    @Override
    public double add(double firstNumber, double secondNumber) {

        BigDecimal firstDecimal = DecimalGetter.getBigDecimal(firstNumber);
        BigDecimal secondDecimal =  DecimalGetter.getBigDecimal(secondNumber);

        BigDecimal result = firstDecimal.add(secondDecimal);

        return result.doubleValue();
    }

    @Override
    public double subtract(double firstNumber, double secondNumber) {

        BigDecimal firstDecimal = DecimalGetter.getBigDecimal(firstNumber);
        BigDecimal secondDecimal =  DecimalGetter.getBigDecimal(secondNumber);

        BigDecimal result = firstDecimal.subtract(secondDecimal);

        return result.doubleValue();
    }

    @Override
    public double multiply(double firstNumber, double secondNumber) {

        BigDecimal firstDecimal = DecimalGetter.getBigDecimal(firstNumber);
        BigDecimal secondDecimal =  DecimalGetter.getBigDecimal(secondNumber);

        BigDecimal result = firstDecimal.multiply(secondDecimal);

        return result.doubleValue();
    }

    @Override
    public double divide(double dividend, double divider) {
        BigDecimal firstDecimal = DecimalGetter.getBigDecimal(dividend);
        BigDecimal secondDecimal =  DecimalGetter.getBigDecimal(divider);

        BigDecimal result = new BigDecimal(0);

        try {
            result = firstDecimal.divide(secondDecimal);
        } catch (ArithmeticException ae){
            if (divider == 0) System.out.println("Een getal kan niet met 0 delen");
            else{
                System.out.println("Het resultaat mag niet optimaal zijn! (Veel cijfer achter de komma");
                return dividend/divider;
            }
        }

        return result.doubleValue();
    }

    @Override
    public double modulo(double firstNumber, double secondNumber) {
        BigDecimal firstDecimal = DecimalGetter.getBigDecimal(firstNumber);
        BigDecimal secondDecimal =  DecimalGetter.getBigDecimal(secondNumber);

        BigDecimal result = new BigDecimal(0);

        try {
            result = firstDecimal.remainder(secondDecimal);
        }catch (ArithmeticException ea){
            if (secondNumber == 0) System.out.println("Een getal kan niet met 0 delen");
        }
        return result.doubleValue();
    }
}

    /*
    public double add(double firstNumber, double secondNumber) {

        String firstNumberAsString = String.valueOf(firstNumber);
        String secondNumberAsString = String.valueOf(secondNumber);

        BigDecimal firstDecimal = new BigDecimal(firstNumberAsString); //parameter est un string car si double il y a une erreur.
        BigDecimal secondDecimal = new BigDecimal(secondNumberAsString);

        BigDecimal result = firstDecimal.add(secondDecimal);


        pour trouver l'erreur
        System.out.println(firstDecimal);
        System.out.println(secondDecimal);
        System.out.println(result);

        return result.doubleValue();

        Jon's idea mais ne fonctionne pas pour pi ou 10/3
        int amountOfNumbersBeforeDecimal1 = String.valueOf((int) firstNumber).length();
        int amountOfNumbersBeforeDecimal2 = String.valueOf((int) firstNumber).length();

        int amountOfDecimals1 = String.valueOf(firstNumber).length() - amountOfNumbersBeforeDecimal1 - 1;
        int amountOfDecimals2 = String.valueOf(secondNumber).length() - amountOfNumbersBeforeDecimal2 - 1;

        int mostDecimals = Integer.max(amountOfDecimals1, amountOfDecimals2);

        int theNumber = 10;
        for (int i = 0; i < mostDecimals; i++) {
            theNumber *= 10;
        }

        double number1 = firstNumber * theNumber;
        double number2 = secondNumber * theNumber;

        double theResult = number1 + number2;

        double realResult = theResult / theNumber;

        return realResult;

    }
     */