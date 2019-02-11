package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;



import java.util.*;

public class MyGdxGame extends ApplicationAdapter {

    SpriteBatch batch;
    Texture pac;
    Texture Main_Menu;
    Texture you_win;
    Texture you_lose;
    Texture play_btn;
    Texture play_btn_hover;
    Texture backgroundTexture;
    Texture backgroundTexture_win;
    BitmapFont font;
    Texture life;
    Texture dot;
    Texture big_dot;
    BitmapFont time;
    Sound intro;
    Sound siren;


    ShapeRenderer sr;
    Texture redG;

    int Px=0;
    int Py=0;

    boolean start=true;

    int backg_animation_count;

    int total_backg_animation_count=0;
    int Rx = 0;//red ghost coords
    int Ry = 0;

    int Bx = 0;//blue ghost coords
    int By = 0;

    int Ox = 0;//orange ghost coords
    int Oy = 0;

    int Pix = 0;//pink ghost coords
    int Piy = 0;

    int[][] pos = {{Rx,Ry},{Bx,By},{Pix,Piy},{Ox,Oy}};

    int lives = 3;
    String main_time="";
    Ghost red = new Ghost("red");
    Ghost blue = new Ghost("blue");
    Ghost pink = new Ghost("pink");
    Ghost orange = new Ghost("orange");

    Ghost[] ghosts = new Ghost[4];


    String state = "RIGHT";//pacmans state
    Pacman p = new Pacman();


    Timer t = new Timer();




    @Override
    public void create () {
        Gdx.graphics.setWindowedMode(672, 800);
        backg_animation_count=0;

        batch = new SpriteBatch();
        backgroundTexture = new Texture("fullLevel.png");
        backgroundTexture_win= new Texture("fullLevel_winner.png");
        pac = new Texture("Original_PacMan_RIGHT1.png");
        Main_Menu= new Texture("Main_Menu.png");
        play_btn=new Texture("Play_button.png");
        play_btn_hover=new Texture("Play_button_Hover.png");
        redG = new Texture("redL.png");
        dot=new Texture("point1.png");
        big_dot=new Texture("big_point1.png");
        font = new BitmapFont(Gdx.files.internal("joystix.fnt"),Gdx.files.internal("joystix.png"),false);
        time = new BitmapFont(Gdx.files.internal("joystix.fnt"),Gdx.files.internal("joystix.png"),false);
        you_win = new Texture("you_win.png");
        you_lose= new Texture("you_lose.png");
        sr = new ShapeRenderer();

        siren = Gdx.audio.newSound(Gdx.files.internal("siren.wav"));
        intro =Gdx.audio.newSound(Gdx.files.internal("pacman_beginning.wav"));
        siren.loop(0.2f);
        intro.loop(0.05f);

        siren.pause();



        life = new Texture("Original_PacMan_RIGHT1.png");



    }

    public void total_reset(){
        Px=0;
        Py=0;

        backg_animation_count=0;
        total_backg_animation_count=0;

        Rx = 0;//red ghost coords
        Ry = 0;

        Bx = 0;//blue ghost coords
        By = 0;

        Ox = 0;//orange ghost coords
        Oy = 0;

        Pix = 0;//pink ghost coords
        Piy = 0;
        lives = 3;
        String main_time="";
        red = new Ghost("red");
        blue = new Ghost("blue");
        pink = new Ghost("pink");
        orange = new Ghost("orange");

        ghosts = new Ghost[4];


        state = "RIGHT";//pacmans state
        p.points_reset();
        p = new Pacman();


        t = new Timer();



    }

