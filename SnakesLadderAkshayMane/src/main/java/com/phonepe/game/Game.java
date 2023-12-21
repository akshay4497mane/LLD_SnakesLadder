package com.phonepe.game;
import exception.GameOverException;
import exception.InvalidPlayerException;
import exception.InvalidPositionException;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algorithms.Dice;
import algorithms.DiceStrategy;
import entities.Board;
import entities.Player;

public class Game {
    private final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    private final Board board;
    private Dice[] diceArray;
    DiceStrategy diceStrategy;
    
    private final Queue<Player> playerQueue;
    
    private final Map<Integer, Player> result;
    private int winnerCount;

    public void startGame() throws GameOverException, InvalidPlayerException, InvalidPositionException {
        LOGGER.info("Starting the game");
        int i=0;
        while( getPlayerQueue().size() >=2 ) {
            Player currPlayer = getNextPlayerToPlay();    
            play(currPlayer);               
        	System.out.println("\n*" + i + "*");
        	i+=1;
        	if(i==3000)
        		break;
        }
        if(i == 3000)
        	System.out.println("Game ended abruptly after 3000 iterations. winners are : ");
        else
        	System.out.println("GAME FINISHED Successfully...Winners Are: ");
    }
    
    public Queue<Player> getPlayerQueue() {
        return playerQueue;
    }

    public Map<Integer, Player> getResult() {
        return result;
    }
    
    public void setDiceArray(Dice[] dice) {
        this.diceArray = dice;
    }

    public Game(List<Player> playerList, Board board, Dice[] diceArray, DiceStrategy diceStrategy) {
        LOGGER.info("Initializing game");
        this.board = board;
        this.diceArray = diceArray;
        this.diceStrategy = diceStrategy;
        this.result = new HashMap<Integer, Player>();
        this.playerQueue = new ArrayDeque<Player>(playerList);
        
    }

    public Player getNextPlayerToPlay() throws GameOverException {
        if (playerQueue.size() <= 1)
            throw new GameOverException("Game is already over");
        Player player = playerQueue.poll();
        while(player != null && player.getSkipChances() != 0 ) {
        	player.setSkipChances(player.getSkipChances()-1);
        	playerQueue.add(player);
        	player = playerQueue.poll();
        }        
        return player;
    }

    public void play(Player player) throws InvalidPlayerException, InvalidPositionException {
        if (!checkReachToEnd(player)) {
        	int diceResult = diceStrategy.rollMultipleDice(diceArray);
        	
            int nextPosition = board.moveToNextPosition(player, diceResult);
            
            if( nextPosition != 0 ) {
                Player oldPlayer = board.getCells().get(nextPosition).getCurrPlayer();
                if( oldPlayer != null && !oldPlayer.equals(player)) {
                	oldPlayer.setPosition(0);//reset Old Player to 0
                	System.out.println("Acc to RULE3: " + player.getPlayerName() + " occupied position " + nextPosition +" . Sent back " + oldPlayer.getPlayerName() + " back to ZERO. ");
                }
                board.getCells().get(player.getPosition()).setCurrPlayer(null);
            	board.getCells().get(nextPosition).setCurrPlayer(player);            	
            }              
            player.setPosition(nextPosition);
            if( board.getCells().get(nextPosition).isMineActive() ) {
            	player.setSkipChances( 2 );
            	System.out.println(player.getPlayerName() + " stepped on a MINE() at " + nextPosition + " Thus CANNOT PLAY for 2 chances :" + nextPosition );            	
            }
            updateGameStatus(player);
        } else throw new InvalidPlayerException("player already reach end of game");
    }

    private void updateGameStatus(Player player) {
        if (checkReachToEnd(player)) {
            result.put(++winnerCount, player);
            if (playerQueue.size() == 1) {
                result.put(++winnerCount, playerQueue.poll());
            }
            board.getCells().get(player.getPosition()).setCurrPlayer(null);
        } else {
        	playerQueue.add(player);
        }
    }

    public void printGameWinners() {
        for(Map.Entry<Integer, Player> entry : result.entrySet()) {
        	System.out.println(entry.getKey() + " " + entry.getValue().getPlayerName());
        }    	
    }

    private boolean checkReachToEnd(Player player) {
        return player.getPosition() >= board.getCells().size()-1;
    }
}
