package entityTesting;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import entities.Board;
import entities.Cell;
import entities.CrocodileMove;
import entities.LadderMove;
import entities.NormalDiceMove;
import entities.Player;
import exception.InvalidPositionException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BoardTest {

    @Mock
    private Logger logger;

    @Mock
    private Cell cell;

    @InjectMocks
    private Board board;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        board = new Board(getCells(100));
        for(int i=0; i<100;i++) {
            board.getCells().get(i).setMove(new NormalDiceMove(i, i));
        }
    }

    @Test
    public void getNextPositionTest() throws InvalidPositionException {
        int nextPosition = board.moveToNextPosition(new Player(2,"Ak"), 5);
        board.getCells().get(7).setMove(new NormalDiceMove(7, 7));
        board.getCells().get(7).setMove(new NormalDiceMove(7, 7));
        Assert.assertEquals(nextPosition, 7);
    }
    @Test
    public void testGetNextPositionWhenCurrentPositionIsAtEnd() throws InvalidPositionException {
        int nextPosition = board.moveToNextPosition(new Player(99,"Ak"), 5);
        Assert.assertEquals(99, nextPosition);
    }
    @Test
    public void testMoveToNextPositionWhenCurrentPositionIsLessThanZero() throws InvalidPositionException {
        // Arrange
        Board board = new Board(getCells(20));
        Player player = new Player(-2, "Ak");
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> board.moveToNextPosition(player, 5));
    }
    public static List<Cell> getCells(int numberOfCells) {
        List<Cell> cells = new ArrayList<>();
        for (int i = 1; i <= numberOfCells; i++) {
            cells.add(new Cell(i));
        }
        return cells;
    }
    @Test
    public void testGetPositionWhenMoveIsNull() throws InvalidPositionException {
        int nextPosition = board.moveToNextPosition(new Player(42,"Ak"), 5);
        Assert.assertEquals(47, nextPosition);
    }
    
    @Test
    void testMoveToNextPosition_withValidMove_shouldMoveCorrectly() throws InvalidPositionException {
        Player player = new Player(92, "TestPlayer");
        int newPosition = board.moveToNextPosition(player, 4);
        assertEquals(96, newPosition);
    }
    

    @Test
    void testMoveToNextPosition_withLadderMove_EmptyCells() throws InvalidPositionException {
            Player player = new Player(30, "TestPlayer");
            board.getCells().get(34).setMove(new LadderMove(34, 46));
            int newPosition = board.moveToNextPosition(player, 4);
            assertEquals(46, newPosition);
    }

    @Test
    void testMoveToNextPosition_withCrocodileMove_shouldLogCorrectMessage() throws InvalidPositionException {
        Player player = new Player(0, "TestPlayer");
        board.getCells().get(6).setMove(new CrocodileMove(6, 6));
        int newPosition = board.moveToNextPosition(player, 6);
        assertEquals(1, newPosition);
    }

    @Test
    void testMoveToNextPosition_withMultipleCells_shouldMoveCorrectly() throws InvalidPositionException {
        List<Cell> cells = Arrays.asList(cell, cell, cell, cell, cell, cell, cell);
        board = new Board(cells);
        Player player = new Player(2, "TestPlayer");
        when(cell.nextPositionByMoveType(eq(player), anyInt())).thenReturn(5);
        when(cell.getPosition()).thenReturn(2);

        int newPosition = board.moveToNextPosition(player, 3);

        assertEquals(5, newPosition);
    }

    @Test
    void testMoveToNextPosition_withPlayerAtLastCell_shouldReturnLastCell() throws InvalidPositionException {
        List<Cell> cells = Arrays.asList(cell, cell, cell, cell, cell, cell, cell);
        board = new Board(cells);
        Player player = new Player(6, "TestPlayer");

        int newPosition = board.moveToNextPosition(player, 1);

        assertEquals(6, newPosition);
    }

    // Mocking the LOGGER for verification
    private static final Logger LOGGER = mock(Logger.class);
}
