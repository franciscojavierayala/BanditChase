package juego;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Ventana que muestra el estado de "Game Over" cuando el jugador pierde en el juego.
 * Permite al jugador volver al menú principal o jugar de nuevo.
 * 
 * @author Francisco Javier Ayala Parejo.
 * @version 1.0
 */
public class GameOverWindow extends JFrame implements ActionListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private JLabel scoreLabel;
    private JButton playAgainButton;
    private JButton closeButton;
    private Clip clip;

    
    /**
     * Construye una ventana de "Game Over" con la puntuación dada.
     * @param score La puntuación obtenida por el jugador.
     */
    public GameOverWindow(int score) {
        super("Game Over");
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/imagenes/chocaste.jpg"));
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        backgroundPanel.add(Box.createVerticalStrut(700));

        scoreLabel = new JLabel("Puntuación: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 80));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backgroundPanel.add(scoreLabel);
        backgroundPanel.add(Box.createVerticalStrut(1));

        playAgainButton = new JButton("Jugar de nuevo");
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.addActionListener(this);
        playAgainButton.setFont(new Font("Impact", Font.BOLD, 60));
        playAgainButton.setBackground(Color.GREEN);
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setFocusable(false);
        backgroundPanel.add(playAgainButton);
        backgroundPanel.add(Box.createVerticalStrut(20));

        closeButton = new JButton("Volver al menú principal");
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.addActionListener(this);
        closeButton.setFont(new Font("Impact", Font.BOLD, 60));
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusable(false);
        backgroundPanel.add(closeButton);

        add(backgroundPanel, BorderLayout.CENTER);

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        playSound("/sonido/hasperdido.wav");
    }
    
    
    /**
     * Este método maneja las acciones realizadas por el usuario, como hacer clic en botones.
     * 
     * @param e El evento de acción generado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Volver al menú principal")) {
            stopSound();
            dispose();
            MenuPrincipal mainMenu = new MenuPrincipal();
            mainMenu.setVisible(true);
        } else if (command.equals("Jugar de nuevo")) {
            stopSound();
            dispose();
            iniciarJuego();
        }
    }

    
    /**
     * Inicia una nueva instancia del juego.
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
     * Este método se llama automáticamente cuando se pulsa y se suelta una tecla que genera un carácter.
     * 
     * @param e El evento de teclado generado.
     */
    @Override
    public void keyTyped(KeyEvent e) {}
    
    
    /**
     * Este método se llama automáticamente cuando se pulsa una tecla.
     * 
     * @param e El evento de teclado generado.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            playAgainButton.doClick();
        } else if (key == KeyEvent.VK_ESCAPE) {
            closeButton.doClick();
        }
    }
    
    
    /**
     * Este método se llama automáticamente cuando se suelta una tecla.
     * 
     * @param e El evento de teclado generado.
     */
    @Override
    public void keyReleased(KeyEvent e) {}

    
    /**
     * Reproduce un sonido almacenado en el archivo especificado.
     * @param filepath La ruta del archivo de sonido.
     */
    private void playSound(String filepath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filepath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Detiene la reproducción del sonido actual, si hay alguno.
     */
    private void stopSound() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
