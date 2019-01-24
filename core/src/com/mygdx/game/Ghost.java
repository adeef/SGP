package com.mygdx.game;

public class Ghost {
    private int x = 336;
    private int y = 372;
    private String state;
    
    private int Ax;//position in array
    private int Ay;
    private String color;

    private int[][] level = {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 5, 4, 0, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 2, 2, 2, 2, 4, 4, 3, 2, 2, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 2, 2, 2, 2, 2, 3, 2, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 4, 4, 2, 2, 2, 2, 4, 4, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 5, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
            {4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 2, 2, 2, 4, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 2, 2, 2, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 0, 4, 0, 0, 0, 4, 0, 4, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 2, 2, 2, 4, 4, 2, 2, 2, 2, 4, 4, 0, 4, 0, 0, 0, 4, 0, 0, 0, 2, 4, 4, 2, 2, 2, 2, 2, 4},
            {4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 0, 0, 0, 5, 0, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4},
            {4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 0, 0, 0, 5, 0, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4},
            {4, 2, 2, 2, 2, 4, 4, 2, 2, 2, 2, 4, 4, 0, 4, 0, 0, 0, 4, 0, 0, 0, 2, 4, 4, 2, 2, 2, 2, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 0, 4, 0, 0, 0, 4, 0, 4, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 2, 2, 2, 4, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 2, 2, 2, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 2, 2, 2, 4, 4, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 2, 2, 2, 4, 4, 3, 2, 2, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 2, 2, 2, 2, 2, 3, 2, 2, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
    };

    public String color(){
        return color;
    }
}
