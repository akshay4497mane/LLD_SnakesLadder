package algorithms;

import java.util.Random;

public class Dice {
	int maxVal; //5 or 6 or 10

    public Dice(int maxVal) {
    	this.maxVal = maxVal;        
    }

    public int rollDice() {
    	Random random = new Random();
        int ans = 1 + random.nextInt(maxVal);
        return ans;
    }
}