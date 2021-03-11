package com.example.myapplication;

public class TestingGround {
    public static void main(String[] args){
        GameState testState = new GameState();
        Piece testPiece = new Piece(5,5);
        System.out.println(testState.inRange(1,1));
    }
}
