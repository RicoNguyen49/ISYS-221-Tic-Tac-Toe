package com.zybooks.tic_tac_toe_isys_221;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.security.PrivateKey;


public class MainActivity extends AppCompatActivity {
    private GameActivity game;
    private GridLayout Game_Grid;
    private String GAME_STATE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Game_Grid = findViewById(R.id.ttt_grid);

        for (int buttonIndex = 0; buttonIndex < Game_Grid.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) Game_Grid.getChildAt(buttonIndex);
            gridButton.setOnClickListener(this::onLightButtonClick);
        }


        game = new GameActivity();
        if (savedInstanceState == null) {
            startGame();
        }
        else {
            String gameState = savedInstanceState.getString(GAME_STATE);
            game.setState(gameState);
            setButtonColors();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE, game.getState());
    }

    private void startGame() {
        game.newGame();
        setButtonColors();
    }

    private void onLightButtonClick(View view) {

        int buttonIndex = Game_Grid.indexOfChild(view);

        int row = buttonIndex / LightsOutGame.GRID_SIZE;
        int col = buttonIndex % LightsOutGame.GRID_SIZE;

        game.selectLight(row, col);

        setButtonColors();
        if (game.isGameOver()) {
            Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show();
        }
    }


    public void onNewGameClick(View view) {
        startGame();
    }



    ActivityResultLauncher<Intent> mColorResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            mLightOnColorId = data.getIntExtra(ColorActivity.EXTRA_COLOR, R.color.yellow);
                            mLightOnColor = ContextCompat.getColor(MainActivity.this, mLightOnColorId);
                            setButtonColors();
                        }
                    }
                }
            });
}