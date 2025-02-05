package lab06;

/**
 * Class for handling basic Math Operation
 * 
 * @param a first number
 * @param b second number
 * @return result of each calculation
 */
public class SimpleMath {
    static double addition(double a, double b) {
        return a + b;
    }

    static double subtraction(double a, double b) {
        return a - b;
    }

    static double multiplication(double a, double b) {
        return a * b;
    }

    static double division(double a, double b) {
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("Indeterminate: both numerator and denominator are zero.");
        }
        if (b == 0) {
            throw new IllegalArgumentException("Undefined: division by zero.");
        } 
        return a / b;
    }

}
