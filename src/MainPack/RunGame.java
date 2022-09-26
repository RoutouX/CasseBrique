package MainPack;

import MoteurSonor.MoteurAudio;
import PackGraphique.MoteurGraphique;
import PackPhysique.MoteurPhysique;

public class RunGame extends Thread{
    private MoteurPhysique moteurPhysique;/** Stocke en variable de classe le moteur physique (groupe des componenet, colision ets...) */
    private MoteurGraphique moteurGraphique;/** Stocke en variable de classe le moteur Graphique (fenetre, affichage des element etc...) */
    private MoteurAudio moteurAudio;/** Stocke en variable de classe le moteur physique (lance la musique etc...)*/


    /**
     * RunGame()
     *
     * Instancie les trois moteur, commance a joué le son d'ambiance
     * Démare le moteur phyqique
     * Démare le moteur Graphique
     */
    public RunGame() {
        moteurAudio = new MoteurAudio(this);
        moteurPhysique = new MoteurPhysique(this);
        moteurGraphique = new MoteurGraphique(this);
        moteurAudio.playSound(MoteurAudio.SOUND_GAME_MUSIC);
        moteurPhysique.run();
        moteurGraphique.run();
    }


    /**
     * Les fonction suivante sont les get/set des variable de la classe
     */
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
