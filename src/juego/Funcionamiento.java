package juego;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


/**
 * Clase que controla el funcionamiento del juego.
 * Esta clase extiende JPanel y maneja la lógica del juego, incluyendo la interacción del usuario, movimiento de los coches y puntuación.
 * Implementa ActionListener y KeyListener para manejar eventos de tiempo y teclado respectivamente.
 * 
 * @author Francisco Javier Ayala Parejo.
 * @version 1.0
 */
public class Funcionamiento extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private int space;
	private int width = 70;
	private int height = 120;
	public static int speed;
	private int WIDTH = 500;
	private int HEIGHT = 700;
	private int playerMove = 5;
	private Rectangle coche;
	private ArrayList<Coches> coches;
	private int deceleration = 1;
	private int score = 0;
	private Random rand;
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private BufferedImage bg;
	private BufferedImage user;
	private BufferedImage coche1;
	private BufferedImage coche2;
	private BufferedImage coche3;
	private BufferedImage coche4;
	private BufferedImage coche5;
	private BufferedImage coche6;
	private BufferedImage coche7;
	private BufferedImage coche8;
	private BufferedImage coche9;
	private Timer t;
	private Clip clip;
	
	
	/**
     * Constructor de la clase Funcionamiento.
     * Inicializa las dimensiones y carga las imágenes necesarias.
     */
	public Funcionamiento() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int) screenSize.getWidth();
		HEIGHT = (int) screenSize.getHeight();
		try {
			bg = ImageIO.read(getClass().getResource("/imagenes/carretera1.jpg"));
			user = ImageIO.read(getClass().getResource("/imagenes/cocheuser.png"));
			coche1 = ImageIO.read(getClass().getResource("/imagenes/cocheazul.png"));
			coche2 = ImageIO.read(getClass().getResource("/imagenes/cocheceleste.png"));
			coche3 = ImageIO.read(getClass().getResource("/imagenes/cochelila.png"));
			coche4 = ImageIO.read(getClass().getResource("/imagenes/cochenaranja.png"));
			coche5 = ImageIO.read(getClass().getResource("/imagenes/cochenegro.png"));
			coche6 = ImageIO.read(getClass().getResource("/imagenes/cocherojo.png"));
			coche7 = ImageIO.read(getClass().getResource("/imagenes/cocherojo2.png"));
			coche8 = ImageIO.read(getClass().getResource("/imagenes/cocherosa.png"));
			coche9 = ImageIO.read(getClass().getResource("/imagenes/cocheverde.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		t = new Timer(5, this);
		rand = new Random();
		coches = new ArrayList<Coches>();
		coche = new Rectangle(WIDTH / 2 - 90, HEIGHT - 100, width, height);
		space = 350;
		addKeyListener(this);
		setFocusable(true);
		addcoches(true);
		addcoches(true);
		addcoches(true);
		addcoches(true);
		t.start();
		playSound("/sonido/juego.wav");
	}
	
	
	 /**
     * Dibuja la puntuación en la pantalla.
     * 
     * @param g El contexto gráfico en el que dibujar.
     */
	public void drawScore(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Puntuación: " + score, 10, 30);
	}
	
	
	/**
     * Dibuja los componentes del juego en el panel.
     * 
     * @param g El contexto gráfico en el que dibujar.
     */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(user, coche.x, coche.y, this);
		for (Coches car : coches) {
			g.drawImage(car.image, car.rect.x, car.rect.y, this);
		}
		drawScore(g);
	}
	
	
	/**
	 * Realiza el movimiento automático hacia atrás del coche del jugador cuando no se presiona ningún botón de dirección.
	 * 
	 * Si el coche alcanza el límite inferior de la pantalla, se detiene.
	 * 
	 * La velocidad de movimiento hacia atrás se determina por la variable de deceleración.
	 */
	public void moveBackwards() {
		if (coche.y + deceleration > HEIGHT - 100) {
			coche.y = HEIGHT - 100;
		} else {
			int backwardSpeed = downPressed ? 2 * deceleration : deceleration;
			coche.y += backwardSpeed;
		}
	}
	
	
	/**
     * Método que agrega coches al juego.
     * 
     * @param first Indica si es la primera llamada al método.
     */
	public void addcoches(boolean first) {
		boolean isLeft = rand.nextBoolean();
		int x;
		if (isLeft) {
			x = WIDTH / 2 + 50;
		} else {
			x = WIDTH / 2 - 85;
		}
		int y;
		BufferedImage carImage;
		if (first) {
			int randomCarIndex = rand.nextInt(9) + 1;
			switch (randomCarIndex) {
			case 1:
				carImage = coche1;
				break;
			case 2:
				carImage = coche2;
				break;
			case 3:
				carImage = coche3;
				break;
			case 4:
				carImage = coche4;
				break;
			case 5:
				carImage = coche5;
				break;
			case 6:
				carImage = coche6;
				break;
			case 7:
				carImage = coche7;
				break;
			case 8:
				carImage = coche8;
				break;
			case 9:
				carImage = coche9;
				break;
			default:
				carImage = null;
				break;
			}
			y = -100 - (coches.size() * space);
		} else {
			int randomCarIndex = rand.nextInt(9) + 1;
			switch (randomCarIndex) {
			case 1:
				carImage = coche1;
				break;
			case 2:
				carImage = coche2;
				break;
			case 3:
				carImage = coche3;
				break;
			case 4:
				carImage = coche4;
				break;
			case 5:
				carImage = coche5;
				break;
			case 6:
				carImage = coche6;
				break;
			case 7:
				carImage = coche7;
				break;
			case 8:
				carImage = coche8;
				break;
			case 9:
				carImage = coche9;
				break;
			default:
				carImage = null;
				break;
			}
			Coches lastCar = coches.get(coches.size() - 1);
			y = lastCar.rect.y - space;
		}
		coches.add(new Coches(x, y, width, height, carImage));
	}
	
	
	/**
     * Establece la velocidad del juego.
     * 
     * @param newSpeed La nueva velocidad.
     */
	public static void setSpeed(int newSpeed) {
		speed = newSpeed;
	}
	
	
	 /**
     * Maneja el evento de acción.
     * 
     * Actualiza el estado del juego en cada iteración.
     * 
     * @param e El evento de acción.
     */
	public void actionPerformed(ActionEvent e) {
	    boolean keyPressed = upPressed || downPressed || leftPressed || rightPressed;
	    for (Coches car : coches) {
	        car.rect.y += speed;
	        if (car.rect.intersects(coche)) {
	            if (coche.y == HEIGHT - 100) {
	                GameOverWindow endGameWindow = new GameOverWindow(score);
	                endGameWindow.setLocationRelativeTo(null);
	                endGameWindow.setVisible(true);
	                t.stop();
	                stopSound();
	                return;
	            } else if (car.rect.y + car.rect.height > coche.y) {
	                GameOverWindow endGameWindow = new GameOverWindow(score);
	                endGameWindow.setLocationRelativeTo(null);
	                endGameWindow.setVisible(true);
	                t.stop();
	                stopSound();
	                return;
	            }
	        }
	    }
		if (!keyPressed) {
			moveBackwards();
		}
		for (Coches car : coches) {
			car.rect.y += speed;
		}
		for (Coches r : coches) {
			if (r.rect.intersects(coche)) {
				coche.y = coche.y + height;
			}
		}
		for (int i = 0; i < coches.size(); i++) {
			Coches car = coches.get(i);
			if (car.rect.y + car.rect.height > HEIGHT) {
				coches.remove(car);
				addcoches(false);
				score++;
			}
		}
		for (int i = 0; i < coches.size(); i++) {
			Coches car = coches.get(i);
			if (car.rect.y + car.rect.height > HEIGHT) {
				coches.remove(car);
				addcoches(false);
			}
		}
		repaint();
		if (upPressed) {
			moveUp();
		}
		if (downPressed) {
			moveDown();
		}
		if (leftPressed) {
			moveLeft();
		}
		if (rightPressed) {
			moveRight();
		}
	}
	
	
	/**
     * Método que realiza el movimiento hacia arriba del coche del jugador.
     * 
     * Si el coche se encuentra en el límite superior de la pantalla, no se mueve.
     * 
     * Si no, se desplaza hacia arriba a una velocidad determinada por el estado de la tecla de aceleración.
     */
	public void moveUp() {
		if (coche.y - playerMove < 0) {
			System.out.println("/b");
		} else {
			coche.y -= (upPressed ? playerMove / 2 : playerMove);
		}
	}
	
	
	/**
     * Método que realiza el movimiento hacia abajo del coche del jugador.
     * 
     * Si el coche se encuentra en el límite inferior de la pantalla, no se mueve.
     * 
     * Si no, se desplaza hacia abajo a una velocidad determinada por el estado de la tecla de aceleración.
     */
	public void moveDown() {
		if (coche.y + playerMove + coche.height > HEIGHT - 1) {
			System.out.println("/b");
		} else {
			int downSpeed = downPressed ? 2 * playerMove : playerMove;
			coche.y += downSpeed;
			try {
				user = ImageIO.read(getClass().getResource("/imagenes/cocheatras.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
     * Método que realiza el movimiento hacia la izquierda del coche del jugador.
     * 
     * Si el coche se encuentra en el límite izquierdo de la pantalla, no se mueve.
     * 
     * Si no, se desplaza hacia la izquierda.
     */
	public void moveLeft() {
		if (coche.x - playerMove < WIDTH / 2 - 130) {
			System.out.println("/b");
		} else {
			coche.x -= playerMove;
			try {
				user = ImageIO.read(getClass().getResource("/imagenes/cocheizquierda.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
     * Método que realiza el movimiento hacia la derecha del coche del jugador.
     * 
     * Si el coche se encuentra en el límite derecho de la pantalla, no se mueve.
     * 
     * Si no, se desplaza hacia la derecha.
     */
	public void moveRight() {
		if (coche.x + playerMove > WIDTH / 2 + 110) {
			System.out.println("/b");
		} else {
			coche.x += playerMove;
			try {
				user = ImageIO.read(getClass().getResource("/imagenes/cochederecha.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
     * Este método se ejecuta cuando se presiona y se libera una tecla, pero no se ha hecho clic en ninguna tecla de función.
     * 
     * No se realiza ninguna acción en este método.     * 
     */
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	
	/**
     * Maneja el evento de tecla presionada.
     * 
     * Detecta qué tecla ha sido presionada y actualiza el estado correspondiente.
     * 
     * @param e El evento de tecla.
     */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			upPressed = true;
			break;
		case KeyEvent.VK_DOWN:
			downPressed = true;
			break;
		case KeyEvent.VK_LEFT:
			leftPressed = true;
			break;
		case KeyEvent.VK_RIGHT:
			rightPressed = true;
			break;
		default:
			break;
		}
	}
	
	
	/**
     * Maneja el evento de tecla liberada.
     * 
     * Detecta qué tecla ha sido liberada y actualiza el estado correspondiente.
     * 
     * @param e El evento de tecla.
     */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			upPressed = false;
			break;
		case KeyEvent.VK_DOWN:
			downPressed = false;
			try {
				user = ImageIO.read(getClass().getResource("/imagenes/cocheuser.png"));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		case KeyEvent.VK_LEFT:
			leftPressed = false;
			try {
				user = ImageIO.read(getClass().getResource("/imagenes/cocheuser.png"));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		case KeyEvent.VK_RIGHT:
			rightPressed = false;
			try {
				user = ImageIO.read(getClass().getResource("/imagenes/cocheuser.png"));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	
	
	/**
     * Reproduce un sonido.
     * 
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
     * Detiene la reproducción del sonido actual.
     */
	private void stopSound() {
		if (clip != null) {
			clip.stop();
			clip.close();
		}
	}
}
