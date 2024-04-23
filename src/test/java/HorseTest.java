import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    Horse testHorse1 = new Horse("Конь", 1, 1);
    Horse testHorse2 = new Horse("Конь", 1);

    @Test
    void testExpectedException_WhenConstructorFirstArgNull() {

        Throwable exсeption = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(null, 1, 1);
                });

        assertEquals("Name cannot be null.", exсeption.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t", "\n", "\r"})
    void testExpectedException_WhenConstructorFirstArgIsBlank(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(name, 1, 1);
                });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void testExpectedException_WhenConstructorSecondArgNegative() {

        Throwable exсeption = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse("Конь", -1, 1);
                });

        assertEquals("Speed cannot be negative.", exсeption.getMessage());
    }

    @Test
    void testExpectedException_WhenConstructorThirdArgNegative() {

        Throwable exсeption = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse("Конь", 1, -1);
                });

        assertEquals("Distance cannot be negative.", exсeption.getMessage());
    }

    @Test
    void getNameTest() {
        assertEquals("Конь", testHorse1.getName());
    }

    @Test
    void getSpeedTest() {
        assertEquals(1, testHorse1.getSpeed());
    }

    @Test
    void getDistanceTest_ConstructorThreeParameters() {
        assertEquals(1, testHorse1.getDistance());
    }

    @Test
    void getDistanceTest_ConstructorTwoParameters() {
        assertEquals(0, testHorse2.getDistance());
    }

    @Test
    void testMoveCall_getRandomDouble() {
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {

            testHorse1.move();

            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}