package MainPack;

import PackGraphique.MoteurGraphique;
import PackPhysique.MoteurPhysique;

public class RunGame extends Thread{

    private MoteurPhysique moteurPhysique;
    private MoteurGraphique moteurGraphique;

    public RunGame() {
        moteurPhysique = new MoteurPhysique(this);
        moteurGraphique = new MoteurGraphique(this);
        moteurPhysique.run();
        moteurGraphique.run();
    }


    public MoteurPhysique getMoteurPhysique() {
        return moteurPhysique;
    }

    public MoteurGraphique getMoteurGraphique() {
        return moteurGraphique;
    }
}
