package MainPack;

import MoteurSonor.MoteurAudio;
import PackGraphique.MoteurGraphique;
import PackPhysique.MoteurPhysique;

public class RunGame extends Thread{

    private MoteurPhysique moteurPhysique;
    private MoteurGraphique moteurGraphique;
    private MoteurAudio moteurAudio;

    public RunGame() {
        moteurAudio = new MoteurAudio(this);
        moteurPhysique = new MoteurPhysique(this);
        moteurGraphique = new MoteurGraphique(this);
        moteurPhysique.run();
        moteurGraphique.run();
    }

    public MoteurAudio getMoteurAudio() {
        return moteurAudio;
    }

    public MoteurPhysique getMoteurPhysique() {
        return moteurPhysique;
    }

    public MoteurGraphique getMoteurGraphique() {
        return moteurGraphique;
    }
}
