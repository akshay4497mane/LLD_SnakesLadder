package entities;


import exception.InvalidPositionException;

public class NormalDiceMove extends Move {
	
	public NormalDiceMove(int start, int end) {
		super(start,end);
	}

	@Override
	public int getNextPosition() {
		return end;
	}

}
