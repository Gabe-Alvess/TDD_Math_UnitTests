package be.intecbrussel.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class BasicMathServiceTest {
    @Autowired
    private BasicMathService basicMathService;

    private static Stream<Arguments> mathAddData() {
        return Stream.of(
                Arguments.of("10", "5", "15"),
                Arguments.of("5", "10", "15"),
                Arguments.of("0", "0", "0"),
                Arguments.of("-50", "-40", "-90"),
                Arguments.of("2.5", "4.5", "7"),
                Arguments.of("3.2", "4.4", "7.6"),
                Arguments.of("", "55", Exception.class),
                Arguments.of("five", "55", Exception.class),
                Arguments.of("50", "potato", Exception.class),
                Arguments.of("0.000000000000001", "0.999999999999999", "1"),
                Arguments.of("0", "55", "55"),
                Arguments.of("25", "0", "25"),
                Arguments.of("-25", "12", "-13"),
                Arguments.of("25", "-12", "13"),
                Arguments.of("2000000000", "2000000000", "4000000000"),
                Arguments.of("2000000000000000000000000000", "2000000000000000000000000000", "4000000000000000000000000000"),
                Arguments.of(
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "4000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
                )
        );
    }

    private static Stream<Arguments> mathSubtractData() {
        return Stream.of(
                Arguments.of("10", "5", "5"),
                Arguments.of("5", "10", "-5"),
                Arguments.of("0", "0", "0"),
                Arguments.of("-50", "-40", "-10"),
                Arguments.of("2.5", "4.5", "-2"),
                Arguments.of("3.2", "4.4", "-1.2"),
                Arguments.of("", "55", Exception.class),
                Arguments.of("five", "55", Exception.class),
                Arguments.of("50", "potato", Exception.class),
                Arguments.of("0.000000000000001", "0.999999999999999", "-0.999999999999998"),
                Arguments.of("0", "55", "-55"),
                Arguments.of("25", "0", "25"),
                Arguments.of("-25", "12", "-37"),
                Arguments.of("25", "-12", "37"),
                Arguments.of("2000000000", "2000000000", "0"),
                Arguments.of("2000000000000000000000000000", "2000000000000000000000000000", "0"),
                Arguments.of(
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "0"
                )
        );
    }

    private static Stream<Arguments> mathMultiplyData() {
        return Stream.of(
                Arguments.of("10", "5", "50"),
                Arguments.of("5", "10", "50"),
                Arguments.of("0", "0", "0"),
                Arguments.of("-50", "-40", "2000"),
                Arguments.of("2.5", "4.5", "11.25"),
                Arguments.of("3.2", "4.4", "14.08"),
                Arguments.of("", "55", Exception.class),
                Arguments.of("five", "55", Exception.class),
                Arguments.of("50", "potato", Exception.class),
                Arguments.of("0.000000000000001", "0.999999999999999", "0.000000000000000999999999999999"),
                Arguments.of("0", "55", "0"),
                Arguments.of("25", "0", "0"),
                Arguments.of("-25", "12", "-300"),
                Arguments.of("25", "-12", "-300"),
                Arguments.of("2000000000", "2000000000", "4000000000000000000"),
                Arguments.of("2000000000000000000000000000", "2000000000000000000000000000", "4000000000000000000000000000000000000000000000000000000"),
                Arguments.of(
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "4000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
                )
        );
    }

    private static Stream<Arguments> mathDivideData() {
        return Stream.of(
                Arguments.of("10", "5", "2"),
                Arguments.of("5", "10", "0.5"),
                Arguments.of("0", "0", ArithmeticException.class),
                Arguments.of("-50", "-40", "1.25"),
                Arguments.of("2.5", "4.5", "0.56"),
                Arguments.of("3.2", "4.4", "0.73"),
                Arguments.of("", "55", Exception.class),
                Arguments.of("five", "55", Exception.class),
                Arguments.of("50", "potato", Exception.class),
                Arguments.of("0.000000000000001", "0.999999999999999", "0"),
                Arguments.of("0", "55", "0"),
                Arguments.of("25", "0", ArithmeticException.class),
                Arguments.of("-25", "12", "-2.08"),
                Arguments.of("25", "-12", "-2.08"),
                Arguments.of("2000000000", "2000000000", "1"),
                Arguments.of("2000000000000000000000000000", "2000000000000000000000000000", "1"),
                Arguments.of(
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "1"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("mathAddData")
    public void testBasicAddition(String n1, String n2, Object expectedResult) {
        if (expectedResult instanceof String) {
            String res = basicMathService.add(n1, n2);
            Assertions.assertEquals(expectedResult, res);
        } else if (expectedResult instanceof Class && Exception.class.isAssignableFrom((Class<?>) expectedResult)) {
            Assertions.assertThrows((Class<? extends Exception>) expectedResult, () -> basicMathService.add(n1, n2));
        } else {
            Assertions.fail();
        }
    }

    @ParameterizedTest
    @MethodSource("mathSubtractData")
    public void testBasicSubtract(String n1, String n2, Object expectedResult) {
        if (expectedResult instanceof String) {
            String res = basicMathService.subtract(n1, n2);
            Assertions.assertEquals(expectedResult, res);
        } else if (expectedResult instanceof Class && Exception.class.isAssignableFrom((Class<?>) expectedResult)) {
            Assertions.assertThrows((Class<? extends Exception>) expectedResult, () -> basicMathService.subtract(n1, n2));
        } else {
            Assertions.fail();
        }

    }

    @ParameterizedTest
    @MethodSource("mathMultiplyData")
    public void testBasicMultiply(String n1, String n2, Object expectedResult) {
        if (expectedResult instanceof String) {
            String res = basicMathService.multiply(n1, n2);
            Assertions.assertEquals(expectedResult, res);
        } else if (expectedResult instanceof Class && Exception.class.isAssignableFrom((Class<?>) expectedResult)) {
            Assertions.assertThrows((Class<? extends Exception>) expectedResult, () -> basicMathService.multiply(n1, n2));
        } else {
            Assertions.fail();
        }
    }

    @ParameterizedTest
    @MethodSource("mathDivideData")
    public void testBasicDivision(String n1, String n2, Object expectedResult) {
        if (expectedResult instanceof String) {
            String res = basicMathService.divide(n1, n2);
            Assertions.assertEquals(res, expectedResult);
        } else if (expectedResult instanceof Class && Exception.class.isAssignableFrom((Class<?>) expectedResult)) {
            Assertions.assertThrows((Class<? extends Exception>) expectedResult, () -> basicMathService.divide(n1, n2));
        } else {
            Assertions.fail();
        }
    }
}
