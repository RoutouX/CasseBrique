package PackPhysique.Component;

import PackPhysique.Map;

public class Wall extends Component{

    private static boolean isMovable = false;
    private static boolean onHitComponentEvent = false;


    public Wall(Map map, double x, double y, double sizeX, double sizeY) {
        super(map, x, y, sizeX, sizeY, isMovable, onHitComponentEvent);
    }
}
