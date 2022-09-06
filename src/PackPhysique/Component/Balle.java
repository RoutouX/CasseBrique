package PackPhysique.Component;

import PackPhysique.Component.Component;
import PackPhysique.Map;

public class Balle extends Component {

    private static boolean isMovable = true;
    private static boolean onHitComponentEvent = false;

    private static double sizeX = 20;
    private static double sizeY = 20;


    public Balle(Map map, double x, double y) {
        super(map, x, y, sizeX, sizeY, isMovable, onHitComponentEvent);
    }

    @Override
    public void run(){
        super.run();
        this.setVitesseY(-500);
    }

    @Override
    public void onStopXEvent(Component component){
        System.out.println("X");
        if (component.getId() != this.getId()) {
            if (component.getClass() == Brique.class) {
                component.terminate();
                getMap().deleteAComponent(component);
                this.setVitesseY(this.getVitesseX()*-1.05);
            } else if (component.getClass() == Raquette.class){
                Raquette raquette = (Raquette)component;
                double positionXBall = this.getX() + (this.getSizeX()/2);
                double positionXRaquettte = raquette.getX() + (Raquette.getSizeX()/2);
                double diferentielBallRaquette = positionXRaquettte - positionXBall;
                double nouveauDifferancielleYX = diferentielBallRaquette / (Raquette.getSizeX()/2)*2;
                this.setVitesseX(this.getVitesseY()*nouveauDifferancielleYX);
            }
            else {
                this.setVitesseX(this.getVitesseX()*-1.05);
            }
        }
    }
    @Override
    public void onStopYEvent(Component component){
        System.out.println("Y");
        if (component.getId() != this.getId()) {
            if (component.getClass() == Brique.class) {
                component.terminate();
                getMap().deleteAComponent(component);
                this.setVitesseY(this.getVitesseY()*-1.05);
            } else if (component.getClass() == Raquette.class){
                this.setVitesseY(this.getVitesseY()*-1.05);
                Raquette raquette = (Raquette)component;
                double positionXBall = this.getX() + (this.getSizeX()/2);
                double positionXRaquettte = raquette.getX() + (Raquette.getSizeX()/2);
                double diferentielBallRaquette = positionXRaquettte - positionXBall;
                double nouveauDifferancielleYX = diferentielBallRaquette / (Raquette.getSizeX()/2)*2;
                this.setVitesseX(this.getVitesseY()*nouveauDifferancielleYX);
            }
            else {
                this.setVitesseY(this.getVitesseY()*-1.05);
            }
        }
    }

    public static double getSizeX() {
        return sizeX;
    }

    public static double getSizeY() {
        return sizeY;
    }
}
