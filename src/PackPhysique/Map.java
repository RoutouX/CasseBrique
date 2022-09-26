package PackPhysique;

import MoteurSonor.MoteurAudio;
import PackPhysique.Component.*;

public class Map extends Thread{

    private MoteurPhysique moteurPhysique;/** Stocke une referance en vers la classe qui la crée */
    private Component[] components;/** Tableau permetant de stocké tout les composant de la map */
    private int positionInListComponent;/** Curseur pour ce déplacé dans le tableau component principalement utilisé pour la génération */

    private int levelGame = 1;/** Niveau de la game, chaque niveau augement le nombre de brique et la vitesse innitial de la balle (incrémenté automatiquement a chaque fin de niveau) */
    private int nnombreBriqueBase = 6; /** Nombre de brique innitiale */
    private int nombreBrique = 6;/** Nombre de brique a généré */

    private static int SIZE_LIST_COMPONENT = 1000; /** Taille du tableau de component */


    /**
     *      Map
     * @param moteurPhysique
     *
     * Constructeur de la classe
     * Stock une referance vers la classe qui crée la map
     * innitialise la position du curseur a 0
     * Initialise le tableau de component
     */
    public Map(MoteurPhysique moteurPhysique) {
        this.moteurPhysique = moteurPhysique;
        positionInListComponent = 0;
        components = new Component[SIZE_LIST_COMPONENT];}


    /**
     *      run
     *  genere une map en fonction du niveau (brique, mur, etc...)
     *  ajoute une Balle et une Raquete, et bien evidament démare tout les composant
     */
    @Override
    public void run() {
        super.run();
        generateMap();
        addNewComponent(new Balle(this, moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeXScreen()/2 - (Balle.getSizeX()/2), moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeYScreen()-200, (400+200*levelGame), new Position(0,-100)));
        addNewComponent(new Raquette(this, moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeXScreen()/2 - Raquette.getSizeX()/2, moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeYScreen()-100));
    }


    /**
     *      generateMap
     *  Genere une map en fonction du niveau et de la taille de l'ecran
     */
    public void generateMap(){
        int sizeXFenetre = moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeXScreen();/** Cherche et stock la taille en largeur de la fenetre */
        int sizeYFenetre = moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeYScreen();/** Cherche et stock la taille en longueur de la fenetre */

        int intSizeXBrique = (int)Brique.getSizeX();
        int intSizeYBrique = (int)Brique.getSizeY();

        addNewComponent(new Wall(this, -1, 0, 1, sizeYFenetre));/** Ajoute un mur a gauche de la fenetre */
        addNewComponent(new Wall(this, sizeXFenetre, 0, 1, sizeYFenetre));/** Ajoute un mur a droite de la fenetre */
        addNewComponent(new Wall(this, 0, -1, sizeXFenetre, 1));/** Ajoute un mur enn haut de la fenetre */
        addNewComponent(new Fall(this, 0, sizeYFenetre+1, sizeXFenetre, 1));/** Ajoute un mur qui détruit la balle en cas de colision en bas de la fenetre */

        int maxbriquePerLine = (int)Math.ceil((sizeXFenetre / intSizeXBrique));/** Calcule le nombre de brique maximum par ligne */

        int positionXCurseurBase = ((sizeXFenetre - (intSizeXBrique*maxbriquePerLine))/2); /** Calcule ou vas etre posé en X la premiere brique */
        int positionXCurseur = positionXCurseurBase;
        int positionYCureseur = 20;/** Définit ou vas etre posé en Y la premiere brique */

        int nbBriquepose = 0;/** Variable qui vas stocké le nombre de brique posé */
        while (nbBriquepose < nombreBrique){ /** Tant que le nombre de brique posé est inferieur au nombre de brique voulu */
            for (int i = 0; ((i < maxbriquePerLine) && (nbBriquepose < nombreBrique)); i++){ /** Pour x brique par ligne et tant que le compteur de brique est inferieur au nombre de brique voulu*/
                addNewComponent(new Brique(this, positionXCurseur, positionYCureseur)); /** On pose une brique a la position du curseur de brique*/
                nbBriquepose++; /** On incrémente le nombre de brique posé */
                positionXCurseur = positionXCurseur + intSizeXBrique; /** déplace le curseur de brique en X a droite de la brique precédente */
            }
            /** Quand la ligne est terminer*/
            positionXCurseur = positionXCurseurBase; /** On remet le curseur de brique X a sa position initiale*/
            positionYCureseur = positionYCureseur + intSizeYBrique; /** On décent le cuseur de brique Y de la taille d'une brique */
        }
    }


