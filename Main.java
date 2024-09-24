import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * To Do list
 * 
 * 
 * 
 * @Ryan Shafer @10/16/2020
 */
public class Main implements ActionListener, KeyListener, MouseListener, MouseWheelListener {
	JFrame f1;
	JPanel main, sub;
	MainGraphics g1;
	JButton b1, b2;
	GameBorder gb;
	Player Player;
	AI AI;
	Ball Ball;
	Gravity gravity;
	boolean attempted, songb, scored, Catch;
	boolean endgame, startgame, B1, B2, B3, FirstStart, B4, barrierswitch;
	String desireddirection, S1, dif, difp;
	int randomdirection, direction, pausecounter, ran, I1, x, time, TBDX, TBDY, index;
	int v, counter, rnd;
	Clip Bounce, Goal, Wall, song, Walls, Bouncep, Bounceb, saber, saberstart;
	ArrayList<Barrier> Barriers;
	Clip Start, dotf, idle, saberwall, saberpaddle, saberbarrier;
	ImageIcon icon;
	int counters;
	int ogb;
	int timer, ncounter;
	boolean okay2 = false;
	boolean duel = false;
	boolean f3 = false, mouse = false;
	boolean thing1;
	int ogx, updown;

	public Main() {
		SetValues();
		Sounds();
		makeGameBorders();
		makePlayer();
		makeBall();
		makeAI();
		setUpFrame();
		RunGame();
	}

	public void RunGame() {
		// Thread runner = new Thread();

		while (!endgame) {

			/** Sleep to Slow Down the Computer from taking over the world */
			try {
				Thread.sleep(x);
			} catch (InterruptedException e) {
			}

			if (startgame) {
				if (FirstStart) {
					Start.start();
					dif = "mixed";
				}
				Barrierscheck();
				Player.ChangeDirection(desireddirection);
				if (B1 == true)
					Player.alg(Ball, dif);
				else
					Player.move();
				if (counter == 0) {
					AI.alg(Ball, dif);
					counter = 0;
				} else
					counter--;
				AIHit();

				if (pausecounter == 0) {
					AI.move();
					Ball.move();
					pausecounter = Ball.Reset();
					gravity.move();
				} else
					pausecounter--;
				Barriers();
				HitDetect();
				strings();
				if (I1 == 0) {
					B4 = false;
				} else
					I1--;
				time++;
				misc();
				Gravitystuff();
				if (!Catch)
					if (gravity.getGravity())
						Ball.Gravity(updown);
				g1.updateSaber(duel, scored);
				g1.repaint();
				FirstStart = false;
				if (timer == 5) {
					sounds();
					timer = 0;
				} else
					timer++;
				if (ncounter <= 0 && !Ball.getCaught()) {
					Ball.setColor(false);
				} else
					ncounter--;
			}

		}
		g1.endupdate();
	}

	@SuppressWarnings("deprecation")
	private void setUpFrame() {
		f1 = new JFrame("Game");
		f1.setSize(gb.getSizeA() + 125, gb.getSizeB() + 150);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c1 = f1.getContentPane();
		g1 = new MainGraphics(Player, Ball, AI, gb, Barriers, gravity);
		g1.addKeyListener(this);
		g1.addMouseListener(this);
		g1.addMouseWheelListener(this);
		b1 = new JButton("Start");
		b1.addActionListener(this);

		b2 = new JButton("End");
		b2.addActionListener(this);

		sub = new JPanel();
		sub.add(b1);
		sub.add(b2);
		main = new JPanel();
		main.setLayout(new BorderLayout());
		main.setSize(600, 600);
		main.add(g1, BorderLayout.CENTER);
		main.add(sub, BorderLayout.SOUTH);
		main.setBackground(Color.black);

		c1.add(main);
		f1.show();
	}

