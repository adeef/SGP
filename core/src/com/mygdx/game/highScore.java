package com.mygdx.game;

import java.io.*;
import java.util.*;

public class highScore {
    PrintWriter outFile;
    double highest = Double.NEGATIVE_INFINITY;
    
    public highScore() throws Exception{
        outFile = new PrintWriter(new FileOutputStream (new File ("scores.txt"),true));

    }
    public void addLine(int score){
        outFile.println(""+score);
    }
    public void close(){
        outFile.close();
    }
    public double high() throws Exception{
        Scanner inFile = new Scanner(new BufferedReader(new FileReader("scores.txt")));
        while(inFile.hasNextLine()){
            int x = Integer.parseInt(inFile.nextLine());
            if(x > highest){
                highest = x;
            }
        }
        inFile.close();
        return highest;
    }
}
