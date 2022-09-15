package MoteurSonor;

import MainPack.RunGame;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.io.File;

public class MoteurAudio extends Thread{

    private RunGame runGame;

    private AudioFormat format;

    private AudioInputStream sondBounds;
    private Clip clipBounds;

    private AudioInputStream sondHit;
    private Clip clipHit;

    public MoteurAudio(RunGame runGame) {
        this.runGame = runGame;
            try{
                sondHit = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + pathForMyOs("/sound/theoAhhhh.wav")).getAbsoluteFile());
                clipHit = AudioSystem.getClip();
                clipHit.open(sondHit);
                //sondBounds = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + pathForMyOs("/sound/theoAhhhh.wav")));
                //clipBounds = AudioSystem.getClip();
                //clipBounds.open(sondBounds);
            } catch (Exception e){
                System.out.println("Eror while loading sound");
                System.out.println(e);
            }
    }

    public void playSoundBounds(){
        System.out.println("Play Sound");
        try{
            clipHit.setFramePosition(0);
            clipHit.start();
        } catch (Exception e){
            System.out.println("Fail to play sound");
            System.out.println(e);
        }

    }


    private String pathForMyOs(String path){
        switch (System.getProperty("os.name")) {
            case "Linux":
                path = path.replace('\\', '/');
                break;
            case "Windows":
                path = path.replace('/', '\\');
                break;
            default:
                System.exit(1);
        }
        return path;
    }

}
