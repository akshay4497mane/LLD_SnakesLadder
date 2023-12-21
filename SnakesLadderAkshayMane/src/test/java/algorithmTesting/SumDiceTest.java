package algorithmTesting;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import algorithms.Dice;
import algorithms.MaxDice;
import algorithms.SumDice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class SumDiceTest {
    Dice[] mockDiceArray;
    SumDice sumDice;

    @BeforeEach
    void setUp() {
        setUpWithNumDice(3); // Default to 3 dice, you can change it as needed
        sumDice = new SumDice();
    }
    // New setup method to initialize dice array with specified numDice
    private void setUpWithNumDice(int numDice) {
        mockDiceArray = new Dice[numDice];
        for (int i = 0; i < numDice; i++) {
            mockDiceArray[i] = new Dice(6);
        }
    }

    @Test
    public void rollSingleDie_shouldReturnValidResult() {
    	setUpWithNumDice(1);    	
        int result = sumDice.rollMultipleDice(mockDiceArray);
        assertTrue(result >= 1 && result <= 6, "Result should be between 1 and 6 (inclusive)");
    }

    @Test
    public void rollMultipleDice_shouldReturnValidSum() {
        int numDice = 3;
    	setUpWithNumDice(numDice);    	
        int result = sumDice.rollMultipleDice(mockDiceArray);
        assertTrue(result >= numDice && result <= 6 * numDice, "Result should be between numDice and 6 * numDice (inclusive)");
    }

    @Test
    public void rollWithMinDice_shouldReturnValidResult() {
        int numDice = 1;
    	setUpWithNumDice(numDice);    	
        int result = sumDice.rollMultipleDice(mockDiceArray);
        assertTrue(result >= numDice && result <= 6 * numDice, "Result should be between numDice and 6 * numDice (inclusive)");
    }

    @Test
    public void rollWithMaxDice_shouldReturnValidResult() {
        int numDice = 10;
    	setUpWithNumDice(numDice);    	
        int result = sumDice.rollMultipleDice(mockDiceArray);
        assertTrue(result >= numDice && result <= 6 * numDice, "Result should be between numDice and 6 * numDice (inclusive)");
    }

    // Add more tests as needed
}
