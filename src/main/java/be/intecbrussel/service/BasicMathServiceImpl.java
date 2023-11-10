package be.intecbrussel.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class BasicMathServiceImpl implements BasicMathService {
    @Override
    public String add(String number1, String number2) {
        checkForValidNumbers(number1, number2);

        BigDecimal n1, n2, sum;

        n1 = new BigDecimal(number1);
        n2 = new BigDecimal(number2);

        sum = n1.add(n2);

        return sum.stripTrailingZeros().toPlainString();
    }

    @Override
    public String subtract(String number1, String number2) {
        checkForValidNumbers(number1, number2);

        BigDecimal n1, n2, subtraction;

        n1 = new BigDecimal(number1);
        n2 = new BigDecimal(number2);

        subtraction = n1.subtract(n2);

        return subtraction.stripTrailingZeros().toPlainString();
    }

    @Override
    public String multiply(String number1, String number2) {
        checkForValidNumbers(number1, number2);

        BigDecimal n1, n2, multiplication;

        n1 = new BigDecimal(number1);
        n2 = new BigDecimal(number2);

        multiplication = n1.multiply(n2);

        return multiplication.stripTrailingZeros().toPlainString();
    }

    @Override
    public String divide(String number1, String number2) {
        checkForValidNumbers(number1, number2);

        BigDecimal n1, n2, division;

        n1 = new BigDecimal(number1);
        n2 = new BigDecimal(number2);

        division = n1.divide(n2, 2, RoundingMode.HALF_UP);

        return division.stripTrailingZeros().toPlainString();
    }

    private void checkForValidNumbers(String number1, String number2) {
        if (!number1.matches("^-?\\d*\\.?\\d+$") || !number2.matches("^-?\\d*\\.?\\d+$")) {
            throw new RuntimeException("Invalid number Strings.");
        }
    }
}
