package model;

import java.io.Serializable;

public class PacMan implements Serializable {

    public final static double ABVANCEMENT = 10.0;

    public  static enum Direction {TOP, RIGTH, BUTTOM, LEFT};

    private double radius;
    private double posX;
    private double posY;
    private long waitTime;
    private Direction direction;
    private int rebound;
    private boolean isStop;

    public PacMan(double radius, double posX, double posY, int waitTime, Direction direction, int rebound, boolean isStop){
        this.radius = radius;
        this.posX = posX;
        this.posY = posY;
        this.waitTime = waitTime;
        this.direction = direction;
        this.rebound = rebound;
        this.isStop = isStop;
    }

    public void move(double posXMinScene, double posXMaxScene, double posYMinScene, double posYMaxScene) {

        posX += ABVANCEMENT;
        posY += ABVANCEMENT;

        switch (direction) {
            case LEFT:
                if (posX - radius < posXMinScene) {
                    posX = radius;
                } else {
                    posX -= ABVANCEMENT;
                }
                break;
            case RIGTH:
                if (posX + radius > posXMaxScene) {
                    posX = posXMaxScene - radius;
                } else {
                    posX = posX + ABVANCEMENT;
                }
                break;
            case TOP:
                if (posY - radius < posYMinScene) {
                    posY = radius;
                } else {
                    posY = posY - ABVANCEMENT;
                }
                break;
            case BUTTOM:
                if (posY + radius > posYMaxScene) {
                    posY = posYMaxScene - radius;
                } else {
                    posY += ABVANCEMENT;
                }
                break;
        }
    }

    public double getRadius(){
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getRebound() {
        return rebound;
    }

    public void setRebound(int rebound) {
        this.rebound = rebound;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        this.isStop = stop;
    }
}
