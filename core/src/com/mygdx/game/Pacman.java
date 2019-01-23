package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import java.util.*;
public class Pacman {

    private static int x;
    private static int y;
    private static String state;
    private static int points;
    private int Ax = x;//position in array
    private int Ay = y;
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

    public Pacman(){
        x = 48;//starting position
        y = 24;
        state = "RIGHT";

    }

    public int[] move() {
        int[] pos = new int[2];//stores x,y
        Ax = x/24;
        Ay = y/24;

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
            x+=2;
        }
        if(state.equals("LEFT") && level[Ax-1][Ay] != 4 && level[Ax-1][Ay] != 5 ){
            x-=2;
        }
        if(state.equals("UP") && level[Ax][Ay+1] != 4 && level[Ax][Ay+1] != 5 ){
            y+=2;
        }
        if(state.equals("DOWN") && level[Ax][Ay-1] != 4 && level[Ax][Ay-1] != 5 ){
            y-=2;
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
    public int grub(){
        Ax = x/24;
        Ay = y/24;
        
        if(level[Ax][Ay] == 2){
            points+=10;
        }
        return points;
    }
}
