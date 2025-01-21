package lab06;

public class SimpleMath {
    static double addition(double a, double b) {
        return a + b;
    }

    /**
     * 
     * @param a first number
     * @param b second number
     * @return result of each calculation
     */
    static double subtraction(double a, double b) {
        return a - b;
    }

    static double multiplication(double a, double b) {
        return a * b;
    }

    static double division(double a, double b) {
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("Indeterminate");
        }
        if (b == 0) {
            throw new IllegalArgumentException("Undefined");
        } else
            return a / b;
    }

}
