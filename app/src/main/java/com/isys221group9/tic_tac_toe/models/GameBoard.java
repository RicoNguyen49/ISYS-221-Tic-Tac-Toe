package com.isys221group9.tic_tac_toe.models;

// GameBoard Class
public class GameBoard {
    // 2 dimensional array to store the game board
    private final int[][] gameBoard;

    // Constructor
    public GameBoard() {
        // Game board with 3 by 3 grid. No expectation to change game board size
        gameBoard = new int[3][3];
    }

    // Method to set the value of a game board cell.
    public void markCell(int row, int column, int value) {
        this.gameBoard[row][column] = value;
    }

    // Method to get the value of a game board cell.
    public int getCell(int row, int column ) {
        return gameBoard[row][column];
    }

    // Method to clear the game board.
    public void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                gameBoard[row][column] = 0;
            }
        }
    }
}
