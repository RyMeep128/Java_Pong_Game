
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Write a description of class Graphics here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainGraphics extends JPanel {
	private static final long serialVersionUID = 1L;
	Player Player;
	Ball Ball;
	AI AI;
	ArrayList<Barrier> Barriers;
	GameBorder gb;
	boolean B3, B4;
	int index;
	boolean duel, scored;
	Gravity gravity;
	Image gravityimage;
	boolean nice = false;

	// Score Score;
	public void paint(Graphics g) {
		super.paint(g);
		DrawMain(g);

	}

	public void endupdate() {
		nice = true;
		// g.drawString("A Fine Addition to my collection",100,200);
	}

	public void updateSaber(boolean Duel, boolean Scored) {
		duel = Duel;
		scored = Scored;
	}

	public MainGraphics(Player Gplayer, Ball Gball, AI GAI, GameBorder gb, ArrayList<Barrier> Barriers,
			Gravity gravity) {
		setBackground(Color.white);
		Player = Gplayer;
		Ball = Gball;
		AI = GAI;
		this.gb = gb;
		B3 = false;
		B4 = false;
		this.Barriers = Barriers;
		this.gravity = gravity;
		gravityimage = Toolkit.getDefaultToolkit().getImage("Gravity.png");
		// Score=GScore;
	}

	private void DrawMain(Graphics g) {

		// g.drawString(""+Player.getX()+","+Player.getY(),Player.getX(),Player.getY()-5);

		if (duel) {
			g.drawImage(gravityimage, gravity.getX(), gravity.getY(), gravity.getSizeA(), gravity.getSizeB(), null);

			g.setColor(Color.black);
			g.fillRect(AI.getX(), AI.getY() + AI.getSizeB() - 15, AI.getSizeA(), 15);
			g.setColor(Color.black);
			g.fillRect(Player.getX(), Player.getY() + Player.getSizeB() - 15, Player.getSizeA(), 15);
			for (index = 0; index < Barriers.size(); index++) {
				g.setColor(Color.black);
				g.fillRect(Barriers.get(index).getX(), Barriers.get(index).getY() + Barriers.get(index).getSizeB() - 15,
						Barriers.get(index).getSizeA(), 15);
			}
			g.setColor(Color.green);
			g.fillRect(Player.getX(), Player.getY(), Player.getSizeA(), Player.getSizeB());
			g.setColor(Color.red);
			g.fillRect(AI.getX(), AI.getY(), AI.getSizeA(), AI.getSizeB());
			for (index = 0; index < Barriers.size(); index++) {
				Starwars(g);
				g.fillRect(Barriers.get(index).getX(), Barriers.get(index).getY(), Barriers.get(index).getSizeA(),
						Barriers.get(index).getSizeB());
			}
			g.setColor(Color.black);
		} else {
			g.drawImage(gravityimage, gravity.getX(), gravity.getY(), gravity.getSizeA(), gravity.getSizeB(), null);

			g.setColor(Color.black);
			g.fillRect(Player.getX(), Player.getY(), Player.getSizeA(), Player.getSizeB());
			g.setColor(Color.black);
			g.fillRect(AI.getX(), AI.getY(), AI.getSizeA(), AI.getSizeB());
			for (index = 0; index < Barriers.size(); index++) {
				g.setColor(Color.gray);

				g.fillRect(Barriers.get(index).getX(), Barriers.get(index).getY(), Barriers.get(index).getSizeA(),
						Barriers.get(index).getSizeB());
				// g.drawRect((Barriers.get(index).getX() - Barriers.get(index).getSizeA())
				// ,(Barriers.get(index).getX() + Barriers.get(index).getSizeA())*
				// 2,(Barriers.get(index).getY() - Barriers.get(index).getSizeB()),
				// (Barriers.get(index).getY() + Barriers.get(index).getSizeB())* 2);
			}
		}

		if (duel)
			g.setColor(Color.blue);
		else
			g.setColor(Color.gray);
		// RainBow(g);
		g.fillOval(Ball.getX(), Ball.getY(), Ball.getSizeA(), Ball.getSizeB());

		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		// g.drawString(Ball.getScoreRight()+","+Ball.getScoreLeft(),(gb.getSizeA()/2)-80,30);
		g.drawString("" + Ball.getScoreRight(), gb.getSizeA() / 4, gb.getY() + 40);
		g.drawString("" + Ball.getScoreLeft(), ((gb.getSizeA() / 2) + (gb.getSizeA() / 4)), gb.getY() + 40);
		// if(Ball.getWhoScored()

		g.drawRect(gb.getX(), gb.getY(), gb.getSizeA(), gb.getSizeB());
		// if(B3||B4)
		// {
		// if(B3)
		// g.setColor(Color.green);
		// if(B4)
		// g.setColor(Color.red);
		// g.drawRect(Player.getX(),Player.getY(),60,+60);
		// }

		if (Ball.getColor() && !Ball.getCaught()) {
			g.setColor(Color.red);
			g.drawRect(Player.getX(), Player.getY(), 60, +60);
		}
		// System.out.println(Ball.getColor()+"&&"+Ball.getCaught());
		if ((Ball.getColor() && Ball.getWithin(Player) && Ball.getXdir() < 0) || Ball.getCaught()) {

			g.setColor(Color.green);
			g.drawRect(Player.getX(), Player.getY(), 60, +60);
		}
		// g.drawRect(Ball.getX(),Ball.getY() - AI.getSizeB(),Ball.getX() ,Ball.getY() +
		// Ball.getSizeB());
		g.setColor(Color.black);
		g.drawLine(gb.getSizeA() / 2, 0, gb.getSizeA() / 2, gb.getSizeB());

		if (scored && duel) {
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.setColor(Color.black);
			// g.drawString("A Fine Addition to my collection",100,200);
			scored = false;
		}
		String scoring = "";
		if (nice) {
			if (Ball.getScoreLeft() == 11)
				scoring = "You Lost";
			else
				scoring = "You Won";
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.setColor(Color.black);
			g.drawString("GameOver", 100, 200);
			g.drawString("" + scoring, 120, 280);
		}
	}

	private void Starwars(Graphics g) {
		if (index < Barriers.size() / 2)
			g.setColor(Color.red);
		if (index > Barriers.size() / 2)
			g.setColor(Color.green);
		if (Ball.getScoreLeft() == Ball.getScoreRight()) {
			g.setColor(Color.yellow);
		}
	}

	@SuppressWarnings("unused")
	private void RainBow(Graphics g) {

		if (Ball.getX() < 100 && Ball.getX() > 0) {
			g.setColor(Color.red);
		}
		if (Ball.getX() < 200 && Ball.getX() > 100) {
			g.setColor(Color.orange);
		}
		if (Ball.getX() < 300 && Ball.getX() > 200) {
			g.setColor(Color.yellow);
		}
		if (Ball.getX() < 400 && Ball.getX() > 300) {
			g.setColor(Color.green);
		}
		if (Ball.getX() < 500 && Ball.getX() > 400) {
			g.setColor(Color.blue);
		}
		if (Ball.getX() < 600 && Ball.getX() > 500) {
			g.setColor(Color.gray);
		}
	}

	public void updateall(Player Gplayer, Ball Gball, AI GAI, boolean B3, boolean B4, ArrayList<Barrier> Barriers) {
		Player = Gplayer;
		Ball = Gball;
		AI = GAI;
		this.B3 = B3;
		this.B4 = B4;
		this.Barriers = Barriers;
		// Score=GScore;
	}
}
