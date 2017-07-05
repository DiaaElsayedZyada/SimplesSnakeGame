import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, KeyListener {

	private static final int pixel = 25;

	private Color[] color = { Color.WHITE, Color.GREEN, Color.BLUE, Color.YELLOW, Color.DARK_GRAY, Color.PINK,
			Color.RED, Color.ORANGE };

	private int length = 3;

	int colornum = 8;

	private Timer t;

	// directions
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;

	private static final int speed = 50;

	private boolean bool;

	private Random random = new Random();

	private long now, last;

	private int foodx = random.nextInt(900);
	private int foody = random.nextInt(700);

	private int Xarray[] = new int[255];
	private int Yarray[] = new int[255];

	private int delay = 0;

	public Game() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		t = new Timer(speed, this);
		t.start();

		Xarray[2] = 50;
		Xarray[1] = 75;
		Xarray[0] = 100;

		Yarray[2] = 100;
		Yarray[1] = 100;
		Yarray[0] = 100;
	}

	@Override
	public void paint(Graphics g) {

		g.clearRect(0, 0, 905, 700);
		g.setColor(color[random.nextInt(colornum)]);
		g.drawRect(10, 10, 880, 680);
		g.setColor(color[random.nextInt(colornum)]);
		g.drawRect(5, 5, 890, 690);

		// score
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score : " + (length - 3), 780, 30);
		// length
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("length : " + length, 780, 50);

		for (int a = 0; a < length; a++) {

			// head
			if (a == 0) {

				g.setColor(color[random.nextInt(colornum)]);

				g.fillOval(Xarray[0], Yarray[0], 25, 25);
			}
			if (a != 0) {
				g.setColor(color[random.nextInt(colornum)]);
				g.fillRect(Xarray[a], Yarray[a], 25, 25);
			}

		}

		if (((Xarray[0] <= foodx + 30) && (foodx - 30 <= Xarray[0]))
				&& ((Yarray[0] <= foody + 30) && (foody - 30 <= Yarray[0]))) {
			length++;
			foodx = random.nextInt(850);
			foody = random.nextInt(650);

		}
		g.setColor(color[random.nextInt(colornum)]);
		g.fillOval((foodx + 15), (foody + 15), 20, 20);

		for (int b = 1; b < length; b++) {
			if ((Xarray[0] == Xarray[b] && Yarray[0] == Yarray[b])
					|| (Xarray[0] <= 10 || Xarray[0] >= 880 || Yarray[0] == 10 || Yarray[0] > 680)) {
				right = false;
				left = false;
				up = false;
				down = false;
				g.setColor(color[random.nextInt(colornum)]);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);

				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Move to restart", 350, 340);

				length = 3;

				Xarray[2] = 50;
				Xarray[1] = 75;
				Xarray[0] = 100;

				Yarray[2] = 100;
				Yarray[1] = 100;
				Yarray[0] = 100;
			}
		}

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (right) {
			for (int r = length - 1; r >= 0; r--) {
				Xarray[r + 1] = Xarray[r];
			}
			for (int r = length; r >= 0; r--) {
				if (r == 0) {
					Xarray[r] = Xarray[r] + 25;
				} else {
					Yarray[r] = Yarray[r - 1];
				}
				if (Yarray[r] > 850) {
					Yarray[r] = 25;
				}
				if (Xarray[r] > 900) {
					Xarray[r] = 0;
				}
			}
			repaint();

		}
		if (left) {
			for (int r = length - 1; r >= 0; r--) {
				Xarray[r + 1] = Xarray[r];
			}
			for (int r = length; r >= 0; r--) {
				if (r == 0) {
					Xarray[r] = Xarray[r] - 25;
				} else {
					Yarray[r] = Yarray[r - 1];
				}
				if (Yarray[r] > 850) {
					Yarray[r] = 25;
				}
				if (Xarray[r] < 0) {
					Xarray[r] = 900;
				}

			}
			repaint();

		}
		if (up) {
			for (int r = length - 1; r >= 0; r--) {
				Yarray[r + 1] = Yarray[r];
			}
			for (int r = length; r >= 0; r--) {
				if (r == 0) {
					Yarray[r] = Yarray[r] - 25;
				} else {
					Xarray[r] = Xarray[r - 1];
				}
				if (Xarray[r] > 850) {
					Xarray[r] = 25;
				}
				if (Yarray[r] < 0) {
					Yarray[r] = 700;
				}
			}
			repaint();

		}
		if (down) {
			for (int r = length - 1; r >= 0; r--) {
				Yarray[r + 1] = Yarray[r];
			}
			for (int r = length; r >= 0; r--) {
				if (r == 0) {
					Yarray[r] = Yarray[r] + 25;
				} else {
					Xarray[r] = Xarray[r - 1];
				}
				if (Xarray[r] > 850) {
					Xarray[r] = 25;
				}
				if (Yarray[r] > 700) {
					Yarray[r] = 0;
				}

			}
			repaint();

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			left = false;
			up = false;
			down = false;

		}
		if (key == KeyEvent.VK_LEFT && !right) {
			right = false;
			left = true;
			up = false;
			down = false;

		}
		if (key == KeyEvent.VK_UP && !down) {
			right = false;
			left = false;
			up = true;
			down = false;

		}
		if (key == KeyEvent.VK_DOWN && !up) {
			right = false;
			left = false;
			up = false;
			down = true;

		}
		
		

	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
