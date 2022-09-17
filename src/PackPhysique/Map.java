package PackPhysique;

import MoteurSonor.MoteurAudio;
import PackPhysique.Component.*;

public class Map extends Thread{

    private MoteurPhysique moteurPhysique;
    private Component[] components;
    private int positionInListComponent;

    private int levelGame = 1;
    private int nnombreBriqueBase = 6;
    private int nombreBrique = 6;//36

    private static int SIZE_LIST_COMPONENT = 1000;

    public Map(MoteurPhysique moteurPhysique) {
        this.moteurPhysique = moteurPhysique;
        positionInListComponent = 0;
        components = new Component[SIZE_LIST_COMPONENT];}

    @Override
    public void run() {
        super.run();
        generateMap();
        addNewComponent(new Balle(this, moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeXScreen()/2 - (Balle.getSizeX()/2), moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeYScreen()-200, (400+200*levelGame), new Position(0,-100)));
        addNewComponent(new Raquette(this, moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeXScreen()/2 - Raquette.getSizeX()/2, moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeYScreen()-100));
    }

    public void generateMap(){
        int sizeXFenetre = moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeXScreen();
        int sizeYFenetre = moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeYScreen();

        int intSizeXBrique = (int)Brique.getSizeX();
        int intSizeYBrique = (int)Brique.getSizeY();

        addNewComponent(new Wall(this, -1, 0, 1, sizeYFenetre));//add leftWall
        addNewComponent(new Wall(this, sizeXFenetre, 0, 1, sizeYFenetre));//add rightWall
        addNewComponent(new Wall(this, 0, -1, sizeXFenetre, 1));//add topWall
        addNewComponent(new Fall(this, 0, sizeYFenetre+1, sizeXFenetre, 1));//add bottomFall

        int maxbriquePerLine = (int)Math.ceil((sizeXFenetre / intSizeXBrique));

        int positionXCurseurBase = ((sizeXFenetre - (intSizeXBrique*maxbriquePerLine))/2);
        int positionXCurseur = positionXCurseurBase;
        int positionYCureseur = 20;

        int nbBriquepose = 0;
        while (nbBriquepose < nombreBrique){
            for (int i = 0; ((i < maxbriquePerLine) && (nbBriquepose < nombreBrique)); i++){
                addNewComponent(new Brique(this, positionXCurseur, positionYCureseur));
                nbBriquepose++;
                positionXCurseur = positionXCurseur + intSizeXBrique;
            }
            positionXCurseur = positionXCurseurBase;
            positionYCureseur = positionYCureseur + intSizeYBrique;
        }
    }


    public void addNewComponent(Component component){
        components[positionInListComponent] = component;
        component.run();
        positionInListComponent++;
    }

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


    public void deleteAllComponent(){
        for (int i=0; i<SIZE_LIST_COMPONENT;i++){
            try{
                components[i].terminate();
            }catch (Exception e){}
            components[i] = null;
        }
        positionInListComponent = 0;
    }

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

    public void resetGame(){
        deleteAllComponent();
        generateMap();
        addNewComponent(new Balle(this, moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeXScreen()/2 - (Balle.getSizeX()/2), moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeYScreen()-200, (400+200*levelGame), new Position(0,-100)));
        addNewComponent(new Raquette(this, moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeXScreen()/2 - Raquette.getSizeX()/2, moteurPhysique.getRunGame().getMoteurGraphique().getFenetreJFrame().getSizeYScreen()-100));
    }

    public void resetGameIf0Balle(){
        if (balleExist() == false){
            resetGame();
        }
    }

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
