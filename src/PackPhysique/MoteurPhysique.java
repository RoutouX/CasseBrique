package PackPhysique;

import MainPack.RunGame;

public class MoteurPhysique extends Thread{

    private RunGame runGame;//Invoker
    private Map map;//Invoked

    public MoteurPhysique(RunGame runGame) {
        this.runGame = runGame;
        this.map = new Map(this);
    }

    @Override
    public void run() {
        super.run();
        map.run();
    }

    public Map getMap() {
        return map;
    }

    public RunGame getRunGame() {
        return runGame;
    }
}
