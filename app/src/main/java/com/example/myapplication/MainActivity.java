package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        GameState first = new GameState();
        GameState second = new GameState(first);

        first.p2Pieces[0].xCord = 5;
        first.turn = 2;
        second.p2NumPieces = 67;

        Button editText = (Button)findViewById(R.id.gameStateTest);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hello");
                Log.e("info" , "First p2 xcord = " + first.p2Pieces[0].xCord);
                Log.e("info" , "second p1 xcord = " + second.p2Pieces[0].xCord);
                Log.e("info" , "first turn = " + first.turn);
                Log.e("info" , "second turn = " + second.turn);
                Log.e("info","first.p1NumPieces = " + first.p2NumPieces);
                Log.e("info","second.p1NumPieces = " + second.p2NumPieces);

                System.out.println(first.toString());
            }
        });

    }
}