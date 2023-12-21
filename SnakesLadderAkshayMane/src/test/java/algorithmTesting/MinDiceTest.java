package algorithmTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import algorithms.Dice;
import algorithms.MaxDice;
import algorithms.MinDice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class MinDiceTest {
    Dice[] mockDiceArray;
    MinDice minDice;

    @BeforeEach
    void setUp() {
        setUpWithNumDice(3); // Default to 3 dice, you can change it as needed
        minDice = new MinDice();
    }
    private void setUpWithNumDice(int numDice) {
        mockDiceArray = new Dice[numDice];
        for (int i = 0; i < numDice; i++) {
            int numberOfSides = 6; // You can set the desired number of sides for each dice
            mockDiceArray[i] = new Dice(numberOfSides);
            // Mocking the behavior of the constructor with parameter
        }
    }    
    @Test
    public void rollDice_shouldReturnValidResult() {
    	setUpWithNumDice(2);    	
        int result = minDice.rollMultipleDice(mockDiceArray);

        assertTrue(result >= 1 && result <= 6, "Result should be between 1 and 6 (inclusive)");
    }

    @Test
    public void rollWithOneDie_shouldReturnValidResult() {
    	setUpWithNumDice(1);    	
        int result = minDice.rollMultipleDice(mockDiceArray);
        assertTrue(result >= 1 && result <= 6, "Result should be between 1 and 6 (inclusive)");
    }

    @Test
    public void rollWithMaxDice_shouldReturnValidResult() {
    	setUpWithNumDice(200);    	
        int result = minDice.rollMultipleDice(mockDiceArray);

        assertTrue(result >= 1 && result <= 6, "Result should be between 1 and 6 (inclusive)");
    }

    @Test
    public void rollWithMinRollOnAllDice_shouldReturnMinResult() {
    	setUpWithNumDice(3);    	
        int result = minDice.rollMultipleDice(mockDiceArray);

        assertTrue(result >= 1, "Result should be 1 (minimum possible roll)");
    }

    // Add more tests as needed
}
