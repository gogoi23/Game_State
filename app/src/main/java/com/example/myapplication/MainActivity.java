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

        first.p2Pieces[0].xCord = 5;
        first.turn = 4;
        second.p2NumPieces = 67;

        EditText gameState = (EditText)findViewById(R.id.stateDescription);

        Button testRun = (Button)findViewById(R.id.gameStateTest);
        testRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameState.append("Welcome to Checkers.\n");
                gameState.append("First p2 xcord = " + first.p2Pieces[0].xCord + "\n");
                gameState.append("second p1 xcord = " + second.p2Pieces[0].xCord + "\n");
                gameState.append( "first turn = " + first.turn + "\n");
                gameState.append("second turn = " + second.turn + "\n");
                gameState.append("first.p1NumPieces = " + first.p2NumPieces + "\n");
                gameState.append("second.p1NumPieces = " + second.p2NumPieces + "\n");
            }
        });

    }
}