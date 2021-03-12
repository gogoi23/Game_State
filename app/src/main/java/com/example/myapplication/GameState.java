/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Gamestate class - initiates moves, captures, and pieces of the game
 *
 * CS301A
 * @version 03/11/2021
 */
package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;

public class GameState {

    // instance variables
    public Piece[] p1Pieces;//if the grid is pieces this might not be needed
    public Piece[] p2Pieces;//if the grid is pieces this might not be needed
    public int p1NumPieces;
    public int p2NumPieces;
    public int turn;
    //add grid here

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
            this.p1Pieces[i] = new Piece(current.p1Pieces[i]);
            this.p2Pieces[i] = new Piece(current.p2Pieces[i]);
        }
    }

    //checks if the given coordinates are empty
    public boolean isEmpty(int newXCord,int newYCord){
        boolean returnValue  = true;
        for(Piece piece : p1Pieces ){
            if(piece.getXcoordinate() == newXCord && piece.getYcoordinate() == newYCord
                    && piece.getAlive()){
                return false;
            }
        }
        for(Piece piece : p2Pieces ){
            if(piece.getXcoordinate() == newXCord && piece.getYcoordinate() == newYCord
                    && piece.getAlive()){
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
            if(p1Pieces[i].getAlive() == true) {
                if(p1Pieces[i].getKing() == true) {
                    returnValue = returnValue + "P1 piece " + i + " is a KING, coordinates: ("
                            + this.p1Pieces[i].getXcoordinate() + ", "
                            + this.p1Pieces[i].getYcoordinate() + ")\n";
                }
                else {
                    returnValue = returnValue + "P1 piece " + i + " is alive, coordinates: ("
                            + this.p1Pieces[i].getXcoordinate() + ", "
                            + this.p1Pieces[i].getYcoordinate() + ")\n";
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
            if(p2Pieces[j].getAlive() == true) {
                if(p2Pieces[j].getKing() == true) {
                    returnValue = returnValue + "P2 piece " + j + " is a KING, coordinates: ("
                            + this.p2Pieces[j].getXcoordinate() + ", "
                            + this.p2Pieces[j].getYcoordinate() + ")\n";
                }
                else {
                    returnValue = returnValue + "P2 piece " + j + " is alive, coordinates: ("
                            + this.p2Pieces[j].getXcoordinate() + ", "
                            + this.p2Pieces[j].getYcoordinate() + ")\n";
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

    /** Capture method. Can be used for player one and player two pieces
     * @param piece the piece doing the capturing
     * @param id  if id = 1 then it's player one's turn
     *            if id = 2 then its player two's turn
     * @param xDir xDir is used to determine the horizontal direction of the capturing piece
     *   If the conditions are met then the piece's x-coordinate is set as x-coordinate + xdir*2
     *   xDir must equal to one or negative one or this method will return false
     * @param yDir xDir and yDir are used to determine the direction of the capturing piece
     *     If the conditions are met then the piece's y-coordinate is set as y-coordinate + ydir*2
     *     yDir must equal to one or negative one or this method will return false
     * @param enemyPieces the array that targeted piece belongs to
     * @return true if capture is legal move and false otherwise
     */
    public boolean capturepiece(Piece piece,int id,Piece[] enemyPieces,int xDir,int yDir){
        //gets returned. Will be set to true if this method works.
        boolean returnValue = false;

        //checks that the player isn't trying to capture a piece that is out of range
        if(!inRange(xDir,yDir)){
            return false;
        }

        //checks if player one is trying to capture a piece behind it. If so, it makes sure it's a king
        if(id == 1 && yDir<1 && !piece.getKing()){
            return false;
        }

        //checks if player one is trying to capture a piece behind it. If so, it makes sure it's a king
        if(id == 2 && yDir>1 && !piece.getKing()){
            return false;
        }

        //runs through all the enemy pieces
        for(Piece piece1 : enemyPieces ){
            //makes sure a dead pieces isn't being captured
            if(piece.getAlive()) {
                //makes checks if any enemy pieces is in a position to be captured by piece
                if (piece.getXcoordinate() + xDir == piece1.getXcoordinate()
                        && piece.getYcoordinate() +yDir == piece1.getYcoordinate()) {
                    //makes sure the space ahead of the the capture pieces is empty
                    if (isEmpty(piece1.getXcoordinate() + xDir, piece1.getYcoordinate() + yDir)) {
                        //kills the pieces and sets the return value to true
                        piece1.setAlive(false);
                        returnValue = true;

                        //changes the turn number
                        piece.setCoordinates(piece1.getXcoordinate() + xDir, piece1.getYcoordinate() + yDir);
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

    /**
     * This is the movePiece method. It is simple to use and can
     * used on any piece in any of the 4 possible directions.
     * It checks for all invalid moves as well
     * @param piece the piece that is moved.
     * @param id if id = 1 then it's player one's turn
     *           if id = 2 then its player two's turn
     * @param xDir xDir can only equal one and negative one. Any other number will return false
     *             If the move is valid then the piece's x-coordinate is set as
     *             x-coordinate + xdir
     * @param yDir yDir can only equal one and negative one. Any other number will return false
     *             If the move is valid then the piece's y-coordinate is set as
     *             y-coordinate + ydir
     * @return true if the move is legal and false otherwise
     */
    public boolean movePiece(Piece piece, int xDir,int yDir,int id){
        //this if statement checks that the user has not tried to move more than one space
        if(inRange(xDir,yDir)){

            //this checks that user is not trying to move off the checker board as well as if the space is held by another piece
            if(inBounds(piece.getXcoordinate()+xDir,piece.getYcoordinate()+yDir) &&
                    isEmpty(piece.getXcoordinate()+xDir,piece.getYcoordinate()+yDir)){

                //this checks if player one is not trying to move a non king piece backwards
                if(id == 1 && yDir<1 && !piece.getKing()){
                    Log.e( "movePiece: ","Can't move backwards because not king" );
                    return false;
                }

                //this checks if player two is not trying to move a non king piece backwards
                else if(id == 2 && yDir>0 && !piece.getKing()){
                    Log.e( "movePiece: ","Can't move backwards because not king" );
                    return false;
                }

                //if all the conditions are right the piece will move.
                else {
                    piece.setCoordinates(piece.getXcoordinate()+xDir,piece.getYcoordinate()+yDir);

                    //will turn to player 1's pieces king if the piece reaches the other side of the board
                    if(turn == 1){
                        turn = 2;
                        if(piece.getYcoordinate() == 8){
                            piece.setKing(true);
                        }
                    }

                    //will turn to player 2's pieces king if the piece reaches the other side of the board
                    else{
                        if(piece.getYcoordinate() == 1){
                            piece.setKing(true);
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

    // prints board in logcat
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
            piece.setCoordinates(piece.xCord - 1, piece.yCord + 1);

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
            piece.setCoordinates(piece.xCord + 1,piece.yCord + 1);

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
                piece.setCoordinates(piece.xCord - 1,piece.yCord - 1);
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
                piece.setCoordinates(piece.xCord + 1,piece.yCord - 1);
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
            piece.setCoordinates(piece.xCord - 1,piece.yCord - 1);
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

            piece.setCoordinates(piece.xCord+1,piece.yCord-1);

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
                piece.setCoordinates(piece.xCord - 1, piece.yCord + 1);
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
                piece.setCoordinates(piece.xCord + 1, piece.yCord + 1);
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
