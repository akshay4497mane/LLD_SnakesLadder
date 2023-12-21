package entityTesting;
import org.junit.jupiter.api.Test;

import entities.CrocodileMove;
import exception.InvalidPositionException;
import static org.junit.jupiter.api.Assertions.*;

class CrocodileMoveTest {

    @Test
    void getNextPosition_basicCase() throws InvalidPositionException {
        CrocodileMove crocodileMove = new CrocodileMove(7, 10);
        assertEquals(2, crocodileMove.getNextPosition());
    }

    @Test
    void getNextPosition_negativeStartPosition_shouldThrowInvalidPositionException() {
        CrocodileMove crocodileMove = new CrocodileMove(-2, 5);
        assertThrows(InvalidPositionException.class, crocodileMove::getNextPosition);
    }

    @Test
    void getNextPosition_negativeEndPosition_shouldThrowInvalidPositionException() {
        CrocodileMove crocodileMove = new CrocodileMove(2, -5);
        assertThrows(InvalidPositionException.class, crocodileMove::getNextPosition);
    }

    @Test
    void getNextPosition_bothNegativePositions_shouldThrowInvalidPositionException() {
        CrocodileMove crocodileMove = new CrocodileMove(-2, -5);
        assertThrows(InvalidPositionException.class, crocodileMove::getNextPosition);
    }

    @Test
    void getNextPosition_startPositionGreaterThanOrEqual5_shouldReturnStartMinus5() throws InvalidPositionException {
        CrocodileMove crocodileMove = new CrocodileMove(8, 10);
        assertEquals(3, crocodileMove.getNextPosition());
    }

    // Add more test cases as needed

}
