package com.mygdx.game;

import java.util.ArrayList;


public class Ghost {
    private int x = 48;
    private int y = 24;
    private String state = "right";
    private int prevAx;
    private int prevAy;
    private int PacX;
    private int PacY;
    
    
    private String color;
    private int[][] level = {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 6, 2, 2, 6, 4, 4, 6, 2, 2, 6, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 6, 2, 2, 6, 2, 2, 2, 6, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 2, 4, 4, 6, 2, 2, 6, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
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
            {4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 6, 2, 2, 6, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 0, 4, 2, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 6, 2, 2, 6, 4, 4, 6, 2, 2, 6, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 6, 2, 2, 6, 2, 2, 2, 6, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
    };
    
    public Ghost(String color){
        
        this.color = color;
        if(color .equals("blue")){
            x = 336;
        }
    }
    
    public int[] move(int PacX,int PacY){
        this.PacX = PacX;
        this.PacY = PacY;
        if(color.equals("blue")){
            int[] pos = new int[2];
            int Ax = x / 24;
            int Ay = y / 24;
            
            state = shortestPath();//calculate shortest path
            
            if (state.equals("right")) {
                x += 1;
            } else if (state.equals("left")) {
                x -= 1;
            } else if (state.equals("up")) {
                y += 1;
            } else if (state.equals("down")) {
                y -= 1;
            }
            if (x <= 24) {//allows for side teleportation 
                x = 647;
            }
            if (x >= 648) {
                x = 24;
            }
            
            prevAx = Ax;
            prevAy = Ay;
            
            pos[0] = x / 24 * 24;//rounding position to match a 24x24 box
            pos[1] = y / 24 * 24; 
            return pos;
        }

        int[] pos = new int[2];
        int Ax = x / 24;
        int Ay = y / 24;

        if (level[Ax][Ay] == 6 && (Ax != prevAx || Ay != prevAy)) {//checks if ghost is at a junction and new position isnt the old one
            
            state = PosChoice().get((int)(Math.random()*(PosChoice().size())));//finding possible directions and choosing one
        }


        if (state.equals("right")) {
            x += 2;
        } else if (state.equals("left")) {
            x -= 2;
        } else if (state.equals("up")) {
            y += 2;
        } else if (state.equals("down")) {
            y -= 2;
        }
        if (x <= 24) {
            x = 647;
        }
        if (x >= 648) {
            x = 24;
        }

        prevAx = Ax;//previous position
        prevAy = Ay;

        pos[0] = x / 24 * 24;//rounding position to match a 24x24 box
        pos[1] = y / 24 * 24;
        return pos;
        
    }

    private String shortestPath() {
        ArrayList<String> possible = PosChoice();
        double dist = Double.POSITIVE_INFINITY;//value of shortest path in possible
        String path = "";//direction ghost will move in
        
        
        for(int i = 0; i < possible.size(); i++){
            int Ax = x/24;
            int Ay = y/24;
            if(possible.get(i).equals("right")){
                double d = Math.pow((Ax + 1) - (PacX/24),2)+Math.pow(Ay - PacY/24,2);//finding distance between ghost and pacman
                d = Math.pow(d,0.5);
                if(d< dist){
                    dist = d;
                    path = possible.get(i);
                }
            }
            if(possible.get(i).equals("left")){
                double d = Math.pow((Ax - 1) - (PacX/24),2)+Math.pow(Ay - PacY/24,2);
                d = Math.pow(d,0.5);
                if(d< dist){
                    dist = d;
                    path = possible.get(i);

                }
            }
            if(possible.get(i).equals("down")){
                double d = Math.pow((Ax) - (PacX/24),2)+Math.pow(Ay-1 - PacY/24,2);
                d = Math.pow(d,0.5);
                if(d< dist){
                    dist = d;
                    path = possible.get(i);

                }
            }
            if(possible.get(i).equals("up")){
                double d = Math.pow((Ax) - (PacX/24),2)+Math.pow(Ay+1 - PacY/24,2);
                d = Math.pow(d,0.5);
                if(d< dist){
                    dist = d;
                    path = possible.get(i);

                }
            }
        }
        return path;
    }

    public ArrayList<String> PosChoice(){//when at a junction, adds possible directions
        ArrayList<String> choices = new ArrayList<String>();
        int Ax = x/24;
        int Ay = y/24;
        
        if(Ax < 30) {//checking possibilities
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
        

        return choices;
    }
}
