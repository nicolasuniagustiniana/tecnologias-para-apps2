

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class reproductor {

    public static void main(String[] args) {
        // Crear un objeto Runnable para reproducir la canción en un hilo separado
        Runnable songRunnable = () -> {
            try {
                File songFile = new File("C:\\Users\\USER\\Downloads\\cancion\\y2mate.com-el-malo-remix-letra-Nico-Hernández-Jessi-Uribe-Pipe-bueno-copia.wav"); 
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(songFile));
                clip.start();
                Thread.sleep(clip.getMicrosecondLength() / 1000); // Esperar hasta que termine la canción
            } catch (IOException | InterruptedException | LineUnavailableException | UnsupportedAudioFileException e) {
            }
        };
        
        // hilo para reproducir la canción
        Thread songThread = new Thread(songRunnable);
        songThread.start();
        
        // Pedir datos al usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
          System.out.print("Ingrese su apellido: ");
        String apellido = scanner.nextLine();
          System.out.print("Ingrese su usuario: ");
        String usuario= scanner.nextLine();
        System.out.println("Hola, " + usuario + apellido + "! La canción está reproduciéndose en segundo plano.");
        
        // Esperar a que el hilo de la canción termine
        try {
            songThread.join();
        } catch (InterruptedException e) {
        }
        
        System.out.println("Se ha terminado de reproducir.");
    }
}
