package PackPhysique.Component;

import MoteurSonor.MoteurAudio;
import PackPhysique.Component.Component;
import PackPhysique.Map;

public class Balle extends Component {

    private static boolean isMovable = true;
    private static boolean onHitComponentEvent = false;

    private static double sizeX = 20;
    private static double sizeY = 20;

    private static double ratioRebond = 1.05;

    private double firstVitesse = 500;
    private Position firstDirection;


    private boolean debug = false;

    public Balle(Map map, double x, double y) {
        super(map, x, y, sizeX, sizeY, isMovable, onHitComponentEvent);
        firstDirection = new Position(0,-100);
    }

    public Balle(Map map, double x, double y, boolean debug) {
        super(map, x, y, sizeX, sizeY, isMovable, onHitComponentEvent);
        firstDirection = new Position(0,-100);
        this.debug = debug;
    }

    public Balle(Map map, double x, double y, double firstVitesse, Position firstDirection) {
        super(map, x, y, sizeX, sizeY, isMovable, onHitComponentEvent);
        this.firstVitesse = firstVitesse;
        this.firstDirection = firstDirection;
    }

    public Balle(Map map, double x, double y, double firstVitesse, Position firstDirection, boolean debug) {
        super(map, x, y, sizeX, sizeY, isMovable, onHitComponentEvent);
        this.firstVitesse = firstVitesse;
        this.firstDirection = firstDirection;
        this.debug = debug;
    }



    @Override
    public void run(){
        super.run();
        this.getVitesse().setSpeed(firstVitesse);
        this.getVitesse().setDirection(firstDirection);
    }

    @Override
    public void onStopXEvent(Component component){
        getMap().getMoteurPhysique().getRunGame().getMoteurAudio().playSound(MoteurAudio.SOUND_GAME_REBOND);
        if (component.getId() != this.getId()) {
            if (component.getClass() == Brique.class) {
                onHitBriqueNewBall(component);
                component.terminate();
                getMap().deleteAComponent(component);
                this.getVitesse().setSpeed(this.getVitesse().getSpeed()*ratioRebond);
                this.getVitesse().inverseX();
            } else if (component.getClass() == Raquette.class){
                this.getVitesse().inverseX();
            } else if (component.getClass() == Fall.class) {
                getMap().deleteAComponent(this);
                this.terminate();
                getMap().resetGameIf0Balle();
            }else {
                //this.setVitesseX(this.getVitesseX()*-ratioRebond);
                this.getVitesse().setSpeed(this.getVitesse().getSpeed()*ratioRebond);
                this.getVitesse().inverseX();
            }
        }
    }
    @Override
    public void onStopYEvent(Component component){
        getMap().getMoteurPhysique().getRunGame().getMoteurAudio().playSound(MoteurAudio.SOUND_GAME_REBOND);
        if (component.getId() != this.getId()) {
            if (component.getClass() == Brique.class) {
                onHitBriqueNewBall(component);
                component.terminate();
                getMap().deleteAComponent(component);
                this.getVitesse().setSpeed(this.getVitesse().getSpeed()*ratioRebond);
                this.getVitesse().inverseY();
            } else if (component.getClass() == Raquette.class){
                this.getVitesse().setSpeed(this.getVitesse().getSpeed()*ratioRebond);
                Raquette raquette = (Raquette)component;
                double positionXBall = this.getX() + (this.getSizeX()/2);
                double positionXRaquettte = raquette.getX() + (Raquette.getSizeX()/2);
                double diferentielBallRaquette = positionXRaquettte - positionXBall;
                double nouveauDifferancielleYX = diferentielBallRaquette / (Raquette.getSizeX()/2)*2;
                if (debug){
                    System.out.println("Speed X = " + getVitesse().getSpeedX());
                    System.out.println("Speed Y = " + getVitesse().getSpeedY());
                    System.out.println("Speed = " + getVitesse().getSpeed());
                }
                this.getVitesse().setDirection(new Position(nouveauDifferancielleYX*-100, -100));
                if (debug){
                    System.out.println("Speed X = " + getVitesse().getSpeedX());
                    System.out.println("Speed Y = " + getVitesse().getSpeedY());
                    System.out.println("Speed = " + getVitesse().getSpeed());
                }
            } else if (component.getClass() == Fall.class) {
                getMap().deleteAComponent(this);
                this.terminate();
                getMap().resetGameIf0Balle();
            } else {
                this.getVitesse().setSpeed(this.getVitesse().getSpeed()*ratioRebond);
                this.getVitesse().inverseY();
            }
        }
    }
    public void onHitBriqueNewBall(Component component){
        double proba = Math.random() * 100;
        if (proba < 20){
            double pX = component.getX();
            double pY = component.getY();
            double sX = component.getHitBox().getSizeX();
            double sY = component.getHitBox().getSizeY();

            getMap().addNewComponent(new Balle(getMap(), (pX + sX/2), (pY+ sY/2), (400+100*getMap().getLevelGame()), new Position(0,-100)));
        }
    }

    public static double getSizeX() {
        return sizeX;
    }

    public static double getSizeY() {
        return sizeY;
    }
}
