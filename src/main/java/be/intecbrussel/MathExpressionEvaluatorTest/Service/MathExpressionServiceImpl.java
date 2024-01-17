package be.intecbrussel.MathExpressionEvaluatorTest.Service;

import be.intecbrussel.MathExpressionEvaluatorTest.exception.InvalideExpressionException;
import be.intecbrussel.MathExpressionEvaluatorTest.model.DoubleWithIndex;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class MathExpressionServiceImpl implements MathExpressionService{
//-5*3-7*((2+7/3)+4)-3

    private BasicMathService basicMathService;

    {
        basicMathService = new BasicMathServiceImpl();
    }
    @Override
    //public String evaluate(final String expression) {
    public String evaluate(String expression) {
        if (isInvalidExpression(expression)) {
            throw new InvalideExpressionException("Invalid Expression");
        }

        expression = evaluateBrackets(expression);
        expression = evaluateMultiplyAndDivideAndModulo(expression);
        expression = evaluateAddAndSubtract(expression);

        return expression;
    }

    private boolean isInvalidExpression(String expression) {
        long amountOfOpenBrackets = Stream.of(expression.split(""))
                .filter(character -> character.equals("("))
                .count();
        long amountOfCloseBrackets = Stream.of(expression.split(""))
                .filter(character -> character.equals(")"))
                .count();
        if (amountOfCloseBrackets != amountOfOpenBrackets){
            return true;
        }
        return false;
    }

    private String evaluateAddAndSubtract(String expression) {
        int index;

        while ((index = getLowestMDMIndex(expression)) >= 0){
            char operator = expression.charAt(index);

            DoubleWithIndex firstNumberAndIndex = findFirstNumberAndIndex(expression, index);
            DoubleWithIndex secondNumberAndIndex = findSecondNumberAndIndex(expression, index);
            double result = 0;

            switch (operator){
                case '*':
                    result = basicMathService.add(firstNumberAndIndex.value, secondNumberAndIndex.index);
                    break;
                case '/':
                    result = basicMathService.subtract(firstNumberAndIndex.value, secondNumberAndIndex.index);
                    break;
            }

            expression = new StringBuilder(expression)
                    .replace(firstNumberAndIndex.index, secondNumberAndIndex.index + 1, String.valueOf(result))
                    .toString();
        }
        return expression;
    }

    private String evaluateBrackets(String expression) {
        int indexOpenBracket;
        //int indexCloseBracket = expression.lastIndexOf(")"); // /!\ als verschillende hakje
        int indexCloseBracket;

        while ((indexOpenBracket = expression.indexOf("(")) >= 0) { // ==> -1 (of min getallen) if het niet gevonden is.

            indexCloseBracket = findCloseBracketIndex(expression, indexOpenBracket);

            if (indexOpenBracket >= 0) {
                //handleBrackets
                String evaluation = evaluate(expression.substring(indexOpenBracket + 1, indexCloseBracket));
                //expression = expression.substring(0, indexOpenBracket) + evaluation + expression.substring(indexCloseBracket+1); na aan chatGPT vragen
                expression = new StringBuilder(expression)
                        .replace(indexOpenBracket, indexCloseBracket + 1, evaluation)
                        .toString();
            }
        }
        return expression;
    }

    private int findCloseBracketIndex(String expression, int indexOpenBracket) {
        int counter = 0;

        for (int i = indexOpenBracket + 1; i < expression.length(); i++ ){
            if (expression.charAt(i) == '(') counter++;
            else if (expression.charAt(i) == ')') counter--;

            if (counter == -1) return i;
        }
        return -1;
    }

    private String evaluateMultiplyAndDivideAndModulo(String expression){
        int index;

        while ((index = getLowestMDMIndex(expression)) >= 0){
            char operator = expression.charAt(index);
            //double firstNumber = findFirstNumber(expression, index);
            //double secondNumber = findSecondNumber(expression, index);

            DoubleWithIndex firstNumberAndIndex = findFirstNumberAndIndex(expression, index);
            DoubleWithIndex secondNumberAndIndex = findSecondNumberAndIndex(expression, index);
            double result = 0;

            switch (operator){
                case '*':
                    result = basicMathService.multiply(firstNumberAndIndex.value,secondNumberAndIndex.value);
                    break;
                case '/':
                    result = basicMathService.divide2(firstNumberAndIndex.value,secondNumberAndIndex.value);
                    break;
                case '%':
                    result = basicMathService.modulo(firstNumberAndIndex.value,secondNumberAndIndex.value);
                    break;
            }

            expression = new StringBuilder(expression)
                    .replace(firstNumberAndIndex.index, secondNumberAndIndex.index + 1, String.valueOf(result))
                    .toString();
        }
        return expression;
    }

    private int findFirstIndexOfAny(String expression, int index){
        String operators = "+-*/%";
        int numberIndex = -1;

        for (int i = index; i >= 0; i--){
            char currentChar = expression.charAt(i);
            if (operators.contains("" + currentChar)){
                numberIndex = i-1; // i is operator, i+1 is the first digit
                break;
            }
        }
        return numberIndex;
    }

    private DoubleWithIndex findSecondNumberAndIndex(String expression, int index){

        int firstNumberIndex = findFirstIndexOfAny(expression, index);
        if (firstNumberIndex < 0) {
            return new DoubleWithIndex(0, -1);
        }

        return new DoubleWithIndex(Double.parseDouble(expression.substring(firstNumberIndex, index)), firstNumberIndex);
    }

    private DoubleWithIndex findFirstNumberAndIndex (String expression, int index){
        /**
         *String[] numbers = expression.split("[-+* division %]);
                * List < String > list = Arrays.stream(numbers)
                *                 .filter(number -> expression.indexOf(number) < index)
                *                 .toList();
         *double number = Double.parseDouble(list.get(list.size() - 1));
         *int numberIndex = expression.lastIndexOf(String.valueOf(number), index);
         *
         *return new DoubleWithIndex(number, numberIndex);
         */

        int firstNumberIndex = getLastIndexOfAny(expression,index);
        if (firstNumberIndex < 0)
            return new DoubleWithIndex(0, -1);

        return new DoubleWithIndex(Double.parseDouble(expression.substring(firstNumberIndex, index)), firstNumberIndex);
    }

    private int getLastIndexOfAny(String expression, int endIndex){
        String operators = "+-*/%";
        int numberIndex = -1;
        //double value = 0;

        for (int i = endIndex; i >= 0; i--){
            char currentChar = expression.charAt(i);
            if (operators.contains("" + currentChar)){
                numberIndex = i+1; // i is operator, i+1 is the first digit
                break;
            }
        }
        /*
        if (numberIndex < 0) return -1;
        value = Double.parseDouble(expression.substring(numberIndex, endIndex));
        return  -1;
         */

        return numberIndex;
    }

    private int getLowestMDMIndex(String expression){
        int[] indices = new int[3];
        indices[0] = expression.indexOf("*");
        indices[1] = expression.indexOf("/");
        indices[2] = expression.indexOf("%");

        OptionalInt first = Arrays.stream(indices)
                .filter(index -> index >= 0)
                .sorted()
                .findFirst();

        int index = first.orElse(-1);

        return index;
    }
}

/*
    private String evaluateMultiplyAndDivideAndModulo(String expression){

        int indexMul;
        int indexDiv;
        int indexMod;
        int index;


        while (index = Integer.min(
                Integer.min(expression.indexOf("*"),
                        expression.indexOf("/"),
                        expression.indexOf("%")     ==> tentative
        ) > 0;*/
