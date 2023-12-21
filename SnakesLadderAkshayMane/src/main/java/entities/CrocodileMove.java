package entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.InvalidPositionException;

public class CrocodileMove extends Move {
    private static final Logger LOGGER = LoggerFactory.getLogger(CrocodileMove.class);

    public CrocodileMove(int start, int end) {
        super(start, end);
    }

    @Override
    public int getNextPosition() throws InvalidPositionException {
        LOGGER.info("CROCODILE() {}", start);
        // Additional check for negative start position
        if ( start < 0 || end < 0 ) {
            throw new InvalidPositionException("Invalid start position for SnakeMove: " + start);
        }
        if (start >= 5)
            return start - 5;
        return 0;
    }
}
