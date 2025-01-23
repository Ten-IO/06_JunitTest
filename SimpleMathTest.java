package lab06;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class SimpleMathTest {
    @Test
    public void testAdditionPositive() {
        double result = SimpleMath.addition(3.5, 7.5);
        assertEquals(11.0, result, 0.01);
    }

    @Test
    public void testAdditionNegative() {
        double result = SimpleMath.addition(-3.5, -7.56);
        assertEquals(-11.06, result, 0.01);
    }

    @Test
    public void testSubtractionPositive() {
        double result = SimpleMath.subtraction(5, 1);
        assertEquals(4, result);
    }

    @Test
    public void testSubtractionNegative() {
        double result = SimpleMath.subtraction(-1, -3);
        assertEquals(2, result);
    }

    @Test
    public void testMultiplyPositive() {
        double result = SimpleMath.multiplication(22, 02);
        assertEquals(44, result);

    }

    @Test
    public void testMultiplyNegative() {
        double result = SimpleMath.multiplication(-101, -36);
        assertEquals(3636, result);

    }

    @Test
    public void testDivisionBisZero() {
        double result = SimpleMath.division(25, 5);
        assertEquals(5, result);
        Exception exc = assertThrows(IllegalArgumentException.class, () -> SimpleMath.division(25, 0));
        assertNotNull(exc);
    }

    @Test
    public void testDivisionBnotZero() {
        double result = SimpleMath.division(25, 5);
        assertEquals(5, result);
        assertEquals(2.5, SimpleMath.division(-5, -2));
    }
    @Test
    public void testDivisionOverZero() {
        Exception exc = assertThrows(IllegalArgumentException.class, () -> SimpleMath.division(-1, 0));
        assertNotNull(exc);
        assertEquals("Undefined: division by zero.", exc.getMessage());
    }

    @Test
    public void testDivisionZeroOverZero() {
        Exception exc = assertThrows(IllegalArgumentException.class, () -> SimpleMath.division(0, 0));
        assertNotNull(exc);
        assertEquals("Indeterminate: both numerator and denominator are zero.", exc.getMessage());

    }
}
