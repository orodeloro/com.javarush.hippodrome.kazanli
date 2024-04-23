import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void testExpectedExceptionWhenConstructorWithArgNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void testExpectedExceptionWhenConstructorWithArgEmpty() {

        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Hippodrome(new ArrayList<>());
                });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void testEqualsSetHorsesListAndGetHorsesList() {

        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Конь" + i, 1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void testMove(){

        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse: horses){
            Mockito.verify(horse).move();
        }
    }

    @Test
    void testGetHorseWithMaxDistance() {

        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4,1),
                new Horse("Ace of Spades", 2.5,1.5),
                new Horse("Zephyr", 2.6,1.1),
                new Horse("Blaze", 2.7,1.2),
                new Horse("Lobster", 2.8,1.3),
                new Horse("Pegasus", 2.9,1.4),
                new Horse("Cherry", 3,1.7)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals("Cherry",hippodrome.getWinner().getName());

    }
}