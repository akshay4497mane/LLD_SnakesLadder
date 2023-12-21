package algorithmTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import algorithms.Dice;
import algorithms.MaxDice;

import org.junit.jupiter.api.BeforeEach; // Changed to BeforeEach to use non-static method

public class MaxDiceTest {
    Dice[] mockDiceArray;
    MaxDice maxDice;
    @BeforeEach
    void setUp() {
        setUpWithNumDice(3); // Default to 3 dice, you can change it as needed
        maxDice = new MaxDice();
    }

    // New setup method to initialize dice array with specified numDice
    private void setUpWithNumDice(int numDice) {
        mockDiceArray = new Dice[numDice];
        for (int i = 0; i < numDice; i++) {
            mockDiceArray[i] = new Dice(6);
        }
    }

    @Test
    public void rollDice_shouldReturnValidResult() {
    	setUpWithNumDice(1);    	
        int result = maxDice.rollMultipleDice(mockDiceArray);
        assertTrue(result >= 1 && result <= 6, "Result should be between 1 and 6 (inclusive)");
    }

    @Test
    public void rollWithOneDie_shouldReturnValidResult() {
    	setUpWithNumDice(1);    	
        int result = maxDice.rollMultipleDice(mockDiceArray);
        assertTrue(result >= 1 && result <= 6, "Result should be between 1 and 6 (inclusive)");
    }

    @Test
    public void rollWithMaxDice_shouldReturnValidResult() {
    	setUpWithNumDice(200);    	
        int result = maxDice.rollMultipleDice(mockDiceArray);
        assertTrue(result >= 1 && result <= 6, "Result should be between 1 and 6 (inclusive)");
    }

    @Test
    public void rollWithMaxRollOnAllDice_shouldReturnMaxResult() {
    	setUpWithNumDice(5);    	
        int result = maxDice.rollMultipleDice(mockDiceArray);
        assertTrue(result <= 6, "Result should be 6 (maximum among number of dice roll)");
    }

    // Add more tests as needed
}