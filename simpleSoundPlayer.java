import java.io.File;
import java.io.IOException;
 
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.SourceDataLine;
 
public class simpleSoundPlayer {
 
    //defining the byte buffer
    private static final int BUFFER_SIZE = 4096;
     

    void play(String filePath) {
        File soundFile = new File(filePath);
        try {
          //convering the audio file to a stream
            AudioInputStream sampleStream = AudioSystem.getAudioInputStream(soundFile);
 
            AudioFormat formatAudio = sampleStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, formatAudio);
 
            SourceDataLine theAudioLine = (SourceDataLine) AudioSystem.getLine(info);
 
            theAudioLine.open(formatAudio);
 
            theAudioLine.start();
             
            System.out.println("Audio Player Started.");
             
            byte[] bufferBytes = new byte[BUFFER_SIZE];
            int readBytes = -1;
 
            while ((readBytes = sampleStream.read(bufferBytes)) != -1) {
                theAudioLine.write(bufferBytes, 0, readBytes);
            }
             
            theAudioLine.drain();
            theAudioLine.close();
            sampleStream.close();
             
            System.out.println("Playback has been finished.");
             
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported file.");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.out.println("Line not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Experienced an error.");
            e.printStackTrace();
        }      
    }
     
    public static void main(String[] args) {
        String thePath = "Nico  Vinz - Am I Wrong.wav";
        simpleSoundPlayer player = new simpleSoundPlayer();
        player.play(thePath);
    }
    public static void menu() {

        System.out.println("---- SpotifyLikeApp ----");
        System.out.println("[H]ome");
        System.out.println("[S]earch by title");
        System.out.println("[L]ibrary");
        System.out.println("[P]lay");
        System.out.println("[Q]uit");
        System.out.println("[F]avorte");
        System.out.println("1, pause");
        

        System.out.println("");
        System.out.print("Enter q to quit:");

    }
    public static void handleMenu(String userInput) {
        switch(userInput) {
            case "h":
                System.out.println("-->Home<--");
                break;

            case "s":
                System.out.println("-->Search by title<--");
                break;

            case "l":
                System.out.println("-->Library<--");
                break;

            case "p":
                System.out.println("-->Play<--");
                play();
                break;
            
            case "q":
                System.out.println("-->Quit<--");
                break;

            case "f":
                System.out.println("-->favorite<--");
                search();
            default:

                break;

        }

    }
    /*

     * plays an audio file

     */

    private static void search() {
    }

    public static void play() {
        // open the audio file
        final File file = new File("Nico  Vinz - Am I Wrong.wav");
        try {
            // create clip
            Clip audioClip = AudioSystem.getClip();

            // get input stream
            final AudioInputStream in = getAudioInputStream(file);
            audioClip.open(in);
            audioClip.setMicrosecondPosition(0);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch(Exception e) {
            e.printStackTrace();

        }


 

    }

    private static AudioInputStream getAudioInputStream(File file) {
        return null;
    }



 

}
 