	private void Sounds() {
		try {
			// First audio clip
			AudioInputStream BounceIn = AudioSystem.getAudioInputStream(new File("ding.wav"));
			Bounce = AudioSystem.getClip();
			Bounce.open(BounceIn);

			AudioInputStream BounceInp = AudioSystem.getAudioInputStream(new File("ding2.wav"));
			Bouncep = AudioSystem.getClip();
			Bouncep.open(BounceInp);

			AudioInputStream BounceInb = AudioSystem.getAudioInputStream(new File("ding3.wav"));
			Bounceb = AudioSystem.getClip();
			Bounceb.open(BounceInb);

			AudioInputStream GoalIn = AudioSystem.getAudioInputStream(new File("Start.wav"));
			Goal = AudioSystem.getClip();
			Goal.open(GoalIn);

			AudioInputStream WallIn = AudioSystem.getAudioInputStream(new File("ding.wav"));
			Wall = AudioSystem.getClip();
			Wall.open(WallIn);

			AudioInputStream WallIns = AudioSystem.getAudioInputStream(new File("ding4.wav"));
			Walls = AudioSystem.getClip();
			Walls.open(WallIns);

			AudioInputStream StartIn = AudioSystem.getAudioInputStream(new File("Goal.wav"));
			Start = AudioSystem.getClip();
			Start.open(StartIn);

			AudioInputStream songin = AudioSystem.getAudioInputStream(new File("song.wav"));
			song = AudioSystem.getClip();
			song.open(songin);

			AudioInputStream dotfin = AudioSystem.getAudioInputStream(new File("Duel.wav"));
			dotf = AudioSystem.getClip();
			dotf.open(dotfin);

			AudioInputStream saberin = AudioSystem.getAudioInputStream(new File("saber.wav"));
			saber = AudioSystem.getClip();
			saber.open(saberin);

			AudioInputStream saberstartin = AudioSystem.getAudioInputStream(new File("startup.wav"));
			saberstart = AudioSystem.getClip();
			saberstart.open(saberstartin);

			AudioInputStream idlein = AudioSystem.getAudioInputStream(new File("idle.wav"));
			idle = AudioSystem.getClip();
			idle.open(idlein);

			AudioInputStream saberwallin = AudioSystem.getAudioInputStream(new File("saber.wav"));
			saberwall = AudioSystem.getClip();
			saberwall.open(saberwallin);

			AudioInputStream saberpaddlein = AudioSystem.getAudioInputStream(new File("saber.wav"));
			saberpaddle = AudioSystem.getClip();
			saberpaddle.open(saberpaddlein);

			AudioInputStream saberbarrierin = AudioSystem.getAudioInputStream(new File("saber.wav"));
			saberbarrier = AudioSystem.getClip();
			saberbarrier.open(saberbarrierin);
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	private void popup() {
		startgame = false;
		dif = (String) JOptionPane.showInputDialog(f1, "Difficulty:easy, normal, hard, unbeatable", "Difficulty",
				JOptionPane.INFORMATION_MESSAGE, icon, null, "");
		startgame = true;
	}

	private void Barrierscheck() {

		if (Ball.getScoreLeft() == Ball.getScoreRight() && Barriers.size() > 2) {
			ogb = Barriers.size();
			for (index = 0; Barriers.size() > 2; index++) {
				Barriers.remove(0);

			}
			if (Barriers.size() <= 2) {
				okay2 = true;
			}
		}

		if (okay2 && Ball.getScoreLeft() != Ball.getScoreRight()) {
			for (index = 0; Barriers.size() < ogb; index++) {
				Barriers.add(new Barrier(Ball, Barriers));
			}
			okay2 = false;
			ogb = 0;
		}
	}

	private void sounds() {
		if (Bounce.getMicrosecondPosition() == Bounce.getMicrosecondLength()) {
			Bounce.setMicrosecondPosition(0);
		}
		if (Goal.getMicrosecondPosition() == Goal.getMicrosecondLength()) {
			Goal.setMicrosecondPosition(0);
		}
		if (Wall.getMicrosecondPosition() == Wall.getMicrosecondLength()) {
			Wall.setMicrosecondPosition(0);
		}
		if (Walls.getMicrosecondPosition() == Walls.getMicrosecondLength()) {
			Walls.setMicrosecondPosition(0);
		}
		if (Start.getMicrosecondPosition() == Start.getMicrosecondLength()) {
			Start.setMicrosecondPosition(0);
		}
		if (Bouncep.getMicrosecondPosition() == Bouncep.getMicrosecondLength()) {
			Bouncep.setMicrosecondPosition(0);
		}
		if (Bounceb.getMicrosecondPosition() == Bounceb.getMicrosecondLength()) {
			Bounceb.setMicrosecondPosition(0);
		}
		if (song.getMicrosecondPosition() == song.getMicrosecondLength()) {
			song.setMicrosecondPosition(0);
		}
		if (dotf.getMicrosecondPosition() == dotf.getMicrosecondLength()) {
			dotf.setMicrosecondPosition(0);
		}
		if (saber.getMicrosecondPosition() == saber.getMicrosecondLength()) {
			saber.setMicrosecondPosition(0);
		}
		if (saberstart.getMicrosecondPosition() == saberstart.getMicrosecondLength()) {
			saberstart.setMicrosecondPosition(0);
		}
		if (idle.getMicrosecondPosition() == idle.getMicrosecondLength()) {
			idle.setMicrosecondPosition(0);
		}
		if (saberwall.getMicrosecondPosition() == saberwall.getMicrosecondLength()) {
			saberwall.setMicrosecondPosition(0);
		}
		if (saberpaddle.getMicrosecondPosition() == saberpaddle.getMicrosecondLength()) {
			saberpaddle.setMicrosecondPosition(0);
		}
		if (saberbarrier.getMicrosecondPosition() == saberbarrier.getMicrosecondLength()) {
			saberbarrier.setMicrosecondPosition(0);
		}
	}

	private void Gravitystuff() {

		if (gravity.hitdetection(Ball).equals("Hit")) {
			gravity.setGravity(true);
			updown = (int) (Math.random() * 2 + 1);
		}

	}

	private void Barriers() {

		if ((time == 6000 * v && v < 21 && Barriers.size() < 5) || attempted) {
			if (!Ball.equalscore()) {
				Barriers.add(new Barrier(Ball, Barriers));
				v = v + 2;
				attempted = false;
			} else attempted = true;

		}

		for (index = 0; index < Barriers.size(); index++) {
			if (Barriers.get(index).getX() > Ball.getX() - Barriers.get(index).getSizeA() + 5
					&& Barriers.get(index).getX() < Ball.getX() + Ball.getSizeA() - 5
					&& Barriers.get(index).getY() > Ball.getY() - Barriers.get(index).getSizeB() + 5
					&& Barriers.get(index).getY() < Ball.getY() + Ball.getSizeB() - 5) {
				Barriers.remove(index);
				Barriers.add(new Barrier(Ball, Barriers));
			}

		}
		if (Ball.getScoreLeft() == Ball.getScoreRight() && Barriers.size() > 4) {
			for (index = 0; Barriers.size() > 5; index++) {
				Barriers.remove(index);
			}
		}

	}

	public void updateBarriers(ArrayList<Barrier> barriers) {
		Barriers = barriers;
	}

	private void Change() {
		gravity.setGravity(false);
		Barrierscheck();
		for (index = 0; index < Barriers.size(); index++)
			Barriers.get(index).ChangeXY(Ball, Barriers);
	}

	private void makeGameBorders() {
		gb = new GameBorder(0, 0, 750, 500);
	}

	private void makePlayer() {
		Player = new Player(40, 300, 15, 60, 0, 0);
		Player.setSpeed(3);
	}

	private void makeAI() {
		AI = new AI(700, 20, 15, 60, 0, 0);
	}

	private void makeBall() {
		if (randomdirection == 1)
			direction = 1;
		else
			direction = -1;
		Ball = new Ball(377, 250, 10, 10, direction, 1);
	}

	private void HitDetect() {
		if (Ball.getX() + Ball.getSizeA() < AI.getX() + AI.getSizeA() + (AI.getSizeA() / 2))
			if (AI.hitdetection(Ball).equals("HitX") || AI.hitdetection(Ball).equals("HitY")) {
				if (duel)
					if (saber.getMicrosecondPosition() == 0) {
						saberpaddle.start();
					} else {
					}
				else if (Bouncep.getMicrosecondPosition() == 0) {
					Bouncep.start();
				}
			}

		for (index = 0; index < Barriers.size(); index++) {
			if (Ball.getX() + Ball.getSizeA() < Barriers.get(index).getX() + Barriers.get(index).getSizeA()
					+ (Barriers.get(index).getSizeA() / 2))
				if (Barriers.get(index).hitdetection(Ball).equals("HitX")
						|| Barriers.get(index).hitdetection(Ball).equals("HitY")) {
					if (duel)
						if (saber.getMicrosecondPosition() == 0) {
							saberbarrier.start();

						} else {
						}
					else if (Bounceb.getMicrosecondPosition() == 0) {
						Bounceb.start();
					}

				}

		}

		if (Ball.getX() < Player.getX() + Player.getSizeA() + (Player.getSizeA() / 2)) {
			if (Player.hitdetectionball(Ball, Catch).equals("HitX")
					|| Player.hitdetectionball(Ball, Catch).equals("HitY")) {
				if (duel) {
					if (saber.getMicrosecondPosition() == 0) {
						saber.start();
					}
				} else if (!Catch)
					if (Bounce.getMicrosecondPosition() == 0) {
						Bounce.start();
					}
			}

		}

		BallHit();
		if (Player.getY() < gb.getY()) {
			if (B2 == false && B1 == false) {
				Player.ChangeDirection("down");
				desireddirection = "down";
			} else {
				Player.setYdir(0);
				Player.setY(gb.getY());
			}
		}

		if (Player.getY() > gb.getSizeB() - 60) {
			if (B2 == false && !B1) {
				Player.ChangeDirection("up");
				desireddirection = "up";
			}

			else {
				Player.setYdir(0);
				Player.setY(gb.getSizeB() - 60);
			}
		}
	}

	private void BallHit() {
		if (Ball.getX() < 0) {
			Ball.Score("left");
			Change();
			scored = true;

			if (Goal.getMicrosecondPosition() == 0) {
				Goal.start();
			}
		}
		if (Ball.getY() < 0) {
			Ball.walls(gb);
			if (duel)
				if (saber.getMicrosecondPosition() == 0) {
					saberwall.start();
				} else {
				}
			else if (Walls.getMicrosecondPosition() == 0) {
				Walls.start();
			}
		}
		if (Ball.getX() > 750) {
			Ball.Score("right");
			Change();
			scored = true;
			if (Goal.getMicrosecondPosition() == 0) {
				Goal.start();
			}
		}
		if (Ball.getY() > gb.getSizeB() - 10) {
			Ball.walls(gb);
			if (duel)
				if (saber.getMicrosecondPosition() == 0) {
					saberwall.start();
				} else {
				}
			else if (Walls.getMicrosecondPosition() == 0) {
				Walls.start();
			}
		}
	}

	private void AIHit() {
		if (AI.getY() < 0) {
			AI.walls("Y,0");
		}
		if (AI.getY() > gb.getSizeB() - 60) {
			AI.walls("Y,600");
		}
		if (gravity.getY() < 0) {
			gravity.walls("Y,0");
		}
		if (gravity.getY() > gb.getSizeB() - gravity.getSizeB()) {
			gravity.walls("Y,600");
		}
	}

	private void Popup() {
		startgame = false;
		Object[] options = { "Resume", "Quit" };
		int optionnumberchosen = JOptionPane.showOptionDialog(f1, "Which would you like to choose?", "Escape",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[1]);

		if (optionnumberchosen == 0) {
			// wooah, Real magic happens if you press this one
		}

		if (optionnumberchosen == 1) {
			f1.dispose();
		}
		startgame = true;

	}

	private void misc() {
		if (Ball.getScoreLeft() == 21 || Ball.getScoreRight() == 21) {
			endgame = true;
		}
		if (duel)
			if (idle.getMicrosecondPosition() == 0)
				idle.start();
		if (Ball.getXdir() > 5) {
			B1 = true;
		}
		if (Ball.getXdir() > 3) {
			Ball.setXdir(3);
		}
		if (Ball.getXdir() < -3) {
			Ball.setXdir(-3);
		}
		if (Ball.getYdir() > 3) {
			Ball.setYdir(3);
		}
		if (Ball.getYdir() < -3) {
			Ball.setYdir(-3);
		}
		if (Player.getX() > Ball.getX() - Player.getSizeA() + 2 && Player.getX() < Ball.getX() + Ball.getSizeA() - 2
				&& Player.getY() > Ball.getY() - Player.getSizeB() + 2
				&& Player.getY() < Ball.getY() + Ball.getSizeB() - 2) {
			Ball.setX(Player.getX() + (Player.getSizeA() * 2));
		}
		if (AI.getX() > Ball.getX() - AI.getSizeA() + 2 && AI.getX() < Ball.getX() + Ball.getSizeA() - 2
				&& AI.getY() > Ball.getY() - AI.getSizeB() + 2 && AI.getY() < Ball.getY() + Ball.getSizeB() - 2) {
			Ball.setX(AI.getX() - (AI.getSizeA() * 2));
		}

		if (duel && !B1) {
			if (Ball.getX() < 200) {
				if (!thing1)
					ogx = x;
				x = 15;
				thing1 = true;
			}
			if (thing1) {
				if (Ball.getX() > 200) {
					thing1 = false;
					x = ogx;
				}
			}
		}
		if (mouse)
			cord();

	}

	private void strings() {

	}

	public void cord() // throws InterruptedException
	{

		// get cords of mouse code, outputs to console every 1/2 second
		// make sure to import and include the "throws in the main method"

		// TimeUnit.SECONDS.sleep(1/2);
		double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
		Player.setY(mouseY - 45);
		// mke sure to import

	}

	private void SetValues() {
		desireddirection = "";
		startgame = false;
		B2 = true;
		randomdirection = (int) (Math.random() * 2 + 1);
		pausecounter = 150;
		B3 = false;
		FirstStart = true;
		x = 5;
		Barriers = new ArrayList<Barrier>();
		v = 1;
		icon = new ImageIcon("Icon(REMOVE THIS FOR IT TO WORK).png");
		dif = "";
		ogx = x;
		gravity = new Gravity(366, 300, 20, 20, 0, 1, false);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == b1) {
			endgame = false;
			startgame = true;
			g1.requestFocus();
		}
		if (event.getSource() == b2) {

			endgame = true;
		}
		g1.requestFocus();
	}

	public void keyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == 38) // up
		{
			desireddirection = "up";
			B1 = false;
			Player.ChangeDirection(desireddirection);
		}
		if (evt.getKeyCode() == 40) // down
		{
			desireddirection = "down";
			B1 = false;
			Player.ChangeDirection(desireddirection);
		}
		if (evt.getKeyCode() == 32) // Spacebar
		{
			Ball.setColor(true);
			ncounter = 50;
			if (!Catch && Ball.getWithin(Player) && Ball.getXdir() < 0 && !(Ball.getX() < Player.getX())) {
				Catch = true;
			}
		}
		if (evt.getKeyCode() == 27) // esc
		{
			Popup();
		}

