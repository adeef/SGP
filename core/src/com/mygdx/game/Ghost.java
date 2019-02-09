package com.mygdx.game;

import java.util.ArrayList;


public class Ghost {
    private int x = 48;
    private int y = 24;
    private String state = "right";
    private int prevAx;
    private int prevAy;
    
    
    private String color;
    
    public Ghost(String color){
        
        this.color = color;
    }
    private int[][] level = {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 6, 2, 2, 6, 4, 4, 6, 2, 2, 6, 4, 0, 0, 0, 4, 0, 4, 7, 0, 0, 4, 6, 2, 2, 6, 2, 6, 2, 6, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 4, 4, 6, 2, 2, 6, 4, 4, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 4, 4, 6, 2, 2, 6, 2, 2, 6, 2, 2, 2, 2, 2, 6, 2, 2, 2, 2, 2, 6, 2, 2, 6, 2, 2, 2, 6, 4},
            {4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 6, 2, 2, 6, 4, 4, 6, 0, 0, 6, 0, 0, 6, 0, 0, 6, 4, 4, 6, 2, 2, 6, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 0, 4, 0, 0, 0, 4, 0, 4, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 6, 2, 2, 6, 4, 4, 6, 2, 2, 6, 4, 4, 0, 4, 0, 0, 0, 4, 6, 0, 0, 6, 4, 4, 6, 2, 2, 2, 6, 4},
            {4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 0, 0, 0, 5, 0, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4},
            {4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 0, 0, 0, 5, 0, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4},
            {4, 6, 2, 2, 6, 4, 4, 6, 2, 2, 6, 4, 4, 0, 4, 0, 0, 0, 4, 6, 0, 0, 6, 4, 4, 6, 2, 2, 2, 6, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 0, 4, 0, 0, 0, 4, 0, 4, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 6, 2, 2, 6, 4, 4, 6, 0, 0, 6, 0, 0, 6, 0, 0, 6, 4, 4, 6, 2, 2, 6, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 4, 4, 6, 2, 2, 6, 2, 2, 6, 2, 2, 2, 2, 2, 6, 2, 2, 2, 2, 2, 6, 2, 2, 6, 2, 2, 2, 6, 4},
            {4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 6, 2, 2, 6, 4, 4, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 6, 2, 2, 6, 4, 4, 6, 2, 2, 6, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 6, 2, 2, 6, 2, 6, 2, 6, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
    };

    
    public int[] move(){
        int[] pos = new int[2];
        int Ax = x/24;
        int Ay = y/24;
        
        if(level[Ax][Ay] == 6 && (Ax != prevAx || Ay != prevAy)) {//checks if ghost is at a junction and new position isnt the old one
            state = PosChoice();
        }


        if (state.equals("right")) {
            x += 2;
        }
        else if (state.equals("left")) {
            x -= 2;
        }
        else if (state.equals("up")) {
            y += 2;
        }
        else if (state.equals("down")) {
            y -= 2;
        }
        if(x <= 24){
            x = 647;
        }
        if(x >= 648){
            x = 24;
        }
        
        prevAx = Ax;
        prevAy = Ay;
        
        pos[0] = x/24*24;
        pos[1] = y/24*24;

        
        return pos;
    }
    public String PosChoice(){//when at a junction, adds possible directions
        ArrayList<String> choices = new ArrayList<String>();
        int Ax = x/24;
        int Ay = y/24;
        
        if(Ax < 30) {
            if (level[Ax + 1][Ay] != 4 && level[Ax + 1][Ay] != 5) {
                choices.add("right");
            }
        }
        if(Ax > 0) {
            if (level[Ax - 1][Ay] != 4 && level[Ax - 1][Ay] != 5) {
                choices.add("left");
            }
        }
        if(Ay < 28) {
            if (level[Ax][Ay + 1] != 4 && level[Ax][Ay + 1] != 5) {
                choices.add("up");
            }
        }
        if(Ay > 0) {
            if (level[Ax][Ay - 1] != 4 && level[Ax][Ay - 1] != 5) {
                choices.add("down");
            }
        }
        
        if(choices.size() > 1){//avoids going back and forth(removing opposite direction)
            if(state .equals("left")) {
                choices.remove("right");
            }
            if(state .equals("right")) {
                choices.remove("left");
            }
            if(state .equals("up")) {
                choices.remove("down");
            }
            if(state .equals("down")) {
                choices.remove("up");
            }
        }
        
        int c = (int)(Math.random()*(choices.size()));//randomly select a direction
        System.out.println("STATE: "+state+" CHOICE: "+choices.get(c));


        return choices.get(c);
    }
}
