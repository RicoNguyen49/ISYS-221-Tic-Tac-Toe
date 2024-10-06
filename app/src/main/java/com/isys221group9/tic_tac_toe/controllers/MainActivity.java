package com.isys221group9.tic_tac_toe.controllers;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.isys221group9.tic_tac_toe.R;
import com.isys221group9.tic_tac_toe.models.GameState;
import com.isys221group9.tic_tac_toe.models.Player;

public class MainActivity extends AppCompatActivity {
    private GameController game;
    private GridLayout gridPlaceholder;
    private TextView currentPlayerText; // Add this line
    private final String GAME_STATE = "gameState";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        game = new GameController();
        gridPlaceholder = findViewById(R.id.ttt_grid);
        currentPlayerText = findViewById(R.id.currentPlayerText);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new_game) {
            startGame();
            return true;
        } else if (item.getItemId() == R.id.action_quit) {
            onQuitClick(null);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(GAME_STATE, game.getState());

        if (game.getState() != null) {
            System.out.println("Saving Game State: " + game.getState().toString());
        }else {
            updateCurrentPlayerText(); // Update current player after valid move
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
        updateCurrentPlayerText();
    }
    private void updateCurrentPlayerText() {
        Player currentPlayer = game.getState().getCurrentPlayer();
        String playerNameText = getString(R.string.current_player_text, currentPlayer.getName());
        currentPlayerText.setText(playerNameText);
    }

    public void onNewGameClick(View view) {
        startGame();
    }

    public void onQuitClick(View view) {
        // Create an alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit?");
        builder.setCancelable(true);

        // "Yes" button to close the app
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();  // Close the activity, and therefore the app
            }
        });

        // "No" button to return to the game
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();  // Close the dialog and return to the game
            }
        });

        // Show the alert dialog
        AlertDialog quitDialog = builder.create();
        quitDialog.show();
    }
}
