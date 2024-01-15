package be.intecbrussel.MathExpressionEvaluatorTest.service;

    import be.intecbrussel.MathExpressionEvaluatorTest.Service.BasicMathService;
    import be.intecbrussel.MathExpressionEvaluatorTest.Service.BasicMathServiceImpl;
    import org.junit.jupiter.api.*;

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class BasicMathServiceTest {
        private BasicMathService basicMathService;

        @BeforeAll
        public void beforeAll() {
            // TODO instantiate the service
            basicMathService = new BasicMathServiceImpl();
        }

        @Test
        public void testBasicAdditionOfTwoIntegers() {
            int firstNumber = 7;
            int secondNumber = 6;

            int expectedValue = 13;

            double result = basicMathService.add(firstNumber, secondNumber);

            Assertions.assertEquals(expectedValue, result);
        }
    }
