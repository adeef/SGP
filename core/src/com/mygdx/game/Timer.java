package com.mygdx.game;

public class Timer {

    private long startTime = 0;
    private long stopTime = 0;

    public Timer()
    {
        startTime = System.currentTimeMillis();
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        stopTime = System.currentTimeMillis();

    }


    public void stop(String process_name) {
        stopTime = System.currentTimeMillis();
        System.out.println(process_name + " StopWatch: " + getElapsedTime() + " milliseconds.");
        System.out.println(process_name + " StopWatch: " + getElapsedTimeSecs() + " seconds.");
    }

    public long getElapsedTime() {
        return stopTime - startTime;
    }

    public double getElapsedTimeSecs() {
        double elapsed;
        elapsed = ((double)(stopTime - startTime)) / 1000;
        return elapsed;
    }
} 