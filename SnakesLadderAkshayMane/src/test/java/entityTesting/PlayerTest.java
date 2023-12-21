package entityTesting;

import org.junit.jupiter.api.Test;

import entities.Player;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testEquals_samePlayerInstance_shouldReturnTrue() {
        Player player = new Player(1, "Akshay");
        assertEquals(player, player);
    }

    @Test
    void testEquals_nullPlayer_shouldReturnFalse() {
        Player player = new Player(1, "Akshay");
        assertNotEquals(player, null);
    }

    @Test
    void testEquals_playersWithSamePositionAndName_shouldReturnTrue() {
        Player player1 = new Player(1, "Akshay");
        Player player2 = new Player(1, "Akshay");
        assertEquals(player1, player2);
    }

    @Test
    void testEquals_playersWithDifferentPosition_shouldReturnFalse() {
        Player player1 = new Player(1, "Akshay");
        Player player2 = new Player(2, "Akshay");
        assertNotEquals(player1, player2);
    }

    @Test
    void testHashCode_samePlayerInstance_shouldBeEqual() {
        Player player = new Player(1, "Akshay");
        assertEquals(player.hashCode(), player.hashCode());
    }

    @Test
    void testHashCode_samePlayerNameDifferentPosition_shouldNotBeEqual() {
        Player player1 = new Player(1, "Akshay");
        Player player2 = new Player(2, "Akshay");
        assertNotEquals(player1.hashCode(), player2.hashCode());
    }

    @Test
    void testToString_nullPlayerName_shouldReturnFormattedString() {
        Player player = new Player(3, null);
        assertEquals("Player :null", player.toString());
    }

    @Test
    void testSetPosition_negativePosition_shouldSetPosition() {
        Player player = new Player(1, "Dave");
        player.setPosition(-5);
        assertEquals(-5, player.getPosition());
    }
}
