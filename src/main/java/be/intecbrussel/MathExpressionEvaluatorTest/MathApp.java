package be.intecbrussel.MathExpressionEvaluatorTest;

import be.intecbrussel.MathExpressionEvaluatorTest.Service.BasicMathServiceImpl;

public class MathApp {

    public static void main(String[] args) {

        BasicMathServiceImpl mathService = new BasicMathServiceImpl();
        System.out.println(mathService.divide2(10,3));

    }
}
