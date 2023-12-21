package entityTesting;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import entities.Cell;
import entities.CrocodileMove;
import entities.LadderMove;
import entities.Move;
import entities.NormalDiceMove;
import entities.Player;
import entities.SnakeMove;
import exception.InvalidPositionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CellTest {

    @Mock
    private Logger logger;

    @Mock
    private Move move;

    @Mock
    private Player player;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Cell cell = new Cell(1, move);
    }

    @Test
    public void testSnakeMove() throws InvalidPositionException {
        Cell cell = new Cell(11, new SnakeMove(19, 6));
        Assert.assertEquals(6, cell.nextPositionByMoveType(new Player(0,"Akshay"), 4));
    }

    @Test
    void testNextPositionByMoveType_withSnakeMove_shouldReturnCorrectPosition() throws InvalidPositionException {
        // Arrange
        Move mockMove = Mockito.mock(SnakeMove.class);
        Player mockPlayer = Mockito.mock(Player.class);
        Cell cell = new Cell(1, mockMove);

        // Act
        when(mockMove.getNextPosition()).thenReturn(0);
        when(mockPlayer.getPosition()).thenReturn(2);

        int newPosition = cell.nextPositionByMoveType(mockPlayer, 3);

        // Assert
        assertEquals(1, newPosition);
    }
    @Test
    void testNextPositionByMoveType_withCrocodileMove_shouldReturnCorrectPosition() throws InvalidPositionException {
        // Arrange
        Move mockMove = Mockito.mock(CrocodileMove.class);
        Player mockPlayer = Mockito.mock(Player.class);
        Cell cell = new Cell(1, mockMove);

        // Act
        when(mockMove.getNextPosition()).thenReturn(0);
        when(mockPlayer.getPosition()).thenReturn(6);

        int newPosition = cell.nextPositionByMoveType(mockPlayer, 5);

        // Assert
        assertEquals(1, newPosition);
    }

    @Test
    void testNextPositionByMoveType_withLadderMove_shouldReturnCorrectPosition() throws InvalidPositionException {
        // Arrange
        Move mockMove = Mockito.mock(LadderMove.class);
        Player mockPlayer = Mockito.mock(Player.class);
        Cell cell = new Cell(1, mockMove);

        // Act
        when(mockMove.getNextPosition()).thenReturn(3);
        when(mockPlayer.getPosition()).thenReturn(2);

        int newPosition = cell.nextPositionByMoveType(mockPlayer, 1);

        // Assert
        assertEquals(3, newPosition);
    }    

    @Test
    void testNextPositionByMoveType_withNormalDiceMove_shouldReturnCorrectPosition() throws InvalidPositionException {
        // Arrange
        Move mockMove = Mockito.mock(NormalDiceMove.class);
        Player mockPlayer = Mockito.mock(Player.class);
        Cell cell = new Cell(1, mockMove);

        // Act
        when(mockMove.getNextPosition()).thenReturn(4);
        when(mockPlayer.getPosition()).thenReturn(1);

        int newPosition = cell.nextPositionByMoveType(mockPlayer, 3);

        // Assert
        assertEquals(4, newPosition);
    }    

    @Test
    void testNextPositionByMoveType_withZeroNextPosition_shouldReturnCurrentPosition() throws InvalidPositionException {
        // Arrange
        Move mockMove = Mockito.mock(SnakeMove.class);
        Player mockPlayer = Mockito.mock(Player.class);
        Cell cell = new Cell(1, mockMove);

        // Act
        when(mockMove.getNextPosition()).thenReturn(0);
        when(mockPlayer.getPosition()).thenReturn(8);

        int newPosition = cell.nextPositionByMoveType(mockPlayer, 2);

        // Assert
        assertEquals(1, newPosition);
    }

}
