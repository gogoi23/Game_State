package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * MainActivity: runs the game state app
 * When the Run Test button is clicked, the game states are printed to an EditText widget
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 *
 * CS301A
 * @version 03/14/2021
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        //I made the dimensions 9 by 9 because I made set the board coordinates to be 8 by 8
        //If the array's dimensions were 8 by 8 I would get a null pointer exceptions every time
        //I tried to acess board[8][anything]
        ImageButton[][] board = new ImageButton[9][9];

        //this is will be listening to the tiles. I made it 9 by 9 for the same reason I made the board 9 by 9
        TileListener [][] boardListener = new TileListener[9][9];

        GameState gameState = new GameState();

        //this button is used to let a player unchoose a piece
        Button cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameState.pieceSelectedBoolean = false;
            }
        });

        //used to display the turn/how many pieces captured etc.
        TextView gameInfo = (TextView)findViewById(R.id.gameInfo);
        gameInfo.setText("Player one, please choose a piece. You can choose a piece by taping it.");

        //This is where I initialize all the image buttons. Their locations in the array
        //matches the location on the board
        board[1][1] = findViewById(R.id.tile11);
        board[2][1] = findViewById(R.id.tile21);
        board[3][1] = findViewById(R.id.tile31);
        board[4][1] = findViewById(R.id.tile41);
        board[5][1] = findViewById(R.id.tile51);
        board[6][1] = findViewById(R.id.tile61);
        board[7][1] = findViewById(R.id.tile71);
        board[8][1] = findViewById(R.id.tile81);

        board[1][2] = findViewById(R.id.tile12);
        board[2][2] = findViewById(R.id.tile22);
        board[3][2] = findViewById(R.id.tile32);
        board[4][2] = findViewById(R.id.tile42);
        board[5][2] = findViewById(R.id.tile52);
        board[6][2] = findViewById(R.id.tile62);
        board[7][2] = findViewById(R.id.tile72);
        board[8][2] = findViewById(R.id.tile82);

        board[1][3] = findViewById(R.id.tile13);
        board[2][3] = findViewById(R.id.tile23);
        board[3][3] = findViewById(R.id.tile33);
        board[4][3] = findViewById(R.id.tile43);
        board[5][3] = findViewById(R.id.tile53);
        board[6][3] = findViewById(R.id.tile63);
        board[7][3] = findViewById(R.id.tile73);
        board[8][3] = findViewById(R.id.tile83);

        board[1][4] = findViewById(R.id.tile14);
        board[2][4] = findViewById(R.id.tile24);
        board[3][4] = findViewById(R.id.tile34);
        board[4][4] = findViewById(R.id.tile44);
        board[5][4] = findViewById(R.id.tile54);
        board[6][4] = findViewById(R.id.tile64);
        board[7][4] = findViewById(R.id.tile74);
        board[8][4] = findViewById(R.id.tile84);

        board[1][5] = findViewById(R.id.tile15);
        board[2][5] = findViewById(R.id.tile25);
        board[3][5] = findViewById(R.id.tile35);
        board[4][5] = findViewById(R.id.tile45);
        board[5][5] = findViewById(R.id.tile55);
        board[6][5] = findViewById(R.id.tile65);
        board[7][5] = findViewById(R.id.tile75);
        board[8][5] = findViewById(R.id.tile85);

        board[1][6] = findViewById(R.id.tile16);
        board[2][6] = findViewById(R.id.tile26);
        board[3][6] = findViewById(R.id.tile36);
        board[4][6] = findViewById(R.id.tile46);
        board[5][6] = findViewById(R.id.tile56);
        board[6][6] = findViewById(R.id.tile66);
        board[7][6] = findViewById(R.id.tile76);
        board[8][6] = findViewById(R.id.tile86);

        board[1][6] = findViewById(R.id.tile16);
        board[2][6] = findViewById(R.id.tile26);
        board[3][6] = findViewById(R.id.tile36);
        board[4][6] = findViewById(R.id.tile46);
        board[5][6] = findViewById(R.id.tile56);
        board[6][6] = findViewById(R.id.tile66);
        board[7][6] = findViewById(R.id.tile76);
        board[8][6] = findViewById(R.id.tile86);

        board[1][7] = findViewById(R.id.tile17);
        board[2][7] = findViewById(R.id.tile27);
        board[3][7] = findViewById(R.id.tile37);
        board[4][7] = findViewById(R.id.tile47);
        board[5][7] = findViewById(R.id.tile57);
        board[6][7] = findViewById(R.id.tile67);
        board[7][7] = findViewById(R.id.tile77);
        board[8][7] = findViewById(R.id.tile87);

        board[1][8] = findViewById(R.id.tile18);
        board[2][8] = findViewById(R.id.tile28);
        board[3][8] = findViewById(R.id.tile38);
        board[4][8] = findViewById(R.id.tile48);
        board[5][8] = findViewById(R.id.tile58);
        board[6][8] = findViewById(R.id.tile68);
        board[7][8] = findViewById(R.id.tile78);
        board[8][8] = findViewById(R.id.tile88);

        gameState.setBoard(board);



        for(int y = 1; y<9 ; y++){
            for(int x = 1; x<9 ; x++){
                boardListener[x][y] = new TileListener(x,y,gameState,gameInfo,board);
                board[x][y].setOnClickListener(boardListener[x][y]);
            }
        }


    }



}