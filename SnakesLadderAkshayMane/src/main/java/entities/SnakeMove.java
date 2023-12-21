package entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.InvalidPositionException;

public class SnakeMove extends Move {
    private static final Logger LOGGER = LoggerFactory.getLogger(SnakeMove.class);

    public SnakeMove(int start, int end) {
        super(start, end);
    }

    @Override
    public int getNextPosition() throws InvalidPositionException {
        LOGGER.info("SNAKE() : {} : {}", start, end);

        // Additional check for negative start position
        if ( start < 0 || end < 0 ) {
            throw new InvalidPositionException("Invalid start position for SnakeMove: " + start);
        }

        return end;
    }
}
