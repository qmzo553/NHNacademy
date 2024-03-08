import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nhnacademy.MovableBall;

class TestMovableBall {

    @ParameterizedTest
    @MethodSource("constructorProvider")
    void testConstructor(int x, int y, int radius, Color color) {
        assertDoesNotThrow(() -> {
            new MovableBall(x, y, radius, color);
        });
    }

    static Stream<Arguments> constructorProvider() {
        return Stream.of(
                Arguments.arguments(10, 10, 10, Color.RED),
                Arguments.arguments(10, 10, 10, Color.BLUE),
                Arguments.arguments(10, 10, 10, Color.GREEN)
        );         
    }

    @ParameterizedTest
    @MethodSource("deltaProvider")
    void testDeltaXY(int x, int y, int radius, int dx, int dy) {
        assertDoesNotThrow(() -> {
            MovableBall ball = new MovableBall(x, y, radius, Color.RED);

            ball.setDX(dx);
            ball.setDY(dy);
            assertEquals(dx, ball.getDX());
            assertEquals(dy, ball.getDY());
        });
    }

    static Stream<Arguments> deltaProvider() {
        return Stream.of(
                Arguments.arguments(10, 10, 10, 0, 0),
                Arguments.arguments(10, 10, 10, Integer.MAX_VALUE, 0),
                Arguments.arguments(10, 10, 10, 0, Integer.MAX_VALUE),
                Arguments.arguments(10, 10, 10, 0, Integer.MIN_VALUE),
                Arguments.arguments(10, 10, 10, Integer.MIN_VALUE, 0)
        );         
    }

    @ParameterizedTest
    @MethodSource("moveTestProvider")
    void testMove(int x, int y, int radius, int dx, int dy, int count) {
        assertDoesNotThrow(() -> {
            MovableBall ball = new MovableBall(x, y, radius, Color.BLUE);

            ball.setDX(dx);
            ball.setDY(dy);

            int currentX = x;
            int currentY = y;

            for(int i = 0; i < count; i++) {
                ball.move();

                currentX += dx;
                currentY += dy;
            }

            assertEquals(currentX, ball.getX());
            assertEquals(currentY, ball.getY());
        });
    }

    static Stream<Arguments> moveTestProvider() {
        return Stream.of(
            Arguments.arguments(0, 0, 1, 10, 10, 5),
            Arguments.arguments(0, 0, 1, 20, 20, 10),
            Arguments.arguments(0, 0, 1, 30, 30, 15),
            Arguments.arguments(0, 0, 1, 40, 40, 20),
            Arguments.arguments(0, 0, 1, 50, 50, 25)
        );
    }
}
