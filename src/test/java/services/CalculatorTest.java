package services;

import exceptions.DivideNumberByZeroException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void shouldSumTwoNumbers() {
        int a = 3;
        int b = 5;

        int result = calculator.sum(a, b);

        Assert.assertEquals(8, result);
    }

    @Test
    public void shouldSubtractTwoNumbers() {
        int a = 5;
        int b = 3;

        int result = calculator.sub(a, b);

        Assert.assertEquals(2, result);
    }

    @Test
    public void shouldDivideTwoNumber() throws DivideNumberByZeroException {
        int a = 6;
        int b = 3;

        int result = calculator.divide(a, b);

        Assert.assertEquals(2, result);
    }

    @Test(expected = DivideNumberByZeroException.class)
    public void divideANumberByZeroException() throws DivideNumberByZeroException {
        int a = 6;
        int b = 0;

        calculator.divide(a, b);
    }
}
