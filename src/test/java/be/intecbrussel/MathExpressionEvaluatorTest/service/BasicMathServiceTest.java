package be.intecbrussel.MathExpressionEvaluatorTest.service;

    import be.intecbrussel.MathExpressionEvaluatorTest.Service.BasicMathService;
    import be.intecbrussel.MathExpressionEvaluatorTest.Service.BasicMathServiceImpl;
    import org.junit.jupiter.api.*;
    import org.junit.jupiter.params.ParameterizedTest;
    import org.junit.jupiter.params.provider.Arguments;
    import org.junit.jupiter.params.provider.MethodSource;
    import org.junit.jupiter.params.provider.ValueSource;

    import java.util.stream.Stream;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)  ==> pas obligé mais alors BeforeAll 'static'
    public class BasicMathServiceTest {
        private final BasicMathService basicMathService;

        {
            this.basicMathService = new BasicMathServiceImpl();
        }

        /*
        @BeforeAll
        public void beforeAll() {
            // TODO instantiate the service
            public static void setup();
        }
        */

        @Test
        public void testBasicAdditionOfTwoIntegers() {
            int firstNumber = 7;
            int secondNumber = 6;

            int expectedValue = 13;

            double result = basicMathService.add(firstNumber, secondNumber);

            Assertions.assertEquals(expectedValue, result);
        }

        @Test
        public void testBasicAdditionOfTwoNegativeIntegers(){
            int firstNumber = -4;
            int secondNumber = -8;
            int expectedResult = -12;

            double result = basicMathService.add(firstNumber, secondNumber);

            Assertions.assertEquals(expectedResult, result);
        }

        @ParameterizedTest
        //@ValueSource(longs = {1,1,2,2,2,4}) // exemple pour savoir si pair ou non
        @MethodSource("basicAdditionFactory") // où dois je trouver les paramètres avec le nom de la méthodes qui les contient
        public void testBasicAdditions(double number1, double number2, double expectedValue){
            double result = basicMathService.add(number1, number2);
            Assertions.assertEquals(expectedValue, result);
        }
        public static Stream<Arguments> basicAdditionFactory(){ //argument = een verzameling of data to test + best pratique stream van arguments die objesct zijn dus on peut utiliser n'importe quel valeur
            return Stream.of(
                    Arguments.of(5, 3, 8),
                    Arguments.of(15, 3, 18),
                    Arguments.of(-5, 3, -2),
                    Arguments.of(0, 0, 0),
                    Arguments.of(-7, -3, -10),
                    Arguments.of(5, -3, 2),
                    Arguments.of(5.5, 4.5, 10),
                    Arguments.of(2000000000, 2000000000, 4000000000L),
                    Arguments.of(-0.00001, 0.00002, 0.00001),
                    Arguments.of(0.99999, 0.000001, 0.999991)
            );
        }
        @ParameterizedTest
        @MethodSource("basicSubtractFactory")
        public void testBasicSubtract(double number1, double number2, double expectedValue){
            double result = basicMathService.subtract(number1, number2);
            Assertions.assertEquals(expectedValue, result);
        }
        public static Stream<Arguments> basicSubtractFactory(){
            return Stream.of(
                    Arguments.of(5, 3, 2),
                    Arguments.of(-7, -3, -4),
                    Arguments.of(5000000000L, 2000000000, 3000000000L),
                    Arguments.of(0, 0, 0),
                    Arguments.of(4.5, 2.5, 2),
                    Arguments.of(-0.00001, 0.00002, -0.00003),
                    Arguments.of(0.999991, 0.000001, 0.99999)
            );
        }

        @ParameterizedTest
        @MethodSource("basicMultiplyFactory")
        public void testBasicMultiply(double number1, double number2, double expectedValue){
            double result = basicMathService.multiply(number1, number2);
            Assertions.assertEquals(expectedValue, result);
        }
        public static Stream<Arguments> basicMultiplyFactory() {
            return Stream.of(
                    Arguments.of(5, 3, 15),
                    Arguments.of(-7, -3, 21),
                    Arguments.of(5000000000L, 2000000000, 10000000000000000000D),
                    Arguments.of(0, 0, 0),
                    Arguments.of(5, 0, 0),
                    Arguments.of(2.5, 4.5, 11.25),
                    Arguments.of(-0.00001, 0.00002, -0.0000000002),
                    Arguments.of(0.999991, 0.000006, 0.000005999946)
            );
        }

        @ParameterizedTest
        @MethodSource("basicDivideFactory")
        public void testDivideMultiply(double number1, double number2, double expectedValue){
            double result = basicMathService.divide(number1, number2);
            Assertions.assertEquals(expectedValue, result);
        }
        public static Stream<Arguments> basicDivideFactory() {
            return Stream.of(
                    Arguments.of(15, 3, 5),
                    Arguments.of(100, 3, 33.333333333333336), //beter oplossing vinden
                    Arguments.of(-7,3, -2.3333333333333335), //beter oplossing vinden
                    Arguments.of(5000000000L, 2000000000, 2.5),
                    Arguments.of(0, 0, 0),
                    Arguments.of(-0.00001, 0.00002, -0.5),
                    Arguments.of(0.999991, 0.000001, 999991)
            );
        }

        @ParameterizedTest
        @MethodSource("basicModuloFactory")
        public void testModuloMultiply(double number1, double number2, double expectedValue){
            double result = basicMathService.modulo(number1, number2);
            Assertions.assertEquals(expectedValue, result);
        }
        public static Stream<Arguments> basicModuloFactory() {
            return Stream.of(
                    Arguments.of(3, 2, 1),
                    Arguments.of(100, -3, 1),
                    Arguments.of(-7, -3, 1)




            );
        }
    }
