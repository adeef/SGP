package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
	Texture backgroundTexture;
	BitmapFont font;
	
	BitmapFont time;
	
	ShapeRenderer sr;
	Texture redG;

	int Px=0;//player coords
	int Py=0;
	
	int Rx = 0;//red ghost coords
	int Ry = 0;

	int Bx = 0;//blue ghost coords
	int By = 0;

	int Ox = 0;//orange ghost coords
	int Oy = 0;

	int Pix = 0;//pink ghost coords
	int Piy = 0;
	
	
	String state = "RIGHT";
	Pacman p = new Pacman();
	ArrayList<int[]> eaten = new ArrayList<int[]>();
	Timer t = new Timer();


	Ghost red = new Ghost("red");
	Ghost orange = new Ghost("orange");
	Ghost pink = new Ghost("pink");
	Ghost blue = new Ghost("blue");
	

	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(672, 800);
		
		batch = new SpriteBatch();
		backgroundTexture = new Texture("fullLevel.png");
		pac = new Texture("Original_PacMan_RIGHT.png");
		
		redG = new Texture("redL.png");

		font = new BitmapFont(Gdx.files.internal("joystix.fnt"),Gdx.files.internal("joystix.png"),false);
		time = new BitmapFont(Gdx.files.internal("joystix.fnt"),Gdx.files.internal("joystix.png"),false);

		sr = new ShapeRenderer();
		





	}
	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();

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


		batch.draw(redG,Rx,Ry);//red ghost
		Rx = red.move()[0];
		Ry = red.move()[1];

		
		batch.draw(redG,Bx,By);//blue ghost
		Bx = blue.move()[0];
		By = blue.move()[1];

		batch.draw(redG,Ox,Oy);//orange ghost
		Ox = orange.move()[0];
		Oy = orange.move()[1];

		batch.draw(redG,Pix,Piy);//pink ghost
		Pix = pink.move()[0];
		Piy = pink.move()[1];
		

		String points = "SCORE: "+p.grub()[0];
		font.draw(batch, points, 0,788);
		t.stop();
		String elap = "TIME: "+(int)t.getElapsedTimeSecs(); 
		time.draw(batch,elap,250,788);


		batch.draw(pac, Px, Py);
		batch.end();




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


	@Override
	public void dispose () {
		batch.dispose();
		pac.dispose();
		font.dispose();
		sr.dispose();
	}

}