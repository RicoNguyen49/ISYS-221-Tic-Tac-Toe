package com.isys221group9.tic_tac_toe.models;

public class GameState {
    private Player currentPlayer;
    private GameBoard gameBoard;
    private boolean gameOver;
    private int turnNumber;

    public GameState(GameBoard gameBoard, Player player) {
        this.gameBoard = gameBoard;
        this.currentPlayer = player;
        gameOver = false;
        turnNumber = 1;
    }

    public char getCurrentPlayerMarker() {
        return currentPlayer.getMarker();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void switchTurn(Player nextPlayer) {
        this.currentPlayer = nextPlayer;
        turnNumber++;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isDraw() {
        // if turnNumber is 10, the game is draw because it is impossible for a player to win on turn 9.
        return turnNumber == 10;
    }

    public boolean isWin() {
        // Check all rows for a win
        for (int row = 0; row < 3; row++) {
            int rowSum = gameBoard.getCell(row, 0) + gameBoard.getCell(row, 1) + gameBoard.getCell(row, 2);
            if (rowSum == -3 || rowSum == 3) {
                return true;
            }
        }

        // Check all columns for a win
        for (int col = 0; col < 3; col++) {
            int colSum = gameBoard.getCell(0, col) + gameBoard.getCell(1, col) + gameBoard.getCell(2, col);
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
