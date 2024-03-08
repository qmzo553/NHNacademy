import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nhnacademy.Ball;
import com.nhnacademy.InvaildSizeException;
import com.nhnacademy.OutOfRangeException;

class TestBall {
    @ParameterizedTest
    @MethodSource("constructorProvider")
    void testConstructor(int x, int y, int radius) {
        assertDoesNotThrow(() -> {
            new Ball(x, y, radius);
        });
    }

    static Stream<Arguments> constructorProvider() {
        return Stream.of(
            Arguments.arguments(0, 0, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("toStringProvider")
    void testToString(int x, int y, int radius, String target) {
        assertDoesNotThrow(() -> {
            Ball ball = new Ball(x, y, radius);

            assertEquals(target, ball.toString());
        });
    }

    static Stream<Arguments> toStringProvider() {
        return Stream.of(
            Arguments.arguments(0, 0, 1, "0, 0, 1")
        );
    }

    // @ParameterizedTest
    // @MethodSource("illegalArgumentExceptionProvider")
    // void testConstructorWithIllegalArgumentException(int x, int y, int radius) {
    //     assertThrowsExactly(IllegalArgumentException.class, () -> {
    //         new Ball(x, y, radius);
    //     });
    // }

    // static Stream<Arguments> illegalArgumentExceptionProvider() {
    //     return Stream.of(
    //         Arguments.arguments(0, 0, 1),
    //         Arguments.arguments(0, 0, Integer.MAX_VALUE),
    //         Arguments.arguments(100, 100, 100),
    //         Arguments.arguments(100, -100, 100),
    //         Arguments.arguments(-100, 100, 100),
    //         Arguments.arguments(-100, -100, 100),
    //         Arguments.arguments(Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, 1),
    //         Arguments.arguments(Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1, 1),
    //         Arguments.arguments(Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 1, 1),
    //         Arguments.arguments(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, 1)
    //     );
    // }

    // @ParameterizedTest
    // @MethodSource("invaildSizeExceptionProvider")
    // void testConstructorWithInvaildSizeException(int x, int y, int radius) {
    //     assertThrowsExactly(InvaildSizeException.class, () -> {
    //         new Ball(x, y, radius);
    //     });
    // }

    // static Stream<Arguments> invaildSizeExceptionProvider() {
    //     return Stream.of(
    //         Arguments.arguments(0, 0, 0),
    //         Arguments.arguments(0, 0, -0),
    //         Arguments.arguments(0, 0, Integer.MIN_VALUE)
    //     );
    // }

    // @ParameterizedTest
    // @MethodSource("outOfRangeExceptionProvider")
    // void testConstructorWithOutOfRangeException(int x, int y, int radius) {
    //     assertThrowsExactly(OutOfRangeException.class, () -> {
    //         new Ball(x, y, radius);
    //     });
    // }

    // static Stream<Arguments> outOfRangeExceptionProvider() {
    //     return Stream.of(
    //         Arguments.arguments(Integer.MAX_VALUE, 0, 1),
    //         Arguments.arguments(0, Integer.MAX_VALUE, 1),
    //         Arguments.arguments(Integer.MIN_VALUE, 0, 1),
    //         Arguments.arguments(0, Integer.MIN_VALUE, 1),
    //         Arguments.arguments(Integer.MIN_VALUE, Integer.MIN_VALUE, 1),
    //         Arguments.arguments(Integer.MAX_VALUE, Integer.MIN_VALUE, 1),
    //         Arguments.arguments(Integer.MIN_VALUE, Integer.MAX_VALUE, 1),
    //         Arguments.arguments(Integer.MAX_VALUE, Integer.MAX_VALUE, 1),
    //         Arguments.arguments(Integer.MAX_VALUE, 0, Integer.MAX_VALUE),
    //         Arguments.arguments(0, Integer.MAX_VALUE, Integer.MAX_VALUE),
    //         Arguments.arguments(Integer.MIN_VALUE, 0, Integer.MAX_VALUE),
    //         Arguments.arguments(0, Integer.MIN_VALUE, Integer.MAX_VALUE)
    //     );
    // }
}
