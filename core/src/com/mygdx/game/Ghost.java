package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;


public class Ghost {
    private int x = 13*24;
    private int y = 16*24;


    private String state = "right";
    private int prevAx;
    private int prevAy;
    private int PacX;
    private int PacY;

    private boolean scared = false;
    private boolean eaten = false;


    private int movement = 2;//how much a ghost changes position
    private int animation_count=0;
    private int frame=1;

    public boolean canGive = false;

    Timer t = new Timer();


    private ArrayList<String> shortCuts = new ArrayList<String>();


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
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
            {4, 6, 2, 2, 6, 4, 4, 6, 2, 2, 6, 4, 4, 0, 4, 4, 4, 4, 4, 6, 0, 0, 6, 4, 4, 6, 2, 2, 2, 6, 4},
            {4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4},
            {4, 2, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 4, 4, 0, 4, 6, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4},
            {4, 6, 2, 2, 6, 4, 4, 6, 2, 2, 6, 4, 4, 0, 4, 4, 4, 4, 4, 6, 0, 0, 6, 4, 4, 6, 2, 2, 2, 6, 4},
            {4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 2, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 4, 2, 4, 4, 2, 4, 4, 4, 2, 4},
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
    }

    public Texture ghost_pic(){
        if(animation_count==20){
            if(frame==1){
                frame=2;
            }
            else{
                frame=1;
            }
            animation_count=0;
        }
        animation_count+=1;
        Texture pic = new Texture(state+color+frame+".png");
        if(scared){
            pic=new Texture("scaredy_ghost.png");
        }
        if(eaten){
            pic=new Texture("eaten_face.png");
        }
        return pic;
    }

    public int[] move(int PacX,int PacY){
        isEaten();

        if(eaten && x/24 == 1 && y/24 == 1){//once home is reached
            scared = false;
            eaten = false;
        }
        if(eaten){
            return middle();
        }

        t.stop();
        if(scared && (int)t.getElapsedTimeSecs() > 10){
            scared = false;
        }
        if(scared){
            movement = 1;
        }
        else{
            movement = 2;
        }
        this.PacX = PacX;
        this.PacY = PacY;


        if(color.equals("blue")){
            int[] pos = new int[2];
            int Ax = x / 24;
            int Ay = y / 24;

            state = shortestPath(PacX/24,PacY/24);//calculate shortest path
            //shortP(new ArrayList<String>(),x/24,y/24,PacX/24,PacY/24);
            //System.out.println(shortCuts);


            if(state.equals("")){
                state = "up";
            }


            if (state.equals("right")) {
                x += movement;
            } else if (state.equals("left")) {
                x -= movement;
            } else if (state.equals("up")) {
                y += movement;
            } else if (state.equals("down")) {
                y -= movement;
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
            x += movement;
        } else if (state.equals("left")) {
            x -= movement;
        } else if (state.equals("up")) {
            y += movement;
        } else if (state.equals("down")) {
            y -= movement;
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
    //recursive path finding - :( did not work
    private void shortP(ArrayList<String> paths, int x1, int y1, int x2, int y2 ){
        System.out.println(x1+","+y1);
        if(x1 == x2 && y1 == y2){
            if(paths.size() < shortCuts.size()){
                shortCuts = paths;
            }
        }

        ArrayList<String> possible = PosChoice();
        System.out.println(possible);

        for(int i = 0; i < possible.size(); i++){

            System.out.println(possible.get(i));
            paths.add(possible.get(i));

            if(possible.get(i).equals("right")){
                shortP(paths,x1+1,y1,x2,y2);
            }
            if(possible.get(i).equals("left")){
                shortP(paths,x1-1,y1,x2,y2);
            }
            if(possible.get(i).equals("up")){
                shortP(paths,x1,y1+1,x2,y2);
            }
            if(possible.get(i).equals("down")){
                shortP(paths,x1,y1-1,x2,y2);
            }
        }
    }

    private String shortestPath(int x2, int y2) {//A-star path finding algorithm
        ArrayList<String> possible = PosChoice();
        double dist = Double.POSITIVE_INFINITY;//value of shortest path in possible
        String path = "";//direction ghost will move in


        for(int i = 0; i < possible.size(); i++){
            int Ax = x/24;
            int Ay = y/24;
            if(possible.get(i).equals("right")){
                double d = Math.pow((Ax + 1) - (x2),2)+Math.pow(Ay - y2,2);//finding distance between ghost and pacman
                d = Math.pow(d,0.5);
                if(d< dist){
                    dist = d;
                    path = possible.get(i);
                }
            }
            if(possible.get(i).equals("left")){
                double d = Math.pow((Ax - 1) - (x2),2)+Math.pow(Ay - y2,2);
                d = Math.pow(d,0.5);
                if(d< dist){
                    dist = d;
                    path = possible.get(i);

                }
            }
            if(possible.get(i).equals("down")){
                double d = Math.pow((Ax) - (x2),2)+Math.pow(Ay-1 - y2,2);
                d = Math.pow(d,0.5);
                if(d< dist){
                    dist = d;
                    path = possible.get(i);

                }
            }
            if(possible.get(i).equals("up")){
                double d = Math.pow((Ax) - (x2),2)+Math.pow(Ay+1 - y2,2);
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
    public void beScared(){
        scared = true;
        t = new Timer();
    }


    public void isEaten(){
        if(!eaten){
            if(x/24 == PacX/24 && y/24 == PacY/24 && scared){
                eaten = true;
                canGive = true;

            }
        }
    }


    public int[] middle(){//when pacman eats a ghost while they're scared
        state = shortestPath(1,1);
        int[] pos = new int[2];
        int Ax = x / 24;
        int Ay = y / 24;

        if (state.equals("right")) {
            x += movement*5;
        } else if (state.equals("left")) {
            x -= movement*5;
        } else if (state.equals("up")) {
            y += movement*5;
        } else if (state.equals("down")) {
            y -= movement*5;
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
    public boolean touchPac(){//checking if ghost touched pacman
        boolean touch = (x/24 == PacX/24 && y/24 == PacY/24 && !scared);
        return touch;
    }

}
