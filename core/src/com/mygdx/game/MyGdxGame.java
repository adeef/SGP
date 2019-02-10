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
	Texture life;
	Texture dot;
	Texture big_dot;
	BitmapFont time;
	
	ShapeRenderer sr;
	Texture redG;

	int Px=0;
	int Py=0;

	boolean start=true;

	
	
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
		
		batch = new SpriteBatch();
		backgroundTexture = new Texture("fullLevel.png");
		pac = new Texture("Original_PacMan_RIGHT1.png");
		Main_Menu= new Texture("Main_Menu.png");
		play_btn=new Texture("Play_button.png");
		play_btn_hover=new Texture("Play_button_Hover.png");
		redG = new Texture("redL.png");
		dot=new Texture("point1.png");
        big_dot=new Texture("big_point1.png");
		font = new BitmapFont(Gdx.files.internal("joystix.fnt"),Gdx.files.internal("joystix.png"),false);
		time = new BitmapFont(Gdx.files.internal("joystix.fnt"),Gdx.files.internal("joystix.png"),false);

		sr = new ShapeRenderer();
		
		life = new Texture("Original_PacMan_RIGHT1.png");
		
		

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
            pac=p.pac_pic();

            movement();//moves player and ghosts
            
            drawO();

            scared();

            timer();
            
            batch.draw(pac, Px, Py);
            
            for(int i = 0; i < 4; i++){
                if(ghosts[i].touchPac()){
                    lives -=1;
                    reset();
                    try{
                        Thread.sleep(2000);
                    }
                    catch (Exception e){}
                    break;
                }
            }

            
            
            for(int i = 0; i <lives; i++){
                batch.draw(life,500+i*35,760);
            }
        }
        batch.end();

        if(!start){

            ArrayList<int[]>drawDots = p.drawing(2);
            batch.begin();
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
            batch.end();

            //sr.end();
        }

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
            if(ghosts[i].canGive){
                p.points_add(200);
                ghosts[i].canGive = false;
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