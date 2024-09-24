package com.zybooks.tic_tac_toe_isys_221;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity {
    private final int[][] ttt_grid;

    public GameActivity(int[][] tttGrid) {
        ttt_grid = new int[3][3];
    }


    public String getState() {
        StringBuilder boardString = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                String value = Integer.toString(ttt_grid[row][col]);
                boardString.append(value);
            }
        }
        return boardString.toString();
    }
    public void setState(String gameState) {
        int index = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if ( String. gameState.(index) == '1' ) {
                    ttt_grid[row][col] = -1;
                }
                else if ( gameState.charAt(index) == "1" ) {
                    ttt_grid[row][col] = 1;
                }
                index++;
            }
        }
    }
}