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

    public void setCordinates(int xCord,int yCord){
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public String toString(){
        String returnValue = "";
        returnValue = returnValue+"Xcord = " + this.xCord;
        returnValue = returnValue + "\nYcord = " + this.yCord;
        returnValue = returnValue + "\nIs Alive = " + this.isAlive;
        returnValue = returnValue + "\nIs King = " + this.isKing;
        return returnValue;
    }

    public int getXcoordinate(){
        return this.xCord;
    }

    public int getYcoordinate(){
        return this.yCord;
    }

    public boolean getAlive(){
        return isAlive;
    }


}
