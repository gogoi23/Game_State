package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        GameState first = new GameState();
        GameState second = new GameState(first);
        GameState third = new GameState();
        GameState fourth = new GameState(third);

        first.p2Pieces[0].xCord = 5;
        first.turn = 2;
        second.p2NumPieces = 6;
        third.p2Pieces[0].xCord = 5;
        third.turn = 2;
        fourth.p2NumPieces = 6;

        EditText gameState = (EditText)findViewById(R.id.stateDescription);

        Button testRun = (Button)findViewById(R.id.gameStateTest);
        testRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameState.append("Welcome to Checkers.\n");
                gameState.append(first.toString() + "\n");
                gameState.append(second.toString() + "\n");
                gameState.append(third.toString() + "\n");
                gameState.append(fourth.toString() + "\n");
            }
        });

    }
}