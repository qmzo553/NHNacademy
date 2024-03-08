import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import com.nhnacademy.Vector;

class TestVector {

    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> {
            int dx = 100;
            int dy = -100;
            Vector vector = new Vector(dx, dy);

            assertEquals(dx, vector.getDX());
            assertEquals(dy, vector.getDY());
        });
    }

    @Test
    void TestSet() {
        assertDoesNotThrow(() -> {
            Vector targetVector = new Vector(100, 100);
            Vector otherVector = new Vector(100, 100);

            assertEquals(targetVector, otherVector);
        });
    }
}