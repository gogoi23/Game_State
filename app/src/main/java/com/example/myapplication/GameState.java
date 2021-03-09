
package com.example.myapplication;

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
        for(int i = 0; i < 12; i++){
            int newXCord1 = current.p1Pieces[i].xCord;
            int newYCord1 = current.p1Pieces[i].yCord;
            boolean newLife1 = current.p1Pieces[i].isAlive;
            boolean newKing1 = current.p1Pieces[i].isKing;

            this.p1Pieces[i] = new Piece(newXCord1,newYCord1,newLife1,newKing1);

            int newXCord2 = current.p2Pieces[i].xCord;
            int newYCord2 = current.p2Pieces[i].yCord;
            boolean newLife2 = current.p2Pieces[i].isAlive;
            boolean newKing2 = current.p2Pieces[i].isKing;
            this.p2Pieces[i] = new Piece(newXCord2,newYCord2,newLife2,newKing2);

        }
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

}
