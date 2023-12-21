package algorithms;

import java.util.Random;

public class MaxDice implements DiceStrategy{
	public int rollMultipleDice(Dice[] diceArray) {
    	Random random = new Random();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < diceArray.length; i++) {
            int roll = diceArray[i].rollDice();            
            max = Math.max(max, roll);
        }
        return max;
    }
}