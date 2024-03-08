package juego;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * La clase MenuPrincipal representa la ventana del menú principal del juego.
 * Permite al jugador seleccionar la dificultad del juego y comenzar a jugar o salir del juego.
 * 
 * @author Francisco Javier Ayala Parejo.
 * @version 1.0
 */

public class MenuPrincipal extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton playButton;
    private JButton exitButton;
    private Clip clip;
    private JComboBox<String> difficultySelector;

    
    /**
     * Constructor de la clase MenuPrincipal.
     * Configura la ventana del menú principal, incluyendo los botones, la selección de dificultad y el fondo.
     */
    public MenuPrincipal() {
        super("Menú Principal");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Configuración de los botones de Jugar y Salir
        playButton = new JButton("Jugar");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.addActionListener(this);
        playButton.setFont(new Font("Impact", Font.BOLD, 60));
        playButton.setBackground(new Color(255, 165, 0));
        playButton.setForeground(new Color(255, 253, 208));
        playButton.setFocusable(false);

        exitButton = new JButton("Salir");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(this);
        exitButton.setFont(new Font("Impact", Font.BOLD, 60));
        exitButton.setBackground(new Color(255, 165, 0));
        exitButton.setForeground(new Color(255, 253, 208));
        exitButton.setFocusable(false);

        // Panel de fondo con imagen de fondo y disposición de los componentes
        JPanel backgroundPanel = new JPanel() {
            private static final long serialVersionUID = 1L;
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(getClass().getResource("/imagenes/mainmenu.jpg"));
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        backgroundPanel.add(Box.createVerticalGlue());
        backgroundPanel.add(Box.createVerticalGlue());
        backgroundPanel.add(Box.createVerticalStrut(700));
        backgroundPanel.add(playButton);
        backgroundPanel.add(Box.createVerticalStrut(20));
        backgroundPanel.add(exitButton);
        backgroundPanel.add(Box.createVerticalGlue());

        // Selector de dificultad
        String[] difficultyOptions = {"Muy Facil", "Fácil", "Normal", "Difícil", "Pesadilla"};
        difficultySelector = new JComboBox<>(difficultyOptions);
        difficultySelector.setAlignmentX(Component.CENTER_ALIGNMENT);
        difficultySelector.addActionListener(this);
        difficultySelector.setFont(new Font("Arial", Font.PLAIN, 30));
        difficultySelector.setMaximumSize(new Dimension(300, 50));
        difficultySelector.setFocusable(false);
        difficultySelector.setSelectedIndex(2);
        backgroundPanel.add(difficultySelector);
        backgroundPanel.add(Box.createVerticalStrut(10));

        // Configuración de la ventana principal
        setContentPane(backgroundPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Reproducir el sonido de fondo del menú principal
        playSound("/sonido/menu.wav");

        // Manejar eventos de teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    
    /**
     * Maneja los eventos de acción, como hacer clic en botones.
     * 
     * @param e El evento de acción generado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedDifficulty = (String) difficultySelector.getSelectedItem();
        switch (selectedDifficulty) {
            case "Muy Facil":
                Funcionamiento.setSpeed(1);
                break;
            case "Fácil":
                Funcionamiento.setSpeed(3);
                break;
            case "Normal":
                Funcionamiento.setSpeed(5);
                break;
            case "Difícil":
                Funcionamiento.setSpeed(7);
                break;
            case "Pesadilla":
                Funcionamiento.setSpeed(9);
                break;
            default:
                break;
        }

        if (e.getSource() == playButton) {
            stopSound();
            dispose();
            iniciarJuego();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    
    /**
     * Inicia el juego al presionar el botón de Jugar.
     */
    private void iniciarJuego() {
        JFrame juego = new JFrame();
        juego.setExtendedState(JFrame.MAXIMIZED_BOTH);
        juego.setUndecorated(true);
        juego.setResizable(false);
        juego.setLocationRelativeTo(null);
        Funcionamiento game = new Funcionamiento();
        juego.add(game);
        juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        juego.setVisible(true);
    }

    
    /**
     * Reproduce un sonido de fondo.
     * 
     * @param filepath La ruta del archivo de audio.
     */
    private void playSound(String filepath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filepath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Detiene la reproducción del sonido actual.
     */
    private void stopSound() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    
    /**
     * Maneja las teclas presionadas para activar acciones, como seleccionar opciones o salir del juego.
     * 
     * @param e El evento de teclado generado.
     */
    private void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                playButton.doClick();
                break;
            case KeyEvent.VK_ESCAPE:
                exitButton.doClick();
                break;
            case KeyEvent.VK_1:
                difficultySelector.setSelectedIndex(0);
                break;
            case KeyEvent.VK_2:
                difficultySelector.setSelectedIndex(1);
                break;
            case KeyEvent.VK_3:
                difficultySelector.setSelectedIndex(2);
                break;
            case KeyEvent.VK_4:
                difficultySelector.setSelectedIndex(3);
                break;
            case KeyEvent.VK_5:
                difficultySelector.setSelectedIndex(4);
                break;
            default:
                break;
        }
    }

    
   
}