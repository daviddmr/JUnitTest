package service;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void shouldSumTwoNumbers() {
        int a = 3;
        int b = 5;

        Calculator calculator = new Calculator();
        int result = calculator.sum(a, b);

        Assert.assertEquals(8, result);
    }

    @Test
    public void shouldSubtractTwoNumbers() {
        int a = 5;
        int b = 3;

        Calculator calculator = new Calculator();
        int result = calculator.sub(a, b);

        Assert.assertEquals(2, result);
    }
}
