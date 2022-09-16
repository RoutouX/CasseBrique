package PackPhysique.Component;

import MoteurSonor.MoteurAudio;
import PackPhysique.Component.Component;
import PackPhysique.Map;

public class Balle extends Component {

    private static boolean isMovable = true;
    private static boolean onHitComponentEvent = false;

    private static double sizeX = 20;
    private static double sizeY = 20;

    private double firstVitesseX = -500;


    public Balle(Map map, double x, double y) {
        super(map, x, y, sizeX, sizeY, isMovable, onHitComponentEvent);

    }

    public Balle(Map map, double x, double y, double firstVitesseX) {
        super(map, x, y, sizeX, sizeY, isMovable, onHitComponentEvent);
        this.firstVitesseX = firstVitesseX;
    }



    @Override
    public void run(){
        super.run();
        this.setVitesseY(firstVitesseX);
    }

    @Override
    public void onStopXEvent(Component component){
        getMap().getMoteurPhysique().getRunGame().getMoteurAudio().playSound(MoteurAudio.SOUND_GAME_REBOND);
        if (component.getId() != this.getId()) {
            if (component.getClass() == Brique.class) {
                onHitBriqueNewBall(component);
                component.terminate();
                getMap().deleteAComponent(component);
                this.setVitesseX(this.getVitesseX()*-1.00);
            } else if (component.getClass() == Raquette.class){
                Raquette raquette = (Raquette)component;
                double positionXBall = this.getX() + (this.getSizeX()/2);
                double positionXRaquettte = raquette.getX() + (Raquette.getSizeX()/2);
                double diferentielBallRaquette = positionXRaquettte - positionXBall;
                double nouveauDifferancielleYX = diferentielBallRaquette / (Raquette.getSizeX()/2)*2;
                this.setVitesseX(this.getVitesseY()*nouveauDifferancielleYX);
            } else if (component.getClass() == Fall.class) {
                getMap().deleteAComponent(this);
                this.terminate();
                getMap().resetGameIf0Balle();
            }else {
                this.setVitesseX(this.getVitesseX()*-1.00);
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
                this.setVitesseY(this.getVitesseY()*-1.00);
            } else if (component.getClass() == Raquette.class){
                this.setVitesseY(this.getVitesseY()*-1.00);
                Raquette raquette = (Raquette)component;
                double positionXBall = this.getX() + (this.getSizeX()/2);
                double positionXRaquettte = raquette.getX() + (Raquette.getSizeX()/2);
                double diferentielBallRaquette = positionXRaquettte - positionXBall;
                double nouveauDifferancielleYX = diferentielBallRaquette / (Raquette.getSizeX()/2)*2;
                this.setVitesseX(this.getVitesseY()*nouveauDifferancielleYX);
            } else if (component.getClass() == Fall.class) {
                getMap().deleteAComponent(this);
                this.terminate();
                getMap().resetGameIf0Balle();
            } else {
                this.setVitesseY(this.getVitesseY()*-1.00);
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

            getMap().addNewComponent(new Balle(getMap(), (pX + sX/2), (pY+ sY/2), 500));
        }
    }

    public static double getSizeX() {
        return sizeX;
    }

    public static double getSizeY() {
        return sizeY;
    }
}
