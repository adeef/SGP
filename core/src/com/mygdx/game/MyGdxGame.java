package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import sun.font.TrueTypeFont;

import java.lang.reflect.Array;
import java.util.*;

public class MyGdxGame extends ApplicationAdapter {

	SpriteBatch batch;
	Texture pac;
	Texture backgroundTexture;
	BitmapFont font;
	ShapeRenderer sr;

	int x=0;
	int y=0;
	String state = "RIGHT";
	Pacman p = new Pacman();
	ArrayList<int[]> eaten = new ArrayList<int[]>();
	

	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(672, 800);
		
		batch = new SpriteBatch();
		backgroundTexture = new Texture("fullLevel.png");
		pac = new Texture("Original_PacMan_RIGHT.png");

		font = new BitmapFont(Gdx.files.internal("joystix.fnt"),Gdx.files.internal("joystix.png"),false);
		sr = new ShapeRenderer();





	}
	@Override
	public void render () {
		

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();

		batch.draw(backgroundTexture,0,0);



		x = p.move()[0];
		y = p.move()[1];
		
		state = p.state();
		
		String points = "SCORE: "+p.grub()[0];
		font.draw(batch, points, 0,788);

		
		batch.draw(pac, x, y);


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
		batch.end();
		
		sr.begin(ShapeType.Filled);
		sr.setColor(1,0,0,0);
		ArrayList<int[]>drawDots = p.drawing();
		for(int i = 0; i < drawDots.size(); i++){
			sr.rect(drawDots.get(i)[0]*24+12,drawDots.get(i)[1]*24+12,4,4);
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