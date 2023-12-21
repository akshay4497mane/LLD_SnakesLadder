package entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.InvalidPositionException;

public class LadderMove extends Move {
    private static final Logger LOGGER = LoggerFactory.getLogger(LadderMove.class);

    public LadderMove(int start, int end) {
        super(start, end);
    }

    @Override
    public int getNextPosition() throws InvalidPositionException {
        LOGGER.info("LADDER() : {} : {}", start, end);
        // Additional check for negative start position
        if ( start < 0 || end < 0 ) {
            throw new InvalidPositionException("Invalid start position for SnakeMove: " + start);
        }
        return end;
    }
}
