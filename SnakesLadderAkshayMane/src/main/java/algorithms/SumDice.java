package algorithms;

import java.util.Random;

public class SumDice implements DiceStrategy{

	public int rollMultipleDice(Dice[] diceArray) {
    	Random random = new Random();
        int sum = 0;
        for (int i = 0; i < diceArray.length; i++) {
            sum += diceArray[i].rollDice();            
        }
        return sum;
    }
}