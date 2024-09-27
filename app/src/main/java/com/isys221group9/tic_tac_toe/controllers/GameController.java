package com.isys221group9.tic_tac_toe.controllers;

import com.isys221group9.tic_tac_toe.models.GameBoard;
import com.isys221group9.tic_tac_toe.models.GameState;
import com.isys221group9.tic_tac_toe.models.Player;

public class GameController {
    private GameBoard gameBoard;
    private GameState gameState;
    private Player player1, player2;

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
        int value = (marker == 'X') ? -1 : 1;
        gameBoard.markCell(row, column, value);
        gameState.update();

        if (!gameState.isGameOver()) {
            Player nextPlayer = (gameState.getCurrentPlayer() == player1) ? player2 : player1;
            gameState.switchTurn(nextPlayer);
        }
    }
}
