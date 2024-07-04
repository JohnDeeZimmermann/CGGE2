package de.cg.cgge.io;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

    private String path;

    private Clip clip;
    private AudioInputStream ais;

    /**
     * Creates a sound instance and calls an init() method
     * @param path The path of the sound
     * @throws UnsupportedAudioFileException Exception
     * @throws IOException Exception
     * @throws LineUnavailableException Exception
     */
    public Sound(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.path = path;
        this.clip = AudioSystem.getClip();
        
        init();        

    }

    /**
     * Plays the sound once
     */
    public void play() {
        clip.setMicrosecondPosition(0);
        clip.start(); 
    }

    /**
     * Loops through the sound
     * @param times The amount of times, the sound should be looped; Give -1 for an endless loop
     */
    public void loop(int times) {
        clip.loop(times);
    }

    /**
     * Resets the sound.
     * It calls a stop() and an init()
     * @throws LineUnavailableException Exception
     * @throws IOException Exception
     * @throws UnsupportedAudioFileException Exception
     */
    public void reset() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        stop();
        init();
    }

    /**
     * Stops the sound
     */
    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
            clip.flush();
            clip.setFramePosition(0);
        }
    }

    /**
     * Deletes the file from memorsy
     */
    public void erase() {
        clip.flush();
        clip.close();
        clip = null; 
        ais = null; 
    }

    /**
     * Loads the file to memory
     * @throws LineUnavailableException Exception
     * @throws IOException Exception
     * @throws UnsupportedAudioFileException Exception
     */
    private void init() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        this.ais = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile()); 
        clip.open(ais);
        
    }

    /**
     * @return Returns the position of the track in microseconds
     */
    public long getPosition() {
        return clip.getMicrosecondPosition();
    }

    /**
     * Sets the postion of the sound
     * @param pos Current position in milliseconds
     */
    public void setPosition(long pos) {
        clip.setMicrosecondPosition(pos);
    }






}
