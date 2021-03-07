package com.example.myapplication;

public class Piece {
    public int xCord;
    public int yCord;
    public boolean isKing;
    public boolean isAlive;

    public Piece(int xCord,int yCord){
        this.xCord = xCord;
        this.yCord = yCord;
        isAlive = true;
        isKing = false;
    }

    public Piece(int xCord,int yCord,boolean isALive,boolean isKing){
        this.xCord = xCord;
        this.yCord = yCord;
        this.isAlive = isALive;
        this.isKing = isKing;
    }


}
