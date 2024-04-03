package com.sds;

public class Frame {

    private int addCount = 0;
    private int point;
    private FrameType type;

    public Frame() {
        this.type = FrameType.START;
    }

    public boolean isFrameOver() {
        return !FrameType.PROCEED.equals(type) && !FrameType.START.equals(type);
    }

    public void hit(int pins) {
        if(pins==10) {
            type = FrameType.STRIKE;
        } else if(point+pins==10) {
            type = FrameType.SPARE;
        } else if(FrameType.START.equals(type)) {
            type = FrameType.PROCEED;
        } else if(FrameType.PROCEED.equals(type)) {
            type = FrameType.DONE;
        }
        point += pins;
        if(point>10) {
            throw new IllegalArgumentException("invalid point");
        }
    }

    //todo: refactoring
    public void addBonus(int bonus) {
        if(FrameType.STRIKE.equals(type)&&addCount<2) {
            point+=bonus;
        }else if(FrameType.SPARE.equals(type)&&addCount==0) {
            point+=bonus;
        }
        addCount++;
    }

    int getPointOfFrame() {
        return point;
    }
}
