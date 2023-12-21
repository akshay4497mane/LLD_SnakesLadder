import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.phonepe.game.Game;

import algorithms.Dice;
import algorithms.DiceStrategy;
import algorithms.MinDice;
import algorithms.SumDice;
import entities.Board;
import entities.Cell;
import entities.Player;
import exception.GameOverException;
import exception.InvalidPlayerException;
import exception.InvalidPositionException;

class GameTest {

    private Game game;
    private Board mockBoard;
    private Dice[] mockDiceArray;
    private DiceStrategy diceStrategy;
    @BeforeEach
    void setUp() {
        mockBoard = Mockito.mock(Board.class);
        mockDiceArray = new Dice[3];
        mockDiceArray[0] = Mockito.mock(Dice.class);
        mockDiceArray[1] = Mockito.mock(Dice.class);
        mockDiceArray[2] = Mockito.mock(Dice.class);
        diceStrategy = new SumDice();
        game = new Game(createMockPlayers(), mockBoard, mockDiceArray,diceStrategy);
    }

    @Test
    void testStartGame_withTwoPlayers_shouldFinishGame() throws GameOverException, InvalidPlayerException, InvalidPositionException {
        // Mocking the behavior of the board and dice
        Mockito.when(mockBoard.getCells()).thenReturn(createMockCells());
        Mockito.when(diceStrategy.rollMultipleDice(mockDiceArray)).thenReturn(6);

        game.startGame();

        // Assert that the game finishes with two players
        assertEquals(0, game.getResult().size());
    }

    @Test
    void testPlay_withPlayerReachingEnd_shouldUpdateGameStatus() throws InvalidPlayerException, InvalidPositionException, GameOverException {
        // Mocking the behavior of the board and dice
        Mockito.when(mockBoard.getCells()).thenReturn(createMockCells());
        Mockito.when(diceStrategy.rollMultipleDice(mockDiceArray)).thenReturn(6);

        // Playing with a player to reach the end
        Player player = game.getNextPlayerToPlay();
        game.play(player);

        // Assert that the game status is updated
        assertFalse(game.getResult().containsValue(player));
    }

    @Test
    void testPlay_withPlayerNotReachingEnd_shouldAddPlayerBackToQueue() throws InvalidPlayerException, InvalidPositionException, GameOverException {
        // Mocking the behavior of the board and dice
        Mockito.when(mockBoard.getCells()).thenReturn(createMockCells());
        Mockito.when(diceStrategy.rollMultipleDice(mockDiceArray)).thenReturn(3);

        // Playing with a player that doesn't reach the end
        Player player = game.getNextPlayerToPlay();
        game.play(player);

        // Assert that the player is added back to the queue
        assertTrue(game.getPlayerQueue().contains(player));
    }

    @Test
    void testPlay_withPlayerReachingEndAndOnePlayerLeft_shouldFinishGame() throws InvalidPlayerException, InvalidPositionException, GameOverException {
        // Mocking the behavior of the board and dice
        Mockito.when(mockBoard.getCells()).thenReturn(createMockCells());
        Mockito.when(diceStrategy.rollMultipleDice(mockDiceArray)).thenReturn(6);

        // Playing with one player to reach the end
        Player player1 = game.getNextPlayerToPlay();
        game.play(player1);

        // Mocking the board to simulate one player left
        Mockito.when(mockBoard.getCells()).thenReturn(createMockCells());

        // Playing with the last player to finish the game
        Player player2 = game.getNextPlayerToPlay();
        game.play(player2);

        // Assert that the game finishes with two players
        assertEquals(0, game.getResult().size());
    }

    @Test
    void testPlay_withPlayerAlreadyAtEnd_shouldThrowInvalidPlayerException() {
        // Creating a player already at the end
        Player player = new Player(2, "Player1");
        game.getPlayerQueue().add(player);

        // Assert that playing with a player already at the end throws InvalidPlayerException
        assertThrows(InvalidPlayerException.class, () -> game.play(player));
    }

    // Add more test cases for other scenarios and edge cases

    private List<Player> createMockPlayers() {
        return Arrays.asList(new Player(0, "Player1"), new Player(0, "Player2"));
    }

    private List<Cell> createMockCells() {
        // Implement the creation of mock cells for the board
        // You can use Mockito.mock(Cell.class) to create mock cells
        return Arrays.asList(Mockito.mock(Cell.class), Mockito.mock(Cell.class), Mockito.mock(Cell.class));
    }
}
