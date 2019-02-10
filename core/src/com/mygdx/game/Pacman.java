package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.util.*;

public class Pacman {

    private static int x = 48;
    private static int y = 24;
    private static String state = "RIGHT";
    private static int points;
    private int Ax = x;//position in array
    private int Ay = y;
    private int animation_count=0;
    int frame=1;

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


    public int[] move() {
        int[] pos = new int[2];//stores x,y
        Ax = x/24;
        Ay = y/24;

        int mx= Gdx.input.getX();
        int my= Gdx.input.getY();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && level[Ax+1][Ay] != 4 && level[Ax+1][Ay] != 5 ) {
            state = "RIGHT";
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && level[Ax-1][Ay] != 4 && level[Ax-1][Ay] != 5 ) {
            state = "LEFT";
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.UP) && level[Ax][Ay+1] != 4 && level[Ax][Ay+1] != 5 ) {
            state = "UP";
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && level[Ax][Ay-1] != 4 && level[Ax][Ay-1] != 5 ) {
            state = "DOWN";

        }
        
        if(state.equals("RIGHT") && level[Ax+1][Ay] != 4 && level[Ax+1][Ay] != 5 ){
            x+=3;
        }
        if(state.equals("LEFT") && level[Ax-1][Ay] != 4 && level[Ax-1][Ay] != 5 ){
            x-=3;
        }
        if(state.equals("UP") && level[Ax][Ay+1] != 4 && level[Ax][Ay+1] != 5 ){
            y+=3;
        }
        if(state.equals("DOWN") && level[Ax][Ay-1] != 4 && level[Ax][Ay-1] != 5 ){
            y-=3;
        }
        if(x <= 24){
            x = 647;
        }
        if(x >= 648){
            x = 24;
        }

        pos[0] = Ax*24;
        pos[1] = Ay*24;

        
        return pos;
    }
    
    public String state(){
        
        return state;
    }
    public ArrayList<int[]> drawing(int check){//drawing dots
        ArrayList<int[]> dots = new ArrayList<int[]>();
        for(int i = 0; i < 28;i++){
            for(int j = 0; j < 31;j++){
                if(level[i][j] == check){
                    int[] t = new int[2];
                    t[0] = i;
                    t[1] = j;
                    dots.add(t);
                }
            }
        }
        return dots;
    }
    public boolean winner() {//check if all dots are eaten
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 31; j++) {
                if(level[i][j] == 2 || level[i][j] == 3){
                    return false;
                }

            }
        }
        return true;
    }
    
    public int[] grub(){//if pacman is over a dot, he eats it and points are added
        Ax = x/24;
        Ay = y/24;
        
        if(level[Ax][Ay] == 2){
            points+=10;
            
            level[Ax][Ay] = 0;
            
        }
        if(level[Ax][Ay] == 3){
            points+=50;
            level[Ax][Ay] = 0;
        }

        int[] r = new int[3];
        r[0] = points;
        r[1] = Ax;
        r[2] = Ay;
        return r;
    }
    public void points_add(int point_addition){
        points+=point_addition;

    }
    public void reset(){
        x = 48;
        y = 24;
    }
    public boolean bigDot(){
        boolean eaten = level[x/24][y/24] == 3;
        return eaten;
    }

    public Texture pac_pic(){
        if(animation_count==10){
            if(frame==1){
                frame=2;
            }
            else{
                frame=1;
            }
            animation_count=0;
        }
        animation_count+=1;
        Texture pic = new Texture("Original_PacMan_"+state+frame+".png");
        return pic;

    }
}
