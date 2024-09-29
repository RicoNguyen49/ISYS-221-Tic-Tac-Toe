package com.isys221group9.tic_tac_toe.controllers;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.GridLayout;

import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import com.isys221group9.tic_tac_toe.R;
import com.isys221group9.tic_tac_toe.models.GameState;

public class MainActivity extends AppCompatActivity {
    private GameController game;
    private GridLayout gridPlaceholder;
    private final String GAME_STATE = "gameState";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new GameController();

        gridPlaceholder = findViewById(R.id.ttt_grid);

        for (int buttonIndex = 0; buttonIndex < gridPlaceholder.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) gridPlaceholder.getChildAt(buttonIndex);
            gridButton.setOnClickListener(this::onCellClick);
        }

        if (savedInstanceState == null) {
            startGame();
        }
        else {
            GameState gameState = (GameState) savedInstanceState.getSerializable(GAME_STATE);
            game.setState(gameState);

            System.out.println("Saving Game State: " + gameState);

            updateUI();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(GAME_STATE, game.getState());

        if (game.getState() != null) {
            System.out.println("Saving Game State: " + game.getState().toString());
        }
    }

    private void startGame() {
        game.newGame();
        updateUI();
    }

    private void onCellClick(View view) {
        // game is already over so do nothing.
        if (game.isGameOver()) {
            return;
        }

        int buttonIndex = gridPlaceholder.indexOfChild(view);
        int row = buttonIndex / 3;
        int col = buttonIndex % 3;

        game.handleMove(row, col);
        updateUI();
        if (game.isGameOver()) {
            Toast.makeText(this, game.getGameOverMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI() {

        for (int buttonIndex = 0; buttonIndex < gridPlaceholder.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) gridPlaceholder.getChildAt(buttonIndex);
            int row = buttonIndex / 3;
            int column = buttonIndex % 3;

            if (game.getBoardCell(row, column) == -1) {
                gridButton.setText("X");
            } else if (game.getBoardCell(row, column) == 1) {
                gridButton.setText("O");
            } else {
                gridButton.setText("");
            }
        }
    }

    public void onNewGameClick(View view) {
        startGame();
    }
}