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
import sun.font.TrueTypeFont;

import java.util.*;

public class MyGdxGame extends ApplicationAdapter {

	SpriteBatch batch;
	Texture img;
	Texture backgroundTexture;
	BitmapFont font;

	int x=0;
	int y=0;
	String state = "RIGHT";
	Pacman p = new Pacman();
	

	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(672, 800);
		
		batch = new SpriteBatch();
		backgroundTexture = new Texture("fullLevel.png");
		img = new Texture("Original_PacMan_RIGHT.png");

		font = new BitmapFont(Gdx.files.internal("joystix.fnt"),Gdx.files.internal("joystix.png"),false);




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
		
		String points = "SCORE: "+p.grub();
		font.draw(batch, points, 0,788);


		batch.draw(img, x, y);


		if(state .equals("RIGHT")) {
			img = new Texture("Original_PacMan_RIGHT.png");
		}
		if(state .equals("LEFT")) {
			img = new Texture("Original_PacMan.png");
		}
		if(state .equals("DOWN")) {
			img = new Texture("Original_PacMan_DOWN.png");
		}
		if(state .equals("UP")) {
			img = new Texture("Original_PacMan_UP.png");
		}


		batch.end();

	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		font.dispose();
	}

}