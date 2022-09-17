package PackPhysique.Component;

public class Vitesse {

    private Position direction;
    private double speedX;
    private double speedY;

    private double speed;

    public Vitesse(Position direction, double speed) {
        this.direction = direction;
        this.speed = speed;

        speedX = speed * (direction.getX()/(Math.abs(direction.getX())+Math.abs(direction.getY())));
        speedY = speed * (direction.getY()/(Math.abs(direction.getX())+Math.abs(direction.getY())));
    }

    public void setDirection(Position direction){
        this.direction = direction;
        speedX = speed * (direction.getX()/(Math.abs(direction.getX())+Math.abs(direction.getY())));
        speedY = speed * (direction.getY()/(Math.abs(direction.getX())+Math.abs(direction.getY())));
    }

    public void inverseX(){
        direction.setX(-direction.getX());
        speedX = speed * (direction.getX()/(Math.abs(direction.getX())+Math.abs(direction.getY())));
        speedY = speed * (direction.getY()/(Math.abs(direction.getX())+Math.abs(direction.getY())));
    }

    public void inverseY(){
        direction.setY(-direction.getY());
        speedX = speed * (direction.getX()/(Math.abs(direction.getX())+Math.abs(direction.getY())));
        speedY = speed * (direction.getY()/(Math.abs(direction.getX())+Math.abs(direction.getY())));
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        speedX = speed * (direction.getX()/(Math.abs(direction.getX())+Math.abs(direction.getY())));
        speedY = speed * (direction.getY()/(Math.abs(direction.getX())+Math.abs(direction.getY())));
    }

    public double getSpeed() {
        return speed;
    }

    public double getSpeedX() {
        speedX = speed * (direction.getX()/(Math.abs(direction.getX())+Math.abs(direction.getY())));
        return speedX;
    }

    public double getSpeedY() {
        speedY = speed * (direction.getY()/(Math.abs(direction.getX())+Math.abs(direction.getY())));
        return speedY;
    }
}
