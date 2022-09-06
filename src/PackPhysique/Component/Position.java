package PackPhysique.Component;

public class Position {
    //-----------------------Variable-----------------------------------
    private double X;
    private double Y;

    //-----------------------Constructor-----------------------------------
    Position(){
        this.X = -1;
        this.Y = -1;
    }
    public Position(double x, double y){
        this.X = x;
        this.Y = y;
    }
    Position(Position position){
        this.X = position.getX();
        this.Y = position.getY();
    }
    //-----------------------Get/Set-----------------------------------
    public void setPosition(Position position){
        this.X = position.getX();
        this.Y = position.getY();
    }
    /*
    public void setXY(double x, double y){
        this.X = x;
        this.Y = y;
    }
    */
    public double getX() {
        return X;
    }
    public void setX(double x) {
        X = x;
    }
    public double getY() {
        return Y;
    }
    public void setY(double y) {
        Y = y;
    }
    //-----------------------Calculator-----------------------------------
    //Distance deux podoubles
    public double distanceBetween(Position position){
        double distance =  Math.sqrt(Math.pow((this.X - position.getX()),2) + Math.pow((this.Y - position.getY()), 2));
        return distance;
    }
    public double distanceBetween(double x, double y){
        double distance =  Math.sqrt(Math.pow((this.X - x),2) + Math.pow((this.Y - y), 2));
        return distance;
    }
    //Alignment
    //x
    public boolean alignmentSurX(Position position){
        if (position.getX() == this.X){return true;} else{return false;}
    }
    public boolean alignmentSurX(double x, double y){
        if (x == this.X){return true;} else{return false;}
    }
    public boolean alignmentSurX(double x){
        if (x == this.X){return true;} else{return false;}
    }
    //y
    public boolean alignmentSurY(Position position){
        if (position.getX() == this.Y){return true;} else{return false;}
    }
    public boolean alignmentSurY(double x, double y){
        if (y == this.Y){return true;} else{return false;}
    }
    public boolean alignmentSurY(double y){
        if (y == this.Y){return true;} else{return false;}
    }
    //Est en range Circle
    public boolean isInRangeCircle(Position position, double d){
        if (distanceBetween(position) <= d){return true;}else {return false;}
    }
    public boolean isInRangeCircle(double x, double y, double d){
        if (distanceBetween(x, y) <= d){return true;}else {return false;}
    }
}
