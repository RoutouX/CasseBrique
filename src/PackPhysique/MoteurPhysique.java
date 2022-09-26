package PackPhysique;

import MainPack.RunGame;

public class MoteurPhysique extends Thread{

    private RunGame runGame;/** Stock la référance/l'adresse a l'object qui la crée */
    private Map map;/** Stock l'object map qui contiennt tout les component de la map */


    /**
     *      MoteurPhysique
     *
     * @param runGame
     *
     * Constructeur de la classe
     * Stock le référance de l'object qui la crée
     * Genere une instance de type Map
     */
    public MoteurPhysique(RunGame runGame) {
        this.runGame = runGame;
        this.map = new Map(this);
    }


    /**
     *      run
     *
     * démare la classe run
     */
    @Override
    public void run() {
        super.run();
        map.run();
    }


    /**
     * Les methode suivante sont les get et set des variable
     */
    public Map getMap() {
        return map;
    }

    public RunGame getRunGame() {
        return runGame;
    }
}
