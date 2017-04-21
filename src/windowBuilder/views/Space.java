/*
 * Space.java is the space where invaders and the good guy (player) battle.
 * */

package windowBuilder.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Space extends JPanel implements Runnable, Config {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension d;
    private ArrayList<Invader> invaders;
    private Player player;
    private Shot shot;

    private final int Invader_INIT_X = 150;
    private final int Invader_INIT_Y = 5;
    private int direction = -1;
    private int deaths = 0;

    private boolean ingame = true;
    private final String explImg = "src/windowBuilder/resources/explosion.png";
    private String message = "You are dead!";

    private Thread animator;

    public Space() {

        initSpace();
    }

    private void initSpace() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(SPACE_WIDTH, SPACE_HEIGHT);
        setBackground(Color.white);

        gameInit();
        setDoubleBuffered(true);
    }

    @Override
    public void addNotify() {

        super.addNotify();
        gameInit();
    }

    public void gameInit() {

        invaders = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {

                Invader invader = new Invader(Invader_INIT_X + 18 * j, Invader_INIT_Y + 18 * i);
                invaders.add(invader);
            }
        }

        player = new Player();
        shot = new Shot();

        if (animator == null || !ingame) {

            animator = new Thread(this);
            animator.start();
        }
    }

    public void drawInvaders(Graphics g) {

        //Iterator<Invader> it = invaders.iterator();

        for (Invader invader: invaders) {

            if (invader.isVisible()) {

                g.drawImage(invader.getImage(), invader.getX(), invader.getY(), this);
            }

            if (invader.isDying()) {
                invader.die();
            }
        }
    }

    public void drawPlayer(Graphics g) {

        if (player.isVisible()) {
            
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {

            player.die();
            ingame = false;
        }
    }

    public void drawShot(Graphics g) {

        if (shot.isVisible()) {
            
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    public void drawBombing(Graphics g) {

        for (Invader a : invaders) {
            
            Invader.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {
                
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (ingame) {

            g.drawLine(0, GROUND, SPACE_WIDTH, GROUND);
            drawInvaders(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void gameOver() {

        Graphics g = this.getGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, SPACE_WIDTH, SPACE_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, SPACE_WIDTH / 2 - 30, SPACE_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, SPACE_WIDTH / 2 - 30, SPACE_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (SPACE_WIDTH - metr.stringWidth(message)) / 2,
                SPACE_WIDTH / 2);
    }

   
    public void animationCycle() {

        if (deaths == NUMBER_OF_ALIENS_TO_DESTROY) {

            ingame = false;
            message = "Game won!";
        }

        // player
        player.move();

        // shot
        if (shot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();

            for (Invader invader: invaders) {

                int InvaderX = invader.getX();
                int InvaderY = invader.getY();

                if (invader.isVisible() && shot.isVisible()) {
                    if (shotX >= (InvaderX)
                            && shotX <= (InvaderX + INVADER_WIDTH)
                            && shotY >= (InvaderY)
                            && shotY <= (InvaderY + INVADER_HEIGHT)) {
                        ImageIcon ii
                                = new ImageIcon(explImg);
                        invader.setImage(ii.getImage());
                        invader.setDying(true);
                        deaths++;
                        shot.die();
                    }
                }
            }

            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }

        // Invaders

        for (Invader Invader: invaders) {

            int x = Invader.getX();

            if (x >= SPACE_WIDTH - BORDER_RIGHT && direction != -1) {

                direction = -1;
                Iterator<Invader> i1 = invaders.iterator();

                while (i1.hasNext()) {

                    Invader a2 = (Invader) i1.next();
                    a2.setY(a2.getY() + GO_DOWN);
                }
            }

            if (x <= BORDER_LEFT && direction != 1) {

                direction = 1;

                Iterator<Invader> i2 = invaders.iterator();

                while (i2.hasNext()) {

                    Invader a = (Invader) i2.next();
                    a.setY(a.getY() + GO_DOWN);
                }
            }
        }

        Iterator<Invader> it = invaders.iterator();

        while (it.hasNext()) {
            
            Invader Invader = (Invader) it.next();
            
            if (Invader.isVisible()) {

                int y = Invader.getY();

                if (y > GROUND - INVADER_HEIGHT) {
                    ingame = false;
                    message = "Invasion!";
                }

                Invader.move(direction);
            }
        }

        // bombs
        Random generator = new Random();

        for (Invader Invader: invaders) {

            int shot = generator.nextInt(15);
            Invader.Bomb b = Invader.getBomb();

            if (shot == CHANCE && Invader.isVisible() && b.isDestroyed()) {

                b.setDestroyed(false);
                b.setX(Invader.getX());
                b.setY(Invader.getY());
            }

            int bombX = b.getX();
            int bombY = b.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !b.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + PLAYER_HEIGHT)) {
                    ImageIcon ii
                            = new ImageIcon(explImg);
                    player.setImage(ii.getImage());
                    player.setDying(true);
                    b.setDestroyed(true);
                }
            }

            if (!b.isDestroyed()) {
                
                b.setY(b.getY() + 1);
                
                if (b.getY() >= GROUND - BOMB_HEIGHT) {
                    b.setDestroyed(true);
                }
            }
        }
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (ingame) {

            repaint();
            animationCycle();
            

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            
            beforeTime = System.currentTimeMillis();
        }

        gameOver();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                
                if (ingame) {
                    if (!shot.isVisible()) {
                        shot = new Shot(x, y);
                    }
                }
            }
        }
    }
}