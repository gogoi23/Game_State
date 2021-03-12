/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Piece class - initializes checker pieces coordinates, if its alive, and if its a king
 *
 * CS301A
 * 03/12/2021
 */

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

    public void setCoordinates(int xCord,int yCord){
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
