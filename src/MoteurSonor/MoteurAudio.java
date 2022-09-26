package MoteurSonor;

import MainPack.RunGame;

import javax.sound.sampled.*;
import java.io.File;

public class MoteurAudio extends Thread{

    /**
     * Les variable suivante permet d'associé un nom a un int, pour simplifié l'apelle de certaine methode en dehors de la class
     */
    public static int SOUND_GAME_MUSIC = 0;
    public static int SOUND_GAME_REBOND = 1;
    public static int SOUND_GAME_YOU_WIN = 2;


    /**
     *Les trois constante suivante gère le gain de chaque son
     */
    private static double doubleGainGameClip = 0.5;
    private static double doubleGainRebodClip = 0.1;
    private static double doubleGainYouWinClip = 0.5;


    private RunGame runGame;/** Variable permetant de stocké une référance vers l'object qui genere celle ci (inutilisé dans ce cas mais mise par reflexe et potantiellement utille dans le future) */


    /**
     * Les 6 variable suivante vont généré le chemain static des fichier audio et leur Clip (Variable permetant de joué le son) respectif
     */
    private static File gameFile = new File(System.getProperty("user.dir") + pathForMyOs("/sound/game.wav"));
    private static Clip gameClip;
    private static File rebondFile = new File(System.getProperty("user.dir") + pathForMyOs("/sound/rebonndnn.wav"));
    private static Clip rebondClip;
    private static File youWinFile = new File(System.getProperty("user.dir") + pathForMyOs("/sound/youWin.wav"));
    private static Clip youWinClip;


    /**
     *      MoteurAudio
     *
     * @param runGame
     *
     * Constructeur de la classe
     */
    public MoteurAudio(RunGame runGame) {
        this.runGame = runGame;/** Stock la referance vers l'object qui le connstruit */

            try{/** On commance par un try pour que l'e programe ne plante pas si l'un des fichier ne donne pas l'acces au son ou si l'une des methode utilisé ci dessous "throw" */

                float dB; /** variable permetant de stocke le gain de chaque clip pour le donné a sont Clip */


                /**
                 * Genere le Clip en fonction du chemain defini dans les static de la classe
                 * Calcule le gain de chaque clip en fonction des variable static de la classe entre 0 et 1 les convertie de db
                 */
                gameClip = AudioSystem.getClip();
                gameClip.open(AudioSystem.getAudioInputStream(gameFile));
                FloatControl gainGameClip = (FloatControl) gameClip.getControl(FloatControl.Type.MASTER_GAIN);
                dB = (float) (Math.log(doubleGainGameClip) / Math.log(10.0) * 20.0);
                gainGameClip.setValue(dB);

                rebondClip = AudioSystem.getClip();
                rebondClip.open(AudioSystem.getAudioInputStream(rebondFile));
                FloatControl gainRebondClip = (FloatControl) rebondClip.getControl(FloatControl.Type.MASTER_GAIN);
                dB = (float) (Math.log(doubleGainRebodClip) / Math.log(10.0) * 20.0);
                gainRebondClip.setValue(dB);

                youWinClip = AudioSystem.getClip();
                youWinClip.open(AudioSystem.getAudioInputStream(youWinFile));
                FloatControl gainYouWinClip = (FloatControl) youWinClip.getControl(FloatControl.Type.MASTER_GAIN);
                dB = (float) (Math.log(doubleGainYouWinClip) / Math.log(10.0) * 20.0);
                gainYouWinClip.setValue(dB);

            } catch (Exception e){/** Si jamais il y a une erreur */
                System.out.println("Eror while loading sound"); /** Indique le type d'erreur dans la console */
                System.out.println(e);/** Affiche le détaille de l'erreur dans la console (utile pour le debug) */
            }
    }


    /**
     *      playSound
     *
     * @param nameOfFile
     *
     * Methode permetant de joué un son en fonction d'un int (Static public definit plus haut)
     * Si le son a joué est celui de la musique on le joue en boucle
     * Sinon on joue le son demandé a partire du début
     *
     */
    public void playSound(int nameOfFile){
        try{
            if (nameOfFile == SOUND_GAME_MUSIC){
                gameClip.loop(999);/** Joue le son en boucle 999 fois */
            } else if (nameOfFile == SOUND_GAME_REBOND) {
                rebondClip.setFramePosition(0); /** Place/replace le curseur de lecture a 0 */
                rebondClip.start();/** Lance le son */
            } else if (nameOfFile == SOUND_GAME_YOU_WIN) {
                youWinClip.setFramePosition(0);/** Place/replace le curseur de lecture a 0 */
                youWinClip.start();/** Lance le son */
            }
        } catch (Exception e){
            System.out.println("Fail to play sound");
            System.out.println(e);
        }
    }


    /**
     *      pathForMyOs
     *
     * @param path
     * @return Le chemain adapté a l'OS utilisé
     *
     * Methose permetant de changer les \ en / en fonction de l'os (Windows 10 / Linux)
     */
    private static String pathForMyOs(String path){
        switch (System.getProperty("os.name")) {
            case "Linux":
                path = path.replace('\\', '/');
                break;
            case "Windows 10":
                path = path.replace('/', '\\');
                break;
            default:
                System.out.println(System.getProperty("os.name"));
                System.exit(1);
        }
        return path;
    }

}
