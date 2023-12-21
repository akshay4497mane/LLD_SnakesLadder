package entityTesting;
import org.junit.jupiter.api.Test;

import entities.LadderMove;
import exception.InvalidPositionException;
import static org.junit.jupiter.api.Assertions.*;

class LadderMoveTest {

    @Test
    void getNextPosition_basicCase() throws InvalidPositionException {
        LadderMove ladderMove = new LadderMove(2, 5);
        assertEquals(5, ladderMove.getNextPosition());
    }

    @Test
    void getNextPosition_negativeStartPosition_shouldThrowInvalidPositionException() {
        LadderMove ladderMove = new LadderMove(-2, 5);
        assertThrows(InvalidPositionException.class, ladderMove::getNextPosition);
    }

    @Test
    void getNextPosition_negativeEndPosition_shouldThrowInvalidPositionException() {
        LadderMove ladderMove = new LadderMove(2, -5);
        assertThrows(InvalidPositionException.class, ladderMove::getNextPosition);
    }

    @Test
    void getNextPosition_bothNegativePositions_shouldThrowInvalidPositionException() {
        LadderMove ladderMove = new LadderMove(-2, -5);
        assertThrows(InvalidPositionException.class, ladderMove::getNextPosition);
    }

    // Add more test cases as needed

}
