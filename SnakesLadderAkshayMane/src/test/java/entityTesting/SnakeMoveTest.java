package entityTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.SnakeMove;
import exception.InvalidPositionException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SnakeMoveTest {

    @Test
    public void getNextPosition_shouldReturnEnd() throws InvalidPositionException {
        SnakeMove snakeMove = new SnakeMove(10, 5);
        int result = snakeMove.getNextPosition();
        assertEquals(5, result, "Should return the 'end' position");
    }

    @Test
    public void getNextPosition_withSameStartEnd_shouldReturnEnd() throws InvalidPositionException {
        SnakeMove snakeMove = new SnakeMove(8, 8);
        int result = snakeMove.getNextPosition();
        assertEquals(8, result, "Should return the 'end' position");
    }

    @Test
    public void getNextPosition_withNegativeStart_shouldThrowInvalidPositionException() {
        SnakeMove snakeMove = new SnakeMove(-5, 3);
        assertThrows(InvalidPositionException.class, snakeMove::getNextPosition);
    }

    @Test
    public void getNextPosition_withNegativeEnd_shouldThrowInvalidPositionException() {
        SnakeMove snakeMove = new SnakeMove(2, -1);
        assertThrows(InvalidPositionException.class, snakeMove::getNextPosition);
    }

    @Test
    public void getNextPosition_withValidPositions_shouldNotThrowException() {
        SnakeMove snakeMove = new SnakeMove(12, 7);
        assertDoesNotThrow(() -> snakeMove.getNextPosition(), "Should not throw exception for valid positions");
    }

    // Add more test cases as needed

}
