package be.intecbrussel.MathExpressionEvaluatorTest.exception;

public class InvalideExpressionException extends RuntimeException{


    public InvalideExpressionException(String message) {
        super(message);
    }

    public InvalideExpressionException(String message, Throwable cause) {
        super(message, cause);
    }
}
