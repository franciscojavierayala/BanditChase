package juego;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 * Clase que representa un coche en el juego.
 * Cada coche tiene un rectángulo asociado para la detección de colisiones y una imagen que lo representa visualmente.
 * 
 * @author Francisco Javier Ayala Parejo.
 * @version 1.0
 */
public class Coches {
    Rectangle rect; // Rectángulo que define el área del coche
    BufferedImage image; // Imagen que representa al coche

    
    /**
     * Constructor de la clase Coches.
     * 
     * @param x La coordenada x del coche.
     * @param y La coordenada y del coche.
     * @param width El ancho del coche.
     * @param height La altura del coche.
     * @param image La imagen que representa al coche.
     */
    Coches(int x, int y, int width, int height, BufferedImage image) {
        this.rect = new Rectangle(x, y, width, height); // Inicializa el rectángulo del coche
        this.image = image; // Asigna la imagen del coche
    }
}
