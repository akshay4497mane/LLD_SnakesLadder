package entities;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.InvalidPositionException;

public class Board {
    List<Cell> cells;
    private static final Logger LOGGER = LoggerFactory.getLogger(Board.class);
    
    public List<Cell> getCells() {
        return cells;
    }

    public Board(List<Cell> cells) {
        this.cells = cells;
    }

    public int moveToNextPosition(Player player, int score) throws InvalidPositionException { //97,6
        LOGGER.info("Board: Entering moveToNextPosition()");

    	int currentPosition = player.getPosition();
    	if (currentPosition < 0)
            throw new IllegalArgumentException("Position should be greater than zero");
        
        //Check boundary condition crossing SIZE
        if( currentPosition + score >= cells.size()) {
            LOGGER.info("{} rolled a {} and moved from {} to {}.",
                    player.getPlayerName(), score, currentPosition, currentPosition);
        	return currentPosition;
        }
        Cell nextCellByScore = cells.get(currentPosition + score);
                
        int nextPosByMove = nextCellByScore.nextPositionByMoveType(player, score);

        return nextPosByMove;
    }
}
