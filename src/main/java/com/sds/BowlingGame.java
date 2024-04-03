package com.sds;

public class BowlingGame {

    private int turn;
    private Frame[] score;

    public BowlingGame() {
        this.score = new Frame[14];
        for(int i=0; i<14; i++) {
            score[i] = new Frame();
        }
        this.turn = 2;
    }


    public void roll(int pins) {
        if(pins<0 || pins>10) throw new IllegalArgumentException("invalid pin.");

        //todo: 로직 분할
        if(score[turn].isFrameOver()) {
            turn++;
        }
        score[turn].hit(pins);
        score[turn-1].addBonus(pins);
        score[turn-2].addBonus(pins);
    }

    public int score() {
        int result = 0;

        for(int i=0; i<14; i++) {
            result += score[i].getPointOfFrame();
        }

        return result;
    }
}
