package MoteurSonor;

import MainPack.RunGame;

import javax.sound.sampled.*;
import java.io.File;

public class MoteurAudio extends Thread{

    public static int SOUND_GAME_MUSIC = 0;
    public static int SOUND_GAME_REBOND = 1;
    public static int SOUND_GAME_YOU_WIN = 2;



    private RunGame runGame;

    private static File gameFile = new File(System.getProperty("user.dir") + pathForMyOs("/sound/game.wav"));
    private static Clip gameClip;


    private static File rebondFile = new File(System.getProperty("user.dir") + pathForMyOs("/sound/rebonndnn.wav"));
    private static Clip rebondClip;

    private static File youWinFile = new File(System.getProperty("user.dir") + pathForMyOs("/sound/youWin.wav"));
    private static Clip youWinClip;

    public MoteurAudio(RunGame runGame) {
        this.runGame = runGame;
            try{
                gameClip = AudioSystem.getClip();
                gameClip.open(AudioSystem.getAudioInputStream(gameFile));

                rebondClip = AudioSystem.getClip();
                rebondClip.open(AudioSystem.getAudioInputStream(rebondFile));

                youWinClip = AudioSystem.getClip();
                youWinClip.open(AudioSystem.getAudioInputStream(youWinFile));

            } catch (Exception e){
                System.out.println("Eror while loading sound");
                System.out.println(e);
            }
    }

    public void playSound(int nameOfFile){
        System.out.println("Play Sound");
        try{
            if (nameOfFile == SOUND_GAME_MUSIC){
                gameClip.loop(999);
            } else if (nameOfFile == SOUND_GAME_REBOND) {
                rebondClip.setFramePosition(0);
                rebondClip.start();
            } else if (nameOfFile == SOUND_GAME_YOU_WIN) {
                youWinClip.start();
            }
        } catch (Exception e){
            System.out.println("Fail to play sound");
            System.out.println(e);
        }
    }


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
                System.out.println("1");
                System.exit(1);
        }
        return path;
    }

}
