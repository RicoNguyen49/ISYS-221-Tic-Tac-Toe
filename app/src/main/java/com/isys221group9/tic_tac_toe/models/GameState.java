package com.isys221group9.tic_tac_toe.models;

import java.io.Serializable;

public class GameState implements Serializable {
    // Define the serialVersionUID exclusively for class compatibility. This will not be an issue for this
    // for this simple project, but good habit to control this directly instead of java doing it behind the scenes.
    private static final long serialVersionUID = 1L;

    private Player currentPlayer;
    private final GameBoard gameBoard;
    private boolean gameOver;
    private int turnNumber;

    public GameState(GameBoard gameBoard, Player player) {
        this.gameBoard = gameBoard;
        this.currentPlayer = player;
        gameOver = false;
        turnNumber = 1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char getCurrentPlayerMarker() {
        return currentPlayer.getMarker();
    }

    public void switchTurn(Player nextPlayer) {
        this.currentPlayer = nextPlayer;
        System.out.println("Switch Player to " + nextPlayer.getName());
        turnNumber++;
    }

    public boolean isDraw() {
        /*
        If turnNumber is 9, and isWin() isn't true, then the game is a draw. In terms of
        operations efficiency, this is the best. It might be clearer to check the cells directly
        as the performance difference is negligible.
        */
        return turnNumber == 9;
    }

    public boolean isWin() {

        for (int index = 0; index < 3; index++) {
            // Check all rows for a win
            int rowSum = gameBoard.getCell(index, 0) + gameBoard.getCell(index, 1) + gameBoard.getCell(index, 2);
            if (rowSum == -3 || rowSum == 3) {
                return true;
            }

            // Check all columns for a win
            int colSum = gameBoard.getCell(0, index) + gameBoard.getCell(1, index) + gameBoard.getCell(2, index);
            if (colSum == -3 || colSum == 3) {
                return true;
            }
        }

        // Check the first diagonal (top-left to bottom-right)
        int diagonal1Sum = gameBoard.getCell(0, 0) + gameBoard.getCell(1, 1) + gameBoard.getCell(2, 2);
        if (diagonal1Sum == -3 || diagonal1Sum == 3) {
            return true;
        }

        // Check the second diagonal (top-right to bottom-left)
        int diagonal2Sum = gameBoard.getCell(0, 2) + gameBoard.getCell(1, 1) + gameBoard.getCell(2, 0);
        if (diagonal2Sum == -3 || diagonal2Sum == 3) {
            return true;
        }

        // No win
        return false;
    }

    public void update() {
        gameOver = isWin() || isDraw();
    }

    public void resetState(Player player) {
        turnNumber = 1;
        gameOver = false;
        setCurrentPlayer(player);
    }
}
