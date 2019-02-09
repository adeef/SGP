package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	Texture play_btn;
	Texture play_btn_hover;
	Texture backgroundTexture;
	BitmapFont font;
	
	BitmapFont time;
	
	ShapeRenderer sr;
	Texture redG;

	int Px=0;
	int Py=0;
	boolean start=true;
	int Rx = 0;
	int Ry = 0;
	String state = "RIGHT";
	Pacman p = new Pacman();
	ArrayList<int[]> eaten = new ArrayList<int[]>();
	Timer t = new Timer();


	Ghost red = new Ghost("red");
	

	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(672, 800);
		
		batch = new SpriteBatch();
		backgroundTexture = new Texture("fullLevel.png");
		pac = new Texture("Original_PacMan_RIGHT.png");
		Main_Menu= new Texture("Main_Menu.png");
		play_btn=new Texture("Play_button.png");
		play_btn_hover=new Texture("Play_button_Hover.png");
		redG = new Texture("redL.png");

		font = new BitmapFont(Gdx.files.internal("joystix.fnt"),Gdx.files.internal("joystix.png"),false);
		time = new BitmapFont(Gdx.files.internal("joystix.fnt"),Gdx.files.internal("joystix.png"),false);

		sr = new ShapeRenderer();

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
            }
        }
    }
    public String time_converter(int secs){
	    String sec_out;
	    String min_out;
	    int minutes=secs/60;
	    secs=secs-(minutes*60);
	    if(secs<10){
	        sec_out="0"+secs;
        }
        else{
            sec_out=""+secs;
        }
        if(minutes<10){
            min_out="0"+minutes;
        }
        else{
            min_out=""+minutes;
        }

	    return ""+min_out+":"+sec_out;
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


            batch.draw(backgroundTexture,0,0);

            state = p.state();


            if(state .equals("RIGHT")) {
                pac = new Texture("Original_PacMan_RIGHT.png");
            }
            if(state .equals("LEFT")) {
                pac = new Texture("Original_PacMan.png");
            }
            if(state .equals("DOWN")) {
                pac = new Texture("Original_PacMan_DOWN.png");
            }
            if(state .equals("UP")) {
                pac = new Texture("Original_PacMan_UP.png");
            }


            Px = p.move()[0];
            Py = p.move()[1];

            //Rx = red.move()[0];
            //Ry = red.move()[1];

            batch.draw(redG,Rx,Ry);//red ghost

            String points = "SCORE: "+p.grub()[0];
            font.draw(batch, points, 0,788);
            t.stop();
            String time_display=time_converter((int)t.getElapsedTimeSecs());
            String elap = "TIME: "+time_display;
            time.draw(batch,elap,250,788);


            batch.draw(pac, Px, Py);


        }
        batch.end();



        if(start!=true){
            sr.begin(ShapeType.Filled);
            sr.setColor(1,1,1,0);
            ArrayList<int[]>drawDots = p.drawing(2);

            for(int i = 0; i < drawDots.size(); i++){
                sr.rect(drawDots.get(i)[0]*24+12,drawDots.get(i)[1]*24+12,4,4);
            }

            drawDots = p.drawing(3);

            for(int i = 0; i < drawDots.size(); i++){
                sr.rect(drawDots.get(i)[0]*24+6,drawDots.get(i)[1]*24+6,12,12);
            }
            if(p.winner()){
                System.out.println("YOU WON!");
            }

            sr.end();
        }

    }


	@Override
	public void dispose () {
		batch.dispose();
		pac.dispose();
		font.dispose();
		sr.dispose();
	}

}