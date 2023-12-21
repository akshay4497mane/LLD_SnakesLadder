package entities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.InvalidPositionException;

public class Cell {
    private final int position;
    private Move move;
    private Player currPlayer;
    private boolean mineActive;
    private static final Logger LOGGER = LoggerFactory.getLogger(Cell.class);
    
	public Cell(int position) {
        this.position = position;
        mineActive = false;
    }

    public Cell(int position, Move move) {
        this.position = position;
        this.move = move;
        mineActive = false;
    }
    
    public int getPosition() {
        return position;
    }

    public int nextPositionByMoveType(Player player, int score ) throws InvalidPositionException {
        int nextPosition = move.getNextPosition();//Depends on Snake, Ladder, Crocodile

        if (nextPosition == 0) {
            return position;
        }

        if (move instanceof SnakeMove) {
            LOGGER.info("{} rolled a {} and bitten by SNAKE at {} and moved from {} to {}",
                    player.getPlayerName(), score, score + player.getPosition(), player.getPosition(), nextPosition);
        } else if (move instanceof CrocodileMove) {
            LOGGER.info("{} rolled a {} and bitten by CROCODILE at {} and moved from {} to {}",
                    player.getPlayerName(), score, score + player.getPosition(), player.getPosition(), nextPosition);
        } else if (move instanceof LadderMove) {
            LOGGER.info("{} rolled a {} and climbed the LADDER at {} and moved from {} to {}",
                    player.getPlayerName(), score, score + player.getPosition(), player.getPosition(), nextPosition);
        } else if (move instanceof NormalDiceMove) {
            LOGGER.info("{} rolled a DICE with {} and moved from {} to {}",
                    player.getPlayerName(), score, player.getPosition(), nextPosition);
        }     
        return nextPosition;
    }

	public void setMove(Move move2) {
    	this.move = move2;
	}
    public Player getCurrPlayer() {
		return currPlayer;
	}
	public void setCurrPlayer(Player currPlayer) {
		this.currPlayer = currPlayer;
	}
	public boolean isMineActive() {
		return mineActive;
	}
	public void setMineActive(boolean mineActive) {
		this.mineActive = mineActive;
	}
}