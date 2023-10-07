package pl.taw;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {

    public static final File SECONDS_LEFT = new File("src/main/resources/sound/seconds_left.wav");
    public static final File END_GAME = new File("src/main/resources/sound/end_game.wav");
    public static final File CORRECT_ANSWER = new File("src/main/resources/sound/correct_answer.wav");
    public static final File WRONG_ANSWER = new File("src/main/resources/sound/wrong_answer2.wav");
    public static final File TIME_IS_UP = new File("src/main/resources/sound/wrong_answer.wav");


    public static void playSound(File file) {
        if (!file.exists()) {
            throw new RuntimeException("File not exists!!!!");
        }
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
