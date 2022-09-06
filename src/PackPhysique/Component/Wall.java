package PackPhysique.Component;

import PackPhysique.Map;

public class Wall extends Component{

    private boolean isMovable = false;
    private boolean onHitComponentEvent = false;


    public Wall(Map map, double x, double y, double sizeX, double sizeY, boolean isMovable, boolean onHitComponentEvent) {
        super(map, x, y, sizeX, sizeY, isMovable, onHitComponentEvent);
    }
}