    /**
     *      addNewComponent
     * @param component
     *
     * Ajoute un comoponent a la liste de componennt, a la positionn du curseur
     * Démare le component
     * incrémente le curseur
     */
    public void addNewComponent(Component component){
        components[positionInListComponent] = component;
        component.run();
        positionInListComponent++;
    }


    /**
     *      deleteAComponent
     * @param component
     *
     * Cherche le component envoyé dans la liste de component
     *      lorquil le trouve le suprime;
     * Verifie si le niveau est gagnner si elle l'est
     *      joue le son de niveau gagner
     *      incrément le level
     *      reinitialise la map
     */
    public void deleteAComponent(Component component){
        for (int i=0; i<SIZE_LIST_COMPONENT;i++){
            if (components[i] != null){
                if (components[i].getId() == component.getId()){
                    components[i] = null;
                    break;
                }
            }
        }
        if (checkWin()){
            System.out.println("Win");
            moteurPhysique.getRunGame().getMoteurAudio().playSound(MoteurAudio.SOUND_GAME_YOU_WIN);
            levelGame++;
            nombreBrique = nnombreBriqueBase * levelGame;
            resetGame();
        }
    }


    /**
     *      checkWin
     * @return boolean si le niveau est gagner
     *
     * Verifie si il reste des brique dans la liste de component
     *      si il ne n'en reste pas le niveau est gagner
     */
    public boolean checkWin(){
        for (int i=0; i<SIZE_LIST_COMPONENT;i++){
            if (components[i] != null) {
                if (components[i].getClass() == Brique.class) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     *      deleteAllComponent
     *
     * Réinitialise le tableau de component
     * remet le curseur a 0
     */
    public void deleteAllComponent(){
        for (int i=0; i<SIZE_LIST_COMPONENT;i++){
            try{
                components[i].terminate();
            }catch (Exception e){}
            components[i] = null;
        }
        positionInListComponent = 0;
    }

    /**
     *      getListComponent
     * @return La liste de tout les component de la map
     *
     * Compte le nombre de component dans le tableau de component
     * Genere une liste de cette meme taille
     * insere les referance des component dans la nouvelle liste
     * renvoie la nouvelle liste
     */
    public Component[] getListComponent() {
        int i = 0;
        for (int x = 0; x < SIZE_LIST_COMPONENT; x++){
            if(components[x]!=null){
                i++;
            }
        }
        Component[] returnListComponent = new Component[i];
        i = 0;
        for (int x = 0; x < SIZE_LIST_COMPONENT; x++){
            if(components[x]!=null){
                returnListComponent[i] = components[x];
                i++;
            }
        }
        return returnListComponent;
    }


    /**
     *      resetGame
     *
     * supprime tout les component de la liste
     * genere une nouvelle map
     * ajoute une raquette et une balle
     */
    public void resetGame(){
        deleteAllComponent();
        generateMap();
        addNewComponent(new Balle(this, moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeXScreen()/2 - (Balle.getSizeX()/2), moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeYScreen()-200, (400+200*levelGame), new Position(0,-100)));
        addNewComponent(new Raquette(this, moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeXScreen()/2 - Raquette.getSizeX()/2, moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeYScreen()-100));
    }


    /**
     *      resetGameIf0Balle
     *
     * Vérifie si la balle est encore dans la liste de composent
     *      si elle n'y est plus on reste la carte
     */
    public void resetGameIf0Balle(){
        if (balleExist() == false){
            resetGame();
        }
    }

    /**
     *      balleExist
     * @return boolean si la balle existe encore dans la map ou pas
     *
     * on verfie dans l'integralité du tableau de la liste de component si il y a encore une balle
     */
    public boolean balleExist(){
        for (int i=0; i<SIZE_LIST_COMPONENT;i++){
            if (components[i] != null) {
                if (components[i].getClass() == Balle.class) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Les methose suivante sont les get/set des variable de la classe
     */
    public MoteurPhysique getMoteurPhysique() {
        return moteurPhysique;
    }

    public int getLevelGame() {
        return levelGame;
    }

    public int getNnombreBriqueBase() {
        return nnombreBriqueBase;
    }

    public int getNombreBrique() {
        return nombreBrique;
    }
}
