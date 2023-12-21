package com.phonepe.game;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import algorithms.Dice;
import algorithms.DiceStrategy;
import algorithms.MaxDice;
import algorithms.MinDice;
import algorithms.SumDice;
import algorithms.generateRandom;
import entities.Board;
import entities.Cell;
import entities.CrocodileMove;
import entities.LadderMove;
import entities.Move;
import entities.NormalDiceMove;
import entities.Player;
import entities.SnakeMove;
import exception.GameOverException;
import exception.InvalidInputException;
import exception.InvalidPlayerException;
import exception.InvalidPositionException;

import java.awt.Dialog.ModalExclusionType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(GameRunner.class);
	
    public static void main(String[] args) throws InvalidInputException {
        try {
        	//READ INPUT From File
            JsonNode jsonNode = readJsonFile("inputConfig.json"); //Input            
            Board board = initializeBoard(jsonNode);           
            Dice[] diceArray = initializeDice(jsonNode);
            DiceStrategy diceStrategy = initializeDiceStrategy(jsonNode);
            List<Player> playerList = initializePlayers(jsonNode);
            Game game = new Game(playerList, board, diceArray, diceStrategy);
            game.startGame();

            playerList = initializePlayers(jsonNode);//reset player list

            //Generate Random Board(Snakes, Ladders, Crocodiles)
            LOGGER.info("Entering initializeBoard method");
            int boardSize = jsonNode.get("boardSize").asInt();
            int numberOfCells = boardSize * boardSize;
            List<Cell> randomCells = new ArrayList<Cell>();
            for (int i = 0; i < numberOfCells; i++) {
                randomCells.add(new Cell(i, new NormalDiceMove(i, i)));
            }
            // Initialize random moves for snakes, ladders, and crocodiles
            generateRandom randomiser = new generateRandom(boardSize);
            randomiser.initializeRandomMoves(jsonNode, "snakes", randomCells);
            randomiser.initializeRandomMoves(jsonNode, "ladders", randomCells);
            randomiser.initializeRandomMoves(jsonNode, "crocodiles", randomCells);   
            Board randomBoard = new Board(randomCells);
            
            Game randomGame = new Game(playerList, randomBoard, diceArray, diceStrategy);
            randomGame.startGame();
            

            System.out.println("Input CONFIG GAME WINNERS are: ");
            game.printGameWinners();

            System.out.println("RANDOM Board GAME WINNERS are: ");
            randomGame.printGameWinners();

            
        } catch (IOException e) {
            LOGGER.error("IOException occurred", e);
        }
        catch (GameOverException e) {
            LOGGER.error("Game Over Exception occurred", e);
        } catch (InvalidPlayerException e) {
            LOGGER.error("Invalid Player Exception occurred", e);
        } catch (InvalidPositionException e) {
            LOGGER.error("Invalid Position Exception occurred", e);
        }
    }
    static JsonNode readJsonFile(String jsonFileName) throws IOException {
        LOGGER.info("Entering readJsonFile method");
        //String jsonFileInput = "A:\\eclipse-workspace\\SnakesLadder\\src\\main\\resources\\inputConfig.json";
        //JsonNode jsonNode = readJsonFile(jsonFileInput);            
    	ClassLoader classLoader = GameRunner.class.getClassLoader();
        	//String jsonFileName = "inputConfig.json";
        URL resourceUrl = classLoader.getResource(jsonFileName);
        if (resourceUrl == null) {
            throw new FileNotFoundException("JSON file not found: " + jsonFileName);
        }
        String jsonFilePath = URLDecoder.decode(resourceUrl.getFile(), StandardCharsets.UTF_8);

        
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File(jsonFilePath));

        int boardSize = jsonNode.get("boardSize").asInt();
        int numberOfSnakes = jsonNode.get("numberOfSnakes").asInt();
        int numberOfLadders = jsonNode.get("numberOfLadders").asInt();
        int numberOfCrocodiles = jsonNode.get("numberOfCrocodiles").asInt();
	    int numberOfDies = jsonNode.get("numberOfDies").asInt();
	    int numberOfPlayers = jsonNode.get("numberOfPlayers").asInt();            
	    String diceStrategy = jsonNode.get("diceStrategy").asText();

	    // Existing logging
	    LOGGER.info("Board Size: {}", jsonNode.get("boardSize").asInt());
	    LOGGER.info("Number of Snakes: {}", jsonNode.get("numberOfSnakes").asInt());
	    LOGGER.info("Number of Ladders: {}", jsonNode.get("numberOfLadders").asInt());
	    LOGGER.info("Number of Crocodiles: {}", jsonNode.get("numberOfCrocodiles").asInt());
	    LOGGER.info("Number of Players: {}", jsonNode.get("numberOfPlayers").asInt());
	    LOGGER.info("Number of Dies: {}", jsonNode.get("numberOfDies").asInt());
	    LOGGER.info("Dice Strategy: {}", jsonNode.get("diceStrategy").asText());

	    System.out.println("Board Size: " + boardSize);
        System.out.println("Number of Snakes: " + numberOfSnakes);
        System.out.println("Number of Ladders: " + numberOfLadders);
        System.out.println("Number of Crocodiles: " + numberOfCrocodiles);
        System.out.println("Number of Players: " + numberOfPlayers);
        System.out.println("Number of Dies: " + numberOfDies);
        System.out.println("Dice Strategy: " + diceStrategy);
        
        return jsonNode;
    }    

    private static Board initializeBoard(JsonNode jsonNode) throws InvalidInputException {
        LOGGER.info("Entering initializeBoard method");
        int boardSize = jsonNode.get("boardSize").asInt();
        int numberOfCells = boardSize * boardSize;
        List<Cell> cells = new ArrayList<Cell>();
        for (int i = 0; i < numberOfCells; i++) {
            cells.add(new Cell(i, new NormalDiceMove(i,i)));
        }
        
        //Initialize snake, ladder, Crocodile, Mines details
        updateCells(jsonNode, "snakes" , cells);
        updateCells(jsonNode, "ladders", cells);
        updateCells(jsonNode, "crocodiles", cells);
        updateCells(jsonNode, "mines", cells);
        
        Board board = new Board(cells);
        return board;
    }
    private static Dice[] initializeDice(JsonNode jsonNode) {
        LOGGER.info("Entering initializeDice method");
	    int numberOfDies = jsonNode.get("numberOfDies").asInt();
	    Dice[] diceArray = new Dice[numberOfDies];
	    for(int i = 0 ;i <numberOfDies ;i++) {
	    	diceArray[i] = new Dice(6);
	    }
	    return diceArray;
    }
    private static DiceStrategy initializeDiceStrategy(JsonNode jsonNode) {
        LOGGER.info("Entering initializeDice method");
	    String strategy = jsonNode.get("diceStrategy").asText();
	    
	    DiceStrategy diceStrategy = null;
	    if ( strategy.equalsIgnoreCase("SUM")) {
	    	diceStrategy = new SumDice();            	
	    }else if ( strategy.equalsIgnoreCase("MIN")) {
	    	diceStrategy = new MinDice();            	
	    }else if ( strategy.equalsIgnoreCase("MAX")) {
	    	diceStrategy = new MaxDice();            	
	    }
	    //else NO_DiceFoundException
	    return diceStrategy;
    }
    
    
    

    private static List<Player> initializePlayers(JsonNode jsonNode) {
        LOGGER.info("Entering initializePlayers method");
	    int numberOfPlayers = jsonNode.get("numberOfPlayers").asInt();            
        JsonNode players = jsonNode.get("players");
	    List<Player> playerList = new ArrayList<Player>();
        for(JsonNode player : players) {
        	int start = player.get("start").asInt();
    	    String name = player.get("name").asText();
    	    playerList.add(new Player(start, name));
        }
	    return playerList;
    }
    
    private static void updateCells(JsonNode jsonNode, String moveType, List<Cell> cells) throws InvalidInputException {
        LOGGER.info("Entering updateCells method " + moveType);
        JsonNode moves = jsonNode.get(moveType);
        for (JsonNode move : moves) {
            int start = move.get("start").asInt();
            int end = move.get("end").asInt();
            if(start >=0 && end < cells.size()) {
	            Move currBoardmove = null;
	            if(moveType.equalsIgnoreCase("snakes"))
	            	currBoardmove = new SnakeMove(start, end);
	            else if(moveType.equalsIgnoreCase("ladders"))
	            	currBoardmove = new LadderMove(start, end);
	            else if(moveType.equalsIgnoreCase("crocodiles"))
	            	currBoardmove = new CrocodileMove(start, end);
	            else if(moveType.equalsIgnoreCase("mines"))
	            	cells.get(start).setMineActive(true);

	            if( currBoardmove != null )
	            	cells.get(start).setMove(currBoardmove);
	            
            }else {
            	System.out.println("Invalid : " + moveType + " Entry with " + start + " " + end);
            	//throw new InvalidInputException("Invalid :" + moveType + " Entry with " + start + " " + end);
            }
        }
    }
}