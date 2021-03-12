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

        String[][]board2 = new String[9][9];


        EditText gameState = (EditText)findViewById(R.id.stateDescription);

        Button testRun = (Button)findViewById(R.id.gameStateTest);
        testRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameState.append("Welcome to Checkers.\n");
                //gameState.append(first.toString() + "\n");

                //creates new gamestate
                GameState first = new GameState();

                Piece firstPiece = first.p1Pieces[10];
                //moves a piece in the first game state
                gameState.append("Player 1 attempted to move a piece from " + "("+
                        firstPiece.getXcoordinate() + "," +
                        firstPiece.getYcoordinate() + ") to (" +
                        (firstPiece.getXcoordinate()+1) + "," +
                        (firstPiece.getYcoordinate()+1) + ")");
                if(first.movePiece(firstPiece, 1, 1, 1) == true) {
                    gameState.append(". It was a success.\n");
                }
                else{
                    gameState.append(", but it didn't work.\n");
                }
                if(firstPiece.isKing == true){
                    gameState.append("The piece has now a king.\n");
                }

                GameState second = new GameState(first);
                GameState third = new GameState();
                GameState fourth = new GameState(third);

                if(second.toString().equals(fourth.toString())) {
                    gameState.append(second.toString() + "\n");
                    gameState.append(fourth.toString() + "\n");
                    printBoard(board2, first.p1Pieces, first.p2Pieces);
                }
            }
        });

    }

    public static void printBoard(String[][] board2, Piece[] P1, Piece[] P2) {
        for(int height=1;height<=8;height++) {
            for(int lenth=1; lenth<=8;lenth++) {
                board2[height][lenth]="___";
            }
        }


        if (P1[0].getAlive()==true) {
            board2[P1[0].getXcoordinate()][P1[0].getYcoordinate()]="O1_";
        }

        if (P1[1].getAlive()==true) {
            board2[P1[1].getXcoordinate()][P1[1].getYcoordinate()]="O2_";
        }
        if (P1[2].getAlive()==true) {
            board2[P1[2].getXcoordinate()][P1[2].getYcoordinate()]="O3_";
        }
        if (P1[3].getAlive()==true) {
            board2[P1[3].getXcoordinate()][P1[3].getYcoordinate()]="O4_";
        }
        if (P1[4].getAlive()==true) {
            board2[P1[4].getXcoordinate()][P1[4].getYcoordinate()]="O5_";
        }
        if (P1[5].getAlive()==true) {
            board2[P1[5].getXcoordinate()][P1[5].getYcoordinate()]="O6_";
        }
        if (P1[6].getAlive()==true) {
            board2[P1[6].getXcoordinate()][P1[6].getYcoordinate()]="O7_";
        }
        if (P1[7].getAlive()==true) {
            board2[P1[7].getXcoordinate()][P1[7].getYcoordinate()]="O8_";
        }
        if (P1[8].getAlive()==true) {
            board2[P1[8].getXcoordinate()][P1[8].getYcoordinate()]="O9_";
        }
        if (P1[9].getAlive()==true) {
            board2[P1[9].getXcoordinate()][P1[9].getYcoordinate()]="O10";
        }
        if (P1[10].getAlive()==true) {
            board2[P1[10].getXcoordinate()][P1[10].getYcoordinate()]="O11";
        }
        if (P1[11].getAlive()==true) {
            board2[P1[11].getXcoordinate()][P1[11].getYcoordinate()]="O12";
        }
        if (P2[0].getAlive()==true) {
            board2[P2[0].getXcoordinate()][P2[0].getYcoordinate()]="T1_";
        }
        if (P2[1].getAlive()==true) {
            board2[P2[1].getXcoordinate()][P2[1].getYcoordinate()]="T2_";
        }
        if (P2[2].getAlive()==true) {
            board2[P2[2].getXcoordinate()][P2[2].getYcoordinate()]="T3_";
        }
        if (P2[3].getAlive()==true) {

            board2[P2[3].getXcoordinate()][P2[3].getYcoordinate()]="T4_";
        }
        if (P2[4].getAlive()==true) {
            board2[P2[4].getXcoordinate()][P2[4].getYcoordinate()]="T5_";
        }
        if (P2[5].getAlive()==true) {
            board2[P2[5].getXcoordinate()][P2[5].getYcoordinate()]="T6_";
        }
        if (P2[6].getAlive()==true) {
            board2[P2[6].getXcoordinate()][P2[6].getYcoordinate()]="T7_";
        }
        if (P2[7].getAlive()==true) {
            board2[P2[7].getXcoordinate()][P2[7].getYcoordinate()]="T8_";
        }
        if (P2[8].getAlive()==true) {
            board2[P2[8].getXcoordinate()][P2[8].getYcoordinate()]="T9_";
        }
        if (P2[9].getAlive()==true) {
            board2[P2[9].getXcoordinate()][P2[9].getYcoordinate()]="T10";
        }
        if (P2[10].getAlive()==true) {
            board2[P2[10].getXcoordinate()][P2[10].getYcoordinate()]="T11";
        }
        if (P2[11].getAlive()==true) {
            board2[P2[11].getXcoordinate()][P2[11].getYcoordinate()]="T12";
        }


        Log.e( "printBoard: ", "_________________________________" );
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][8]+"|"+board2[2][8]+"|"+board2[3][8]+"|"+board2[4][8]+"|"+board2[5][8]+"|"+board2[6][8]+"|"+board2[7][8]+"|"+board2[8][8]+"|"+8);
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][7]+"|"+board2[2][7]+"|"+board2[3][7]+"|"+board2[4][7]+"|"+board2[5][7]+"|"+board2[6][7]+"|"+board2[7][7]+"|"+board2[8][7]+"|"+7);
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][6]+"|"+board2[2][6]+"|"+board2[3][6]+"|"+board2[4][6]+"|"+board2[5][6]+"|"+board2[6][6]+"|"+board2[7][6]+"|"+board2[8][6]+"|"+6);
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][5]+"|"+board2[2][5]+"|"+board2[3][5]+"|"+board2[4][5]+"|"+board2[5][5]+"|"+board2[6][5]+"|"+board2[7][5]+"|"+board2[8][5]+"|"+5);
        Log.e( "printBoard: ", "\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][4]+"|"+board2[2][4]+"|"+board2[3][4]+"|"+board2[4][4]+"|"+board2[5][4]+"|"+board2[6][4]+"|"+board2[7][4]+"|"+board2[8][4]+"|"+4);
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][3]+"|"+board2[2][3]+"|"+board2[3][3]+"|"+board2[4][3]+"|"+board2[5][3]+"|"+board2[6][3]+"|"+board2[7][3]+"|"+board2[8][3]+"|"+3);
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][2]+"|"+board2[2][2]+"|"+board2[3][2]+"|"+board2[4][2]+"|"+board2[5][2]+"|"+board2[6][2]+"|"+board2[7][2]+"|"+board2[8][2]+"|"+2);
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][1]+"|"+board2[2][1]+"|"+board2[3][1]+"|"+board2[4][1]+"|"+board2[5][1]+"|"+board2[6][1]+"|"+board2[7][1]+"|"+board2[8][1]+"|"+1);
        Log.e( "printBoard: ","\n  1   2   3   4   5   6   7   8");


    }




}