package com.mygdx.game;

import java.util.ArrayList;


public class Ghost {
    private int x = 48;
    private int y = 24;
    private String state = "right";
    
    private String color;
    
    public Ghost(String color){
        
        this.color = color;
    }
    private int[][] level = {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 6, 2, 2, 6, 4, 4, 3, 2, 2, 6, 4, 0, 0, 0, 4, 0, 4, 7, 0, 0, 4, 6, 2, 2, 6, 2, 3, 2, 6, 4},
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
            {4, 6, 2, 2, 6, 4, 4, 6, 2, 2, 6, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 6, 2, 2, 6, 2, 3, 2, 6, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
    };

    
    public int[] move(){
        int[] pos = new int[2];
        int Ax = x/24;
        int Ay = y/24;
        if(level[Ax][Ay] == 6) {
            state = PosChoice();
        }

        if (state.equals("right")) {
            Ax += 1;
        }
        else if (state.equals("left")) {
            Ax -= 1;
        }
        else if (state.equals("up")) {
            Ay += 1;
        }
        else if (state.equals("down")) {
            Ay -= 1;
        }
        if(x <= 24){
            x = 647;
        }
        if(x >= 648){
            x = 24;
        }
        
        x = Ax*24;
        y = Ay*24;
                
        pos[0] = x;
        pos[1] = y;

        
        return pos;
    }
    public String PosChoice(){
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
            if (level[Ax][Ay - 1] != 4 && level[Ax][Ay] != 5) {
                choices.add("down");
            }
        }
        
        if(choices.size() > 1){
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
        
        int c = (int)(Math.random()*(choices.size()));
        System.out.println("STATE: "+state+" CHOICE: "+choices.get(c));


        return choices.get(c);
    }
}
