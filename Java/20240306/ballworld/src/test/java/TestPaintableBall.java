import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.awt.Color;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nhnacademy.PaintableBall;

class TestPaintableBall {
    
    @ParameterizedTest
    @MethodSource("constructorProvider")
    void testConstructor(int x, int y, int radius, Color color) {
        assertDoesNotThrow(() -> {
            new PaintableBall(x, y, radius, color);
        });
    }

    static Stream<Arguments> constructorProvider() {
        return Stream.of(
            Arguments.arguments(0, 0, 1, Color.RED),
            Arguments.arguments(0, 0, 1, Color.BLUE)
        );
    }
}
