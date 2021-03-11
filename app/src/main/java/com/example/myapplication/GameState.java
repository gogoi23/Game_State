package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;

public class GameState {
    public Piece[] p1Pieces;
    public Piece[] p2Pieces;
    public int p1NumPieces;
    public int p2NumPieces;
    public int turn;

    public GameState(){
        turn = 1;
        p1NumPieces = 12;
        p2NumPieces = 12;

        p1Pieces = new Piece[12];


        p1Pieces[0] = new Piece(1,1);
        p1Pieces[1] = new Piece(3,1);
        p1Pieces[2] = new Piece(5,1);
        p1Pieces[3] = new Piece(7,1);
        p1Pieces[4] = new Piece(2,2);
        p1Pieces[5] = new Piece(4,2);
        p1Pieces[6] = new Piece(6,2);
        p1Pieces[7] = new Piece(8,2);
        p1Pieces[8] = new Piece(7,3);
        p1Pieces[9] = new Piece(5,3);
        p1Pieces[10] = new Piece(3,3);
        p1Pieces[11] = new Piece(1,3);

        p2Pieces = new Piece[12];
        p2Pieces[0] = new Piece(2,6);
        p2Pieces[1] = new Piece(4,6);
        p2Pieces[2] = new Piece(6,6);
        p2Pieces[3]= new Piece(8,6);
        p2Pieces[4] = new Piece(1,7);
        p2Pieces[5] = new Piece(3,7);
        p2Pieces[6] = new Piece(5,7);
        p2Pieces[7] = new Piece(7,7);
        p2Pieces[8] = new Piece(2,8);
        p2Pieces[9] = new Piece(4,8);
        p2Pieces[10] = new Piece(6,8);
        p2Pieces[11] = new Piece(8,8);



    }

    public GameState (GameState current){
        this.p1Pieces = new Piece[12];
        this.p2Pieces = new Piece[12];

        //this.p1Pieces = current.p1Pieces;
       // this.p2Pieces = current.p2Pieces;

        this.p1NumPieces = current.p1NumPieces;
        this.p2NumPieces = current.p2NumPieces;
        this.turn = current.turn;

        for(int i = 0;i<12;i++){

            this.p1Pieces[i] = new Piece(current.p1Pieces[i].xCord,current.p1Pieces[i].yCord,
            current.p1Pieces[i].isAlive,current.p1Pieces[i].isKing);


            this.p2Pieces[i] = new Piece(current.p2Pieces[i].xCord,current.p2Pieces[i].yCord,
            current.p2Pieces[i].isAlive,current.p2Pieces[i].isKing);

        }
    }

    //moves player ones pieces left and forward
    //works on any pieces that is player ones
    //DO NOT USE ON PLAYER TWO'S PIECES
    public boolean moveLeftForwardP1(Piece piece){
        //prints the pieces before the move
        Log.e("move", "\nbefore" + piece);

        //if statement checks if the piece can move to the desired spot
        //is it in bound. Is it empty
        if (inBounds(piece.xCord - 1, piece.yCord + 1) &&
        isEmpty(piece.xCord - 1, piece.yCord + 1)) {

            //sets the coordinates
            piece.setCordinates(piece.xCord - 1, piece.yCord + 1);

            //makes it a king if it reaches the end of the baord
            if (piece.yCord == 8) {
                piece.isKing = true;
            }

            //prints the updated info
            Log.e("move", piece.toString());
            //changes to player two's turn
            this.turn = 2;
            return true;
        }
        else {
            Log.e("move", "moving left forward is not valid ");
            return false;
        }

    }

    //use to move player one's piece right and forward
    //works on any pieces that is player ones
    //DO NOT USE ON PLAYER TWO'S PIECES
    //works pretty much the same as moveLeftForwardP1 except it moves right and forward
    //for an explanation look at the comments in moveLeftForwardP1.
    public boolean moveRightForwardP1(Piece piece){

        Log.e("move", "\nbefore" + piece);
        if (inBounds(piece.xCord + 1, piece.yCord + 1) &&
        isEmpty(piece.xCord + 1, piece.yCord + 1)) {
            piece.setCordinates(piece.xCord + 1,piece.yCord + 1);

            if(piece.yCord == 8){
                piece.isKing = true;
            }

            Log.e("move", piece.toString());
            this.turn = 2;
            return true;
        } else {
            Log.e("move", "moving right forward not valid move");
            return false;
        }

    }

