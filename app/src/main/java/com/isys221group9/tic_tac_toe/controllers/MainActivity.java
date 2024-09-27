package com.isys221group9.tic_tac_toe.controllers;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.GridLayout;

import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private LightsOutGame mGame;

    private GridLayout mLightGrid;

    private int mLightOnColor;

    private int mLightOffColor;

    private final String GAME_STATE = "gameState";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mLightGrid = findViewById(R.id.light_grid);

        for (int buttonIndex = 0; buttonIndex < mLightGrid.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) mLightGrid.getChildAt(buttonIndex);
            gridButton.setOnClickListener(this::onLightButtonClick);
        }

        Button winButton = (Button) mLightGrid.getChildAt(0);

        winButton.setOnLongClickListener(this::onLongWinButtonClick);

        mLightOnColor = ContextCompat.getColor(this, R.color.yellow);

        mLightOffColor = ContextCompat.getColor(this, R.color.black);

        mGame = new LightsOutGame();

        if (savedInstanceState == null) {
            startGame();
        }
        else {
            String gameState = savedInstanceState.getString(GAME_STATE);

            mGame.setState(gameState);

            setButtonColors();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(GAME_STATE, mGame.getState());
    }

    private void startGame() {
        mGame.newGame();

        setButtonColors();
    }

    private void onLightButtonClick(View view) {

        int buttonIndex = mLightGrid.indexOfChild(view);

        int row = buttonIndex / LightsOutGame.GRID_SIZE;

        int col = buttonIndex % LightsOutGame.GRID_SIZE;

        mGame.selectLight(row, col);

        setButtonColors();

        if (mGame.isGameOver()) {
            Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean onLongWinButtonClick(View view) {

        mGame.instantWin();

        setButtonColors();

        if (mGame.isGameOver()) {
            Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    private void setButtonColors() {

        for (int buttonIndex = 0; buttonIndex < mLightGrid.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) mLightGrid.getChildAt(buttonIndex);

            int row = buttonIndex / LightsOutGame.GRID_SIZE;

            int col = buttonIndex % LightsOutGame.GRID_SIZE;

            if (mGame.isLightOn(row, col)) {
                gridButton.setBackgroundColor(mLightOnColor);

                setButtonDescription(gridButton,true);
            } else {
                gridButton.setBackgroundColor(mLightOffColor);

                setButtonDescription(gridButton,false);
            }
        }
    }

    private void setButtonDescription(Button button, boolean lightOn) {
        if (lightOn) {
            button.setContentDescription(getString(R.string.uppercaps_on));
        } else {
            button.setContentDescription(getString(R.string.uppercaps_off));
        }
    }

    public void onNewGameClick(View view) {
        startGame();
    }


}