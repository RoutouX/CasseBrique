package PackGraphique;

import MainPack.RunGame;

import java.util.concurrent.TimeUnit;

public class MoteurGraphique extends Thread{

    private RunGame runGame;
    private FenetreJFrame fenetreJFrame;

    public MoteurGraphique(RunGame runGame) {
        this.runGame = runGame;
        this.fenetreJFrame = new FenetreJFrame(this);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Run Moteur Graphique");
        Thread refraiche = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    fenetreJFrame.revalidate();
                    fenetreJFrame.repaint();
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000/60);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        refraiche.start();
    }

    public FenetreJFrame getFenetreJFrame() {
        return fenetreJFrame;
    }

    public RunGame getRunGame() {
        return runGame;
    }
}
