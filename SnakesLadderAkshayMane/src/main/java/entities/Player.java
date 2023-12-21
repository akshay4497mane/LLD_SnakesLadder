package entities;

import java.util.Objects;

public class Player {
    private int position;
    private final String playerName;
    private int skipChances; //0 = No skipping | 2 = skip 2 chances

    public Player(int position, String playerName) {
        this.position = position;
        this.playerName = playerName;
        setSkipChances(0);//No skipping
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return position == player.position &&
                Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, playerName);
    }

    @Override
    public String toString() {
        return "Player :" + playerName;
    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

	public int getSkipChances() {
		return skipChances;
	}
	public void setSkipChances(int skipChances) {
		this.skipChances = skipChances;
	}
}