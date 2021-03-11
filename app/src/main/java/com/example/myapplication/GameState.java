
package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;

public class GameState {

    // instance variables
    public Piece[] p1Pieces;
    public Piece[] p2Pieces;
    public int p1NumPieces;
    public int p2NumPieces;
    public int turn;

    public GameState(){
        turn = 1;
        p1NumPieces = 12;
        p2NumPieces = 12;

        // p1 starting coordinates
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

        // p2 starting coordinates
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

    //deep copy constructor of GameState
    public GameState (GameState current){
        //creates new arrays
        this.p1Pieces = new Piece[12];
        this.p2Pieces = new Piece[12];

        //copies number of pieces for each player
        this.p1NumPieces = current.p1NumPieces;
        this.p2NumPieces = current.p2NumPieces;

        this.turn = current.turn; //copies number indicating whose turn it is

        //storing the current pieces in the new arrays
        for(int i = 0;i<12;i++){

            this.p1Pieces[i] = new Piece(current.p1Pieces[i].xCord,current.p1Pieces[i].yCord,
            current.p1Pieces[i].isAlive,current.p1Pieces[i].isKing);


            this.p2Pieces[i] = new Piece(current.p2Pieces[i].xCord,current.p2Pieces[i].yCord,
            current.p2Pieces[i].isAlive,current.p2Pieces[i].isKing);

        }
    }

    //checks if the given coordinates are empty
    public boolean isEmpty(int newXCord,int newYCord){
        boolean returnValue  = true;
        for(Piece piece : p1Pieces ){
            if(piece.xCord == newXCord && piece.yCord == newYCord && piece.isAlive){
                return false;
            }
        }
        for(Piece piece : p2Pieces ){
            if(piece.xCord == newXCord && piece.yCord == newYCord && piece.isAlive){
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

    // states games status
    @Override
    public String toString() {

        String returnValue = "";

        // player 1 status
        int p1count = 0;
        for(int i = 0; i < p1Pieces.length; i++) {
            if(p1Pieces[i].isAlive == true) {
                if(p1Pieces[i].isKing == true) {
                    returnValue = returnValue + "P1 piece " + i + " is a KING, coordinates: (" + this.p1Pieces[i].xCord + ", " + this.p1Pieces[i].yCord + ")\n";
                }
                else {
                    returnValue = returnValue + "P1 piece " + i + " is alive, coordinates: (" + this.p1Pieces[i].xCord + ", " + this.p1Pieces[i].yCord + ")\n";
                }
                p1count++;
            }
            else {
                returnValue = returnValue + "P1 piece " + i + " has been captured.\n";
            }
        }
        returnValue = returnValue + "P1 has " + p1count + " pieces remaining\n";

        // player 2 status
        int p2count = 0;
        for(int j = 0; j < p2Pieces.length; j++) {
            if(p2Pieces[j].isAlive == true) {
                if(p2Pieces[j].isKing == true) {
                    returnValue = returnValue + "P2 piece " + j + " is a KING, coordinates: (" + this.p2Pieces[j].xCord + ", " + this.p2Pieces[j].yCord + ")\n";
                }
                else {
                    returnValue = returnValue + "P2 piece " + j + " is alive, coordinates: (" + this.p2Pieces[j].xCord + ", " + this.p2Pieces[j].yCord + ")\n";
                }
                p2count++;
            }
            else {
                returnValue = returnValue + "P2 piece " + j + " has been captured.\n";
            }
        }
        returnValue = returnValue + "P2 has " + p2count + " pieces remaining\n";

        // determine whose turn it is
        if(turn == 1) {
            returnValue = returnValue + "P1 turn.\n";
        }
        else if (turn == 2){
            returnValue = returnValue + "P2 turn.\n";
        }

        return returnValue;
    }

    /*Capture method. Can be used for player one and player's two pieces
    * Piece is the piece doing the capturing
    *if id = 1 then it's player one's turn
    *if id = 2 then its player two's turn
    * xDir and yDir are used to determin the direction of the piece being capture
    * If the conditions are met then the pieces x-coordinate is set as x-coordinate+xdire*2
    * and same for the y-coordinate
    * xDire and yDire must equal to one or negative on or this method will return false
    * enemyPieces are array that targeted piece belongs to
    */
    public boolean capturepiece(Piece piece,int id,Piece[] enemyPieces,int xDir,int yDir){
        //gets returned. Will be set to true if this method works.
        boolean returnValue = false;

        //checks that the player isn't trying to capture a piece that is out of range
        if(!inRange(xDir,yDir)){
            return false;
        }

        //checks if player one is trying to capture a piece behind it. If it is it makes sure it's a king
        if(id == 1 && yDir<1 && !piece.isKing){
            return false;
        }

        //checks if player one is trying to capture a piece behind it. If it is it makes sure it's a king
        if(id == 2 && yDir>1 && !piece.isKing){
            return false;
        }

        //runs through all the enemy pieces
        for(Piece piece1 : enemyPieces ){
            //makes sure a dead pieces isn't being captured
            if(piece.isAlive) {
                //makes checks if any enemy pieces is in a position to be captured by piece
                if (piece.xCord + xDir == piece1.xCord && piece.yCord +yDir == piece1.yCord) {
                    //makes sure the space ahead of the the capture pieces is empty
                    if (isEmpty(piece1.xCord + xDir, piece1.yCord + yDir)) {
                        //kills the pieces and sets the return value to true
                        piece1.isAlive = false;
                        returnValue = true;

                        //changes the turn number
                        piece.setCordinates(piece1.xCord + xDir, piece1.yCord + yDir);
                        if(turn == 1){
                            turn = 2;
                        }
                        else{
                            turn = 1;
                        }
                    }
                }
            }
        }

        return returnValue;
    }

    //this checks if the move is only moving diagonal one space
    public boolean inRange(int xDir,int yDir){
        if((xDir == 1 || xDir == -1) && (yDir == 1 || yDir == -1)){
            return true;
        }
        else{
            return false;
        }

    }

    /*
    *this is the second movePiece method. It is much simpler to use and can
    *used on any piece in any of the 4 possible directions. It checks for all invalid moves as well
    *if id = 1 then it's player one's turn
    *if id = 2 then its player two's turn
    *xDir and yDir can only equal one and negative one. Any other number will return false
    * piece is the pieces that is moved. If the move is valid then the pieces x-coordinate is set as x-coordinate+xdire
    * and same for the y - coordinate
    */
    public boolean movePiece(Piece piece,int xDir,int yDir,int id){
        //this if statement checks that the user has not tried to move more than one space
        if(inRange(xDir,yDir)){

            //this checks that user is not trying to move off the checker board as well as if the space is held by another piece
            if(inBounds(piece.xCord+xDir,piece.yCord+yDir) && isEmpty(piece.xCord+xDir,piece.yCord+yDir)){

                //this checks if player one is not trying to move a non king piece backwards
                if(id == 1 && yDir<1 && !piece.isKing){
                    Log.e( "movePiece: ","Can't move backwards because not king" );
                    return false;
                }

                //this checks if player two is not trying to move a non king piece backwards
                else if(id == 2 && yDir>0 && !piece.isKing){
                    Log.e( "movePiece: ","Can't move backwards because not king" );
                    return false;
                }

                //if all the conditions are right the piece will move.
                else {
                    piece.setCordinates(piece.xCord+xDir,piece.yCord+yDir);

                    //will turn to player 1's pieces king if the piece reaches the other side of the board
                    if(turn == 1){
                        turn = 2;
                        if(piece.yCord == 8){
                            piece.isKing = true;
                        }
                    }

                    //will turn to player 2's pieces king if the piece reaches the other side of the board
                    else{
                        if(piece.yCord == 1){
                            piece.isKing = true;
                        }
                        turn = 1;
                    }
                    return true;
                }
            }

            //returns false if the move is out of bounds or tries to move into a spot with another piece
            else {
                Log.e( "movePiece: ","not in bounds" );
                return false;
            }
        }
        //will return false if the player tries to move
        else {
            Log.e( "movePiece: ","not in range" );
            return false;
        }

    }

}

/*
    //these are previous move methods. I had 8 different move methods.
    // one for move right forward player1, move left forward player1 etc.
    //each move method only works on player ones or player two's pieces. And they can only move in one direction
    //it was really complicated and hard to work with.
    //I also felt like they were very hard to explain to my teamates.
    //I don't want to delete it because we might need it later
    public boolean movePiece(int id,boolean forward,boolean left, Piece piece){
        boolean returnValue = false;
        if(id == 1){
            if(left){
                if(forward){
                    return moveLeftForwardP1(piece);
                }
                else{
                    return moveLeftBackwardsP1(piece);
                }
            }
            else{
                if(forward){
                    return moveRightForwardP1(piece);
                }
                else{
                    return moveRightBackwardsP1(piece);
                }
            }

        }

        else{
            if(left){
                if(forward){
                    return moveLeftForwardsP2(piece);
                }
                else{
                    return moveLeftBackwardsP2(piece);
                }
            }
            else{
                if(forward){
                    return moveRightForwardsP2(piece);
                }
                else{
                    return moverightBackwardsP2(piece);
                }
            }
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
 */
