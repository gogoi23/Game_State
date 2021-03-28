package com.example.myapplication;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class TileListener implements View.OnClickListener{

    int xCord;//the xcord of the button its litenting to
    int yCord;//the xcord of the button its litenting to
    GameState gameState;//the current game state
    TextView gameInfo;//the text view that displays who's turn it is
    ImageButton[][] board;

    //initial constructor
    public TileListener(int xCord, int yCord, GameState gameState,TextView gameInfo,ImageButton[][] board){
        this.xCord = xCord;
        this.yCord = yCord;
        this.gameState = gameState;
        this.gameInfo = gameInfo;
        this.board = board;
    }

    //this programs what actually happens when a button is clicked
    @Override
    public void onClick(View v) {
        //this if statement is to choose a piece to move
        if(gameState.pieceSelectedBoolean == false){
            //this checks if they chose an empty spot
            if(gameState.isEmpty(xCord,yCord)){
                gameInfo.setText("This tile is empty" );
            }
            else {
                //this checks if the piece belongs to the player
                if (gameState.hasEnemyPieces(xCord,yCord)) {
                    gameInfo.setText("This piece is not yours" );
                }

                //if all the conditions are right the piece is chosen
                else{
                    gameInfo.setText("This piece can be moved. Click on the spot where you want to move it." );
                    gameState.setPieceSelectedPieceAndPieceSelectedBoolean(xCord,yCord);

                }

            }

        }

        //if a piece has been chosen
        else{
            //these are the distances of where the piece will move to. If they are
            //invalid it will not do anything
            int newXCord = xCord-gameState.pieceSelectedPiece.getXcoordinate();
            int newYcord = yCord - gameState.pieceSelectedPiece.getYcoordinate();

            //if the player is trying to move and not capture
            if(!gameState.hasEnemyPieces(xCord,yCord)){

                //if the location is valid it moves
                if(gameState.movePiece(gameState.pieceSelectedPiece,newXCord,newYcord,gameState.turn)){
                    gameState.pieceSelectedBoolean = false;//sets the piece selected back to false
                    gameState.setBoard(board);
                    gameInfo.setText("That move was valid. Player two please choose a piece" );

                }
                //Prints an error message if they can't move
                else{
                    gameInfo.setText("This move is invalid" );

                }

            }
            //if they are trying to capture a piece
            else{

                //player 1 capturing a piece
                if(gameState.turn == 1) {
                    if (gameState.capturepiece(gameState.pieceSelectedPiece, gameState.turn,
                            gameState.p2Pieces, newXCord, newYcord)) {
                        gameInfo.setText("You have captured a piece");
                        gameState.setBoard(board);
                        gameState.pieceSelectedBoolean = false;
                    }
                    //prints an error message if they are trying to capture a piece that is not valid.
                    else{
                        gameInfo.setText("You can not capture this piece." );
                    }
                }

                //player 2 capturing a piece
                if(gameState.turn == 2){
                    if (gameState.capturepiece(gameState.pieceSelectedPiece, gameState.turn,
                            gameState.p1Pieces, newXCord, newYcord)) {
                        gameInfo.setText("You have captured a piece");
                        gameState.setBoard(board);
                        gameState.pieceSelectedBoolean = false;
                    }
                    //prints an error message if they are trying to capture a piece that is not valid.
                    else{
                        gameInfo.setText("You can not capture this piece." );
                    }
                }



            }
        }
    }
}
