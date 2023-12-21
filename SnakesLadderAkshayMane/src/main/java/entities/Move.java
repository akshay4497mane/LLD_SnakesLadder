package entities;

import exception.InvalidPositionException;

public abstract class Move {
	int start;
	int end;	
	
    public Move(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    public abstract int getNextPosition() throws InvalidPositionException;
}
