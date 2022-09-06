package PackPhysique.Component;

import PackPhysique.Map;

import java.awt.Component;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class HitBox extends Position{
    private double sizeX;
    private double sizeY;
    private Rectangle2D.Double boxe;


    public HitBox(double x, double y, double sizeX, double sizeY) {
        super(x, y);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.boxe = new Rectangle2D.Double(x, y, sizeX, sizeY);
    }

    private void updateBoxe(){
        this.boxe = new Rectangle2D.Double(super.getX(), super.getY(), sizeX, sizeY);
    }

    @Override
    public void setPosition(Position position){
        super.setX(position.getX());
        super.setY(position.getY());
        updateBoxe();
    }

    @Override
    public void setX(double x){
        super.setX(x);
        updateBoxe();
    }

    @Override
    public void setY(double y){
        super.setY(y);
        updateBoxe();
    }

    public void setSize(double sizeX, double sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        updateBoxe();
    }

    public boolean isHit(HitBox hitBox){
        return boxe.intersects(hitBox.getBoxe());
    }

    public Rectangle2D.Double getBoxe() {
        return boxe;
    }

    public double getSizeX() {
        return sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }


    //Graphique

    public Graphics drawHitBox(Graphics graphics){
        graphics.drawRect((int)super.getX(), (int)super.getY(), (int)getSizeX(), (int)getSizeY());
        return graphics;
    }
}


