package PackPhysique.Component;

import PackPhysique.Component.Component;
import PackPhysique.Map;

public class Balle extends Component {

    private static boolean isMovable = true;
    private static boolean onHitComponentEvent = true;


    public Balle(Map map, double x, double y, double sizeX, double sizeY, boolean isMovable, boolean onHitComponentEvent) {
        super(map, x, y, sizeX, sizeY, isMovable, onHitComponentEvent);
    }

    @Override
    public void run(){
        super.run();
        this.setVitesseY(-150);
    }

    @Override
    public void onStopXEvent(){
        rebond();
    }
    @Override
    public void onStopYEvent(){
        rebond();
    }
    @Override
    public void onHitEvent(Component component){
        component.terminate();
        getMap().deleteAComponent(component);
    }

    public void rebond(){
        this.setVitesseX(this.getVitesseX()*-1);
        this.setVitesseY(this.getVitesseY()*-1);
    }




}
