package com.isys221group9.tic_tac_toe.controllers;

import com.isys221group9.tic_tac_toe.models.GameBoard;
import com.isys221group9.tic_tac_toe.models.GameState;
import com.isys221group9.tic_tac_toe.models.Player;

public class GameController {
    private GameBoard gameBoard;
    private GameState gameState;
    private Player player1, player2;
    private String gaveOverMessage;

    public GameController() {
        gameBoard = new GameBoard();
        player1 = new Player("Player 1", 'X');
        player2 = new Player("Player 2", 'O');
        gameState = new GameState(gameBoard, player1);
    }

    public void newGame() {
        gameBoard.resetBoard();
        gameState.resetState(player1);
    }

    public void handleMove(int row, int column) {
        if (gameState.isGameOver() || gameBoard.getCell(row, column) != 0) {
            // invalid move
            return;
        }

        char marker = gameState.getCurrentPlayerMarker();
        int value = (marker == 'X') ? -1 : (marker == 'O') ? 1 : 0;

        if (value != 0) {
            gameBoard.markCell(row, column, value);
            gameState.update();
        }

        if (!gameState.isGameOver()) {
            Player nextPlayer = (gameState.getCurrentPlayer() == player1) ? player2 : player1;
            System.out.println("Switching Players...");
            gameState.switchTurn(nextPlayer);
        }
    }

    public boolean isGameOver() {
        setGameOverMessage();
        return gameState.isGameOver();
    }

    public String getGameOverMessage() {
        return gaveOverMessage;
    }

    public void setGameOverMessage() {
        if (gameState.isDraw()) {
            gaveOverMessage = "DRAW!";
        } else {
            gaveOverMessage = String.format("%s WINS!", gameState.getCurrentPlayer().getName());
        }
    }

    public int getBoardCell(int row, int column) {
        return gameBoard.getCell(row, column);
    }

    public GameState getState() {
        // Need to save state for GameBoard, Player1 & Player2, & GameState. Should we have the gameState
        // save all needed data? How would that look...
        // Nope, just serialize the GameState it has everything we need.
        return gameState;
    }

    public void setState(GameState gameState) {
        this.gameState = gameState;
        this.gameBoard = gameState.getGameBoard();

        if (gameState.getCurrentPlayer().getName().equals(player1.getName())) {
            player1 = gameState.getCurrentPlayer();
        } else {
            player2 = gameState.getCurrentPlayer();
        }
    }
}