    @Override
    public void render () {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if(start){
            starting_menu();


        }

        else{
            intro.stop();
            batch.draw(backgroundTexture,0,0);
            if((p.winner() || lives==0) && total_backg_animation_count<=120){

                if(backg_animation_count>=12){
                    batch.draw(backgroundTexture_win,0,0);
                }
                if(backg_animation_count>=24){
                    backg_animation_count=0;
                }
                backg_animation_count+=1;
                total_backg_animation_count+=1;
            }
            if(total_backg_animation_count==121){
                batch.draw(Main_Menu,0,0);
                if(p.winner()){
                    batch.draw(you_win,186,400);
                }
                if(lives==0){
                    batch.draw(you_lose,186,400);

                }

                batch.draw(play_btn,224,240);
                int mx = Gdx.input.getX();
                int my = Gdx.input.getY();

                if(mx>=224 && mx<=449 && my>=502 && my<=560){
                    batch.draw(play_btn_hover,224,240);
                    if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                        System.out.println("IT RESET HEREEEE");
                        total_reset();

                    }
                }


            }
            else {

                state = p.state();
                pac=p.pac_pic();
                System.out.println(lives);
                System.out.println(total_backg_animation_count+"TTTTTTTTTTT");
                if(p.winner()!=true && lives!=0){
                    siren.resume();
                    movement();//moves player and ghosts

                }
                else {
                    siren.pause();
                }

                drawO();

                scared();

                timer();


                batch.draw(pac, Px, Py);

                for(int i = 0; i < 4; i++){//saaaaaaaaa
                    if(lives>=1){
                        if(ghosts[i].touchPac()){
                            lives -=1;
                            reset();
                            break;
                        }
                    }
                }



                for(int i = 0; i <lives; i++){
                    batch.draw(life,500+i*35,760);
                }
            }


            if(!start){


                if(total_backg_animation_count<121){
                    ArrayList<int[]>drawDots = p.drawing(2);
                    for(int i = 0; i < drawDots.size(); i++){
                        batch.draw(dot,drawDots.get(i)[0]*24+12,drawDots.get(i)[1]*24+12);
                        //             sr.rect(drawDots.get(i)[0]*24+12,drawDots.get(i)[1]*24+12,4,4);
                    }


                    drawDots = p.drawing(3);

                    // sr.begin(ShapeType.Filled);
                    //sr.setColor(1,1,1,0);
                    for(int i = 0; i < drawDots.size(); i++){
                        batch.draw(big_dot,drawDots.get(i)[0]*24+8,drawDots.get(i)[1]*24+8);
                        //sr.rect(drawDots.get(i)[0]*24+6,drawDots.get(i)[1]*24+6,12,12);
                    }

                }



                //sr.end();
            }
        }
        batch.end();

    }



    public void starting_menu(){
        batch.draw(Main_Menu,0,0);
        batch.draw(play_btn,224,371);


        int mx=Gdx.input.getX();
        int my=Gdx.input.getY();
        System.out.println("X:"+mx+" Y:"+my);

        if(mx>=224 && mx<=449 && my>=371 && my<=429){
            batch.draw(play_btn_hover,224,371);
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                start=false;
                t = new Timer();
            }
        }
    }

    public void movement(){
        ghosts[0] = red;
        ghosts[1] = blue;
        ghosts[2] = pink;
        ghosts[3] = orange;



        Px = p.move()[0];
        Py = p.move()[1];



        for(int i = 0; i < ghosts.length; i++){
            pos[i][0] = ghosts[i].move(Px,Py)[0];
            pos[i][1] = ghosts[i].move(Px,Py)[1];
        }
    }

    public void timer(){

        for(int i = 0; i < 4; i++){
            if(lives >= 1) {
                if (ghosts[i].canGive) {
                    p.points_add(200);
                    ghosts[i].canGive = false;
                }
            }
        }


        String points = "SCORE: "+p.grub()[0];
        font.draw(batch, points, 0,788);
        t.stop();

        int secs = (int)t.getElapsedTimeSecs();

        String time_display= t.time_converter(secs);
        String elap = "TIME: "+time_display;
        time.draw(batch,elap,250,788);
    }

    public void drawO(){//drawing ghost positions
        batch.draw(red.ghost_pic(),pos[0][0],pos[0][1]);
        batch.draw(pink.ghost_pic(),pos[2][0],pos[2][1]);
        batch.draw(orange.ghost_pic(),pos[3][0],pos[3][1]);
        batch.draw(blue.ghost_pic(),pos[1][0],pos[1][1]);


    }
    public void scared(){
        if(p.bigDot()){
            for(int i = 0; i <4; i++){
                ghosts[i].beScared();
            }
        }
    }
    public void reset(){
        red = new Ghost("red");
        blue = new Ghost("blue");
        pink = new Ghost("pink");
        orange = new Ghost("orange");

        ghosts = new Ghost[4];


        p.reset();


    }

    @Override
    public void dispose () {
        batch.dispose();
        pac.dispose();
        font.dispose();
        sr.dispose();
    }
}