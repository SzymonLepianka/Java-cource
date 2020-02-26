import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallsPanel extends JPanel {

    private AnimationThread animation = new AnimationThread();


    static class Ball {

        int x;
        int y;
        int d;
        double r;
        double vx;
        double vy;
        Color color;

        Ball() {
            Random r = new Random();

            this.x = r.nextInt(700);
            this.y = r.nextInt(700);
            this.color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            this.vx = (r.nextInt(8)) + 8;
            this.vy = (r.nextInt(8)) + 8;
            this.d = r.nextInt(15) + 15;
            this.r = ((double) d) / 2;
        }

        public void draw(Graphics2D g2d) {
            AffineTransform saveAT = g2d.getTransform();
            this.render(g2d);
            g2d.setTransform(saveAT);
        }

        public void render(Graphics2D g2d) {
            g2d.setColor(this.color);

            g2d.fillOval(x, y, d, d);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        balls.forEach(ball -> ball.draw((Graphics2D) g));
    }


    List<Ball> balls = new ArrayList<>();

    class AnimationThread extends Thread {
        boolean pause = true;

        public void run() {
            for (; ; ) {
                synchronized (this) {
                    //uśpij
                    if (pause) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Ball ball : balls) {
                    ball.x += ball.vx;
                    ball.y += ball.vy;
                    if (ball.x+ball.d >= 680 || ball.x <= 0) ball.vx *= -1;
                    if (ball.y+ball.d >= 630 || ball.y <= 0) ball.vy *= -1;
                    /*for (Ball ball2 : balls) {
                        if (((ball2.x > ball.x && ball2.x < ball.x + ball.d) && (ball2.y > ball.y && ball2.y < ball.y + ball.d)) || ((ball.x > ball2.x && ball.x < ball2.x + ball2.d) && (ball.y > ball2.y && ball.y < ball2.y + ball2.d))) {
                            ball.vx *= -1;
                            ball.vy *= -1;
                            ball2.vx *= -1;
                            ball2.vy *= -1;
                        }
                    }*/
                }
                for (int i = 0; i < balls.size(); i++) {
                    for (int j = i + 1; j < balls.size(); j++) {
                        Ball ball1 = balls.get(i);
                        Ball ball2 = balls.get(j);
                        double ball1CentreX = ball1.x + ball1.r;
                        double ball1CentreY = ball1.y + ball1.r;
                        double ball2CentreX = ball2.x + ball1.r;
                        double ball2CentreY = ball2.y + ball1.r;
                        double a = Math.pow(ball1CentreX - ball2CentreX, 2) + Math.pow(ball1CentreY - ball2CentreY, 2);
                        double distance = Math.sqrt(a);
                        if (distance <= ball1.r + ball2.r) {
                            double modifierForBall1 = ((ball1.vx - ball2.vx) * (ball1CentreX - ball2CentreX) + (ball1.vy - ball2.vy) * (ball1CentreY - ball2CentreY)) / a;
                            double modifierForBall2 = ((ball2.vx - ball1.vx) * (ball2CentreX - ball1CentreX) + (ball2.vy - ball1.vy) * (ball2CentreY - ball1CentreY)) / (Math.pow((ball2CentreX - ball1CentreX), 2) + (Math.pow(ball2CentreY - ball1CentreY, 2)));
                            ball1.vx = ball1.vx - modifierForBall1 * (ball1CentreX - ball2CentreX);
                            ball1.vy = ball1.vy - modifierForBall1 * (ball1CentreY - ball2CentreY);
                            ball2.vx = ball2.vx - modifierForBall2 * (ball2CentreX - ball1CentreX);
                            ball2.vy = ball2.vy - modifierForBall2 * (ball2CentreY - ball1CentreY);
                        }
                    }
                }
                repaint();
            }
        }
    }

    BouncingBallsPanel() {
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        animation.start();
    }

    void onStart() {
        animation.pause = false;
        System.out.println("Start or resume animation thread");
        if (balls.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                balls.add(new Ball());
            }
        }
        synchronized (animation) {
            animation.notifyAll();

        }
    }

    void onStop() throws InterruptedException {
        //this.animation
        //this.animation.suspend();
        animation.pause = true;

        System.out.println("Suspend animation thread");
    }

    void onPlus() {
        this.balls.add(new Ball());

        System.out.println("Add a ball");
    }

    void onMinus() {
        if (!balls.isEmpty()) {
            System.out.println("Remove a ball");
            this.balls.remove(0);
        }
    }
}