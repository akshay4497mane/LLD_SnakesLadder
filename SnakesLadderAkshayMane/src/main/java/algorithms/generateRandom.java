package algorithms;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

import entities.Board;
import entities.Cell;
import entities.CrocodileMove;
import entities.LadderMove;
import entities.Move;
import entities.SnakeMove;
import exception.InvalidInputException;

public class generateRandom {
    private static final Logger LOGGER = LoggerFactory.getLogger(Board.class);
    boolean[][] usedUpCellsMap;
    public generateRandom(int boardSize) {
    	usedUpCellsMap = new boolean[boardSize][boardSize];
    }
    
    public void initializeRandomMoves(JsonNode jsonNode, String moveType, List<Cell> cells) throws InvalidInputException {
        LOGGER.info("Entering initializeRandomMoves method for " + moveType);
        int boardSize = jsonNode.get("boardSize").asInt();
        int totalCells = boardSize * boardSize;

        int numberOfMoves = jsonNode.get("numberOf" + moveType.substring(0, 1).toUpperCase() + moveType.substring(1)).asInt();
        for (int i = 0; i < numberOfMoves; i++) {
            int start=0, end=0;

            // Generate random start and end values within the board size
            if ( moveType.equalsIgnoreCase("snakes") ) {
                do {
                	start = boardSize + (int) (Math.random() * (totalCells - boardSize));
                	end = (int) (Math.random() * (start - boardSize));
                } while (usedUpCellsMap[start / boardSize][start % boardSize] || usedUpCellsMap[end / boardSize][end % boardSize]);
            } else if (moveType.equalsIgnoreCase("ladders")) {
                do {
                	start = (int) (Math.random() * (totalCells - boardSize));
                	end = start + (int) (Math.random() * (totalCells - start));
                } while (usedUpCellsMap[start / boardSize][start % boardSize] || usedUpCellsMap[end / boardSize][end % boardSize]);            
            }else if (moveType.equalsIgnoreCase("crocodiles")) {
                do {
                	start = 5 + (int) (Math.random() * (totalCells - 10));
                } while (usedUpCellsMap[start / boardSize][start % boardSize] );            
            }


            Move currBoardMove = null;
            if (moveType.equalsIgnoreCase("snakes") && start > end && start / boardSize > end / boardSize) {
                currBoardMove = new SnakeMove(start, end);
            } else if (moveType.equalsIgnoreCase("ladders") && start < end && start / boardSize < end / boardSize) {
                currBoardMove = new LadderMove(start, end);
            }else if (moveType.equalsIgnoreCase("crocodiles") && start >=5 && start< totalCells-5) {
                currBoardMove = new CrocodileMove(start,start);
            }else {
            	i--;
            	continue;
            }
            	
            // Mark the cells as used
            usedUpCellsMap[start / boardSize][start % boardSize] = true;
            usedUpCellsMap[end / boardSize][end % boardSize] = true;
            LOGGER.info("NEW: " + moveType + " : " + start + "-" + end);
            if (currBoardMove != null) {
                cells.get(start).setMove(currBoardMove);
            }
        }
    }
}
