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
}