		if (evt.getKeyCode() == 115) // f4
			if (mouse)
				mouse = false;
			else
				mouse = true;
		if (evt.getKeyCode() == 114) // f3
			if (f3)
				f3 = false;
			else
				f3 = true;
		if (f3) {
			if (evt.getKeyCode() == 39) // right
				if (B1 == true)
					B1 = false;
				else {
					B1 = true;
					x = ogx;
					ogx = x;
				}
			if (evt.getKeyCode() == 20) // Caps lock
				if (B2 == true)
					B2 = false;
				else
					B2 = true;
			if (evt.getKeyCode() == 92) // \|
				song.start();
			if (evt.getKeyCode() == 71) // G?
				if (gravity.getGravity())
					gravity.setGravity(false);
				else
					gravity.setGravity(true);
			if (evt.getKeyCode() == 82) // R
			{
				Ball.setX(270);
				Ball.setY(250);
			}
			if (evt.getKeyCode() == 83) // S?
			{
				pausecounter = 0;
			}
			if (B2) {
				if (evt.getKeyCode() == 96)
					x = 0;
				if (evt.getKeyCode() == 97)
					x = 1;
				if (evt.getKeyCode() == 98)
					x = 2;
				if (evt.getKeyCode() == 99)
					x = 3;
				if (evt.getKeyCode() == 100)
					x = 4;
				if (evt.getKeyCode() == 101)
					x = 5;
				if (evt.getKeyCode() == 102)
					x = 6;
				if (evt.getKeyCode() == 103)
					x = 7;
				if (evt.getKeyCode() == 104)
					x = 8;
				if (evt.getKeyCode() == 105) {
					x = 20;

				}
				if (evt.getKeyCode() == 61) {
					if (Barriers.size() <= 10) {
						Barriers.add(new Barrier(Ball, Barriers));
						;
					}
				}
			}
			if (evt.getKeyCode() == 45)
				popup();
			if (B2)

				if (evt.getKeyCode() == 68) // d
				{
					boolean firstthing = true;
					if (duel == true) {
						saberstart.stop();
						saberstart.setMicrosecondPosition(0);
						duel = false;
						firstthing = false;
						idle.stop();
						dotf.stop();
						x = ogx;
					} else
						duel = true;
					if (duel) {

						if (firstthing) {
							saberstart.start();
							firstthing = false;
						}
						dotf.start();
						idle.start();

					}
				}
		}
	}

	public void mousePressed(MouseEvent evt) {
	}

	public void mouseClicked(MouseEvent evt) {
	}

	public void mouseReleased(MouseEvent evt) {
	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}

	public void keyReleased(KeyEvent evt) {
		if ((evt.getKeyCode() == 38 || evt.getKeyCode() == 40) && B2) // up
		{
			desireddirection = "stop";
			B1 = false;
			// Player.moveY(-1*Player.getYdir());
			Player.ChangeDirection(desireddirection);
		}
		if (evt.getKeyCode() == 32) // Spacebar
		{
			ncounter = 50;
			if (Catch && !(Ball.getX() < Player.getX())) {

				Catch = false;
				if (Ball.getXdir() == 0) {

					Ball.addX(10);
					Ball.Artifcalhit("HitX");
				}
			}

			// if(B3==true)
			// B3=false;
			// else
			// B3=true;
			// System.out.println(B3);
			// }
		}
	}

	public void keyTyped(KeyEvent evt) {
		// implements MouseWheelListener
		// //where initialization occurs:
		// //Register for mouse-wheel events on the text area.
		// textArea.addMouseWheelListener(this);
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		Player.scroll(notches);
	}

}