    //this moves player ones piece backwards
    public boolean moveLeftBackwardsP1(Piece piece){
        Log.e("move", "\nbefore" + piece);
        if (!piece.isKing) {
            Log.e("move","Moving backwards isn't an option because this piece is not a king");
            return false;
        }
        else{
            if (inBounds(piece.xCord - 1, piece.yCord - 1)&&
            isEmpty(piece.xCord - 1, piece.yCord - 1)) {
                piece.setCordinates(piece.xCord - 1,piece.yCord - 1);
                Log.e("move", piece.toString());
                this.turn = 2;
                return true;
            }
            else {
                Log.e("move", "moving left backwards is not valid move");
                return false;
            }
        }
    }

    public boolean moveRightBackwardsP1(Piece piece){
        Log.e("move", "\nbefore" + piece);
        if (!piece.isKing) {
            Log.e("move","Moving backwards isn't an option because this piece is not a king");
            return false;
        }
        else {
            if (inBounds(piece.xCord + 1, piece.yCord - 1) &&
            isEmpty(piece.xCord + 1, piece.yCord - 1)) {
                piece.setCordinates(piece.xCord + 1,piece.yCord - 1);
                Log.e("move", piece.toString());
                this.turn = 2;
                return true;
            } else {

                Log.e("move", "moving right backwards not valid move");
                return false;
            }

        }
    }


    public boolean moveLeftBackwardsP2(Piece piece){
        Log.e("move", "\nbefore" + piece);
        Log.e("move", "Player 2");
        if (inBounds(piece.xCord - 1, piece.yCord - 1) &&
        isEmpty(piece.xCord - 1, piece.yCord - 1)) {
            this.turn = 1;
            piece.setCordinates(piece.xCord - 1,piece.yCord - 1);
            if (piece.yCord == 1) {
                piece.isKing = true;
            }
            Log.e("move", "\nafter" + piece);
            return true;
        } else {
            Log.e("move", "moving left backwards is not valid move");
            return false;
        }


    }

    public boolean moverightBackwardsP2(Piece piece){
        Log.e("move", "\nbefore" + piece);
        Log.e("move", "Player 2");
        if (inBounds(piece.xCord + 1, piece.yCord - 1)&&
        isEmpty(piece.xCord + 1, piece.yCord - 1)) {
            turn = 1;
            piece.setCordinates(piece.xCord+1,piece.yCord-1);

            if (piece.yCord == 1) {
                piece.isKing = true;
            }

            Log.e("move", "\nafter" + piece);
            this.turn = 1;
            return true;
        } else {
            Log.e("move", "moving right backwards not valid move");
            return false;
        }
    }

    public boolean moveLeftForwardsP2(Piece piece){
        Log.e("move", "\n before"+piece.toString());
        if (!piece.isKing) {
            Log.e("move","Moving forward isn't an option because this piece is not a king");
            return false;
        }
        else{

            if (inBounds(piece.xCord - 1, piece.yCord + 1) &&
            isEmpty(piece.xCord - 1, piece.yCord + 1)) {
                piece.setCordinates(piece.xCord - 1, piece.yCord + 1);
                Log.e("move", "\n after"+piece.toString());
                this.turn = 1;
                return true;
            } else {
                Log.e("move", "moving left forwards is not valid move");
                return false;
            }

        }
    }

    public boolean moveRightForwardsP2(Piece piece){
        Log.e("move", "\n before"+piece.toString());
        if (!piece.isKing) {
            Log.e("move","Moving forward isn't an option because this piece is not a king");
            return false;
        }
        else{

            if (inBounds(piece.xCord + 1, piece.yCord + 1) &&
            isEmpty(piece.xCord + 1,piece.yCord+1)) {
                piece.setCordinates(piece.xCord + 1, piece.yCord + 1);
                Log.e("move", "\n after"+piece.toString());
                this.turn = 1;
                return true;
            } else {
                Log.e("move", "moving left forwards is not valid move");
                return false;
            }

        }
    }

    //checks if the given coordinates are empty
    public boolean isEmpty(int newXCord,int newYCord){
        boolean returnValue  = true;
        for(Piece piece : p1Pieces ){
            if(piece.xCord == newXCord && piece.yCord == newYCord){
                return false;
            }
        }
        for(Piece piece : p2Pieces ){
            if(piece.xCord == newXCord && piece.yCord == newYCord){
                return false;
            }
        }
        return returnValue;
    }

    //checks to see if the given coordinates are in bounds
    public boolean inBounds(int newXCord,int newYCord){
        boolean valid = true;

        if(newXCord>8 || newYCord>8 || newXCord<1 || newYCord<1){
            valid = false;
        }

        return valid;

    }



}
