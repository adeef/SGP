package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.*;

public class MyGdxGame extends ApplicationAdapter {

	SpriteBatch batch;
	Texture img;
	Texture backgroundTexture;

	int x=0;
	int y=0;
	String state = "RIGHT";
	Pacman p = new Pacman();

	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(672, 744);
		
		batch = new SpriteBatch();
		backgroundTexture = new Texture("fullLevel.png");
		img = new Texture("Original_PacMan_RIGHT.png");
		
		
	}
	@Override
	public void render () {
		

		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(backgroundTexture,0,0);
		batch.draw(img, x, y);

		x = p.move()[0];
		y = p.move()[1];
		//Thread.sleep((long)(1000/30-Gdx.graphics.getDeltaTime()));


		state = p.state();
		if(state .equals( "RIGHT")) {
			img = new Texture("Original_PacMan_RIGHT.png");
		}
		if(state .equals( "LEFT")) {
			img = new Texture("Original_PacMan.png");
		}
		if(state .equals( "DOWN")) {
			img = new Texture("Original_PacMan_DOWN.png");
		}
		if(state .equals( "UP")) {
			img = new Texture("Original_PacMan_UP.png");
		}

		batch.end();

	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

}