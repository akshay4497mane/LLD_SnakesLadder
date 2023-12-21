package algorithms;

import java.util.Random;

public class MinDice implements DiceStrategy{
//	Dice[] diceArray;
//    public MinDice(Dice[] dice) {
//    	diceArray = dice;
//    }
	public int rollMultipleDice(Dice[] diceArray) {
    	Random random = new Random();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < diceArray.length; i++) {
            int roll = diceArray[i].rollDice();            
            min = Math.min(min, roll);
        }
        return min;
    }
}