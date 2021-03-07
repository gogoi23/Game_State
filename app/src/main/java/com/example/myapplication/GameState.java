package com.example.myapplication;

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

        this.p1Pieces = current.p1Pieces;
        this.p2Pieces = current.p2Pieces;

        this.p1NumPieces = current.p1NumPieces;
        this.p2NumPieces = current.p2NumPieces;
        this.turn = current.turn;

        /*for(int i = 0;i<12;i++){
            int newxCord1 = current.p1Pieces[i].xCord;
            int newyCord1 = current.p1Pieces[i].yCord;
            boolean newLife1 = current.p1Pieces[i].isAlive;
            boolean newKing1 = current.p1Pieces[i].isKing;

            this.p1Pieces[i] = new Piece(newxCord1,newyCord1,newLife1,newKing1);

            int newxCord2 = current.p2Pieces[i].xCord;
            int newyCord2 = current.p2Pieces[i].yCord;
            boolean newLife2 = current.p2Pieces[i].isAlive;
            boolean newKing2 = current.p2Pieces[i].isKing;
            this.p2Pieces[i] = new Piece(newxCord2,newyCord2,newLife2,newKing2);


        }*/
    }



}
