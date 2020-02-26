import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

public class DrawPanel extends JPanel {
    List<XmasShape> shapes = new ArrayList<>();
    /* DrawPanel() {
        //setBackground(new Color(10, 130, 250));

//        setOpaque(true);
        shapes.add(new Tree(3));
    }*/
    DrawPanel() {
        setBackground(new Color(19, 69, 175));
        /*for (int i = 0; i < 5; i++) {
            XmasTreeBranch r = new XmasTreeBranch(500, 380 - 80 * i, 25 * i, new Color(51, 130 + 15 * i, 102));
            shapes.add(r);
        }*/
        shapes.add(new XmasTreeBranch(500, 400, 0,  new Color(0, 152, 103))); //najniższa
        shapes.add(new XmasTreeBranch(500, 300, 30,  new Color(0, 162, 103)));
        shapes.add(new XmasTreeBranch(500, 200, 60,  new Color(0, 172, 103)));
        shapes.add(new XmasTreeBranch(500, 100, 90,  new Color(0, 182, 103)));
        shapes.add(new XmasTreeBranch(500, 0, 120,  new Color(0, 192, 103)));

        shapes.add(new XmasTreeTrunk(233,325, new Color(97, 46, 33)));

        shapes.add(new Star(440, 55, 0.3, new Color(235, 222, 52)));

        shapes.add(new Bubble(340, 270, 0.41, new Color(0,0,0), new Color(255,117,167)));
        shapes.add(new Bubble(365, 220, 0.42, new Color(0,0,0), new Color(212,9,88)));
        shapes.add(new Bubble(340, 350, 0.43, new Color(0,0,0), new Color(193,140,52)));
        shapes.add(new Bubble(265, 275, 0.44, new Color(0,0,0), new Color(132,102,182)));
        shapes.add(new Bubble(290, 234, 0.45, new Color(0,0,0), new Color(63,227,32)));
        shapes.add(new Bubble(400, 400, 0.46, new Color(0,0,0), new Color(242,3,114)));
        shapes.add(new Bubble(260, 325, 0.47, new Color(0,0,0), new Color(155,198,173)));
        shapes.add(new Bubble(330, 314, 0.65, new Color(0,0,0), new Color(231,245,99)));
        shapes.add(new Bubble(268, 336, 0.55, new Color(0,0,0), new Color(137,43,31)));
        shapes.add(new Bubble(315, 120, 0.45, new Color(0,0,0), new Color( 204,186,89)));
        shapes.add(new Bubble(278, 176, 0.55, new Color(0,0,0), new Color(231,161,57)));
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (XmasShape s : shapes) {
            s.draw((Graphics2D) g);
        }
        g.setColor(new Color(230, 0, 0));
        g.setFont(new Font("Helvetica", Font.BOLD, 40));
        g.drawString("MERRY CHRISTMAS!!!", 30, 80);
/*
        g.setFont(new Font("Helvetica", Font.BOLD, 18));
        g.drawString("Hello World", 20, 20);
        System.out.println("painting");
 */
        //g.drawLine(10,10,100,100);

        /*g.setColor(Color.yellow);
        g.fillOval(100,101,30,30);
        g.setColor(Color.black);
        g.drawOval(100,101,30,30);*/

      /*  int x[]={286,253,223,200,148,119,69,45,0};
        int y[]={0,101,89,108,79,95,66,80,56};
        g.fillPolygon(x,y,x.length);*/

        //Image img = Toolkit.getDefaultToolkit().getImage("doge.jpg");
        //g.drawImage(img,0,0,getWidth(),getHeight(),this);

        /////////////////////////

        //Graphics2D g2d= (Graphics2D)g;
/*
        // zachowaj macierz przekształcenia
        AffineTransform mat = g2d.getTransform();
        // przesuń początek układu
        g2d.translate(100,100);
        // zastosuj skalowanie
        g2d.scale(.2,.2);
        // narysuj linie
        for(int i=0;i<12;i++){
            g2d.drawLine(0,0,100,100);
            g2d.rotate(2*Math.PI/12);
        }
        //oddtwórz poprzednie ustawienia transformacji układu współrzędnych
        g2d.setTransform(mat);
*/
/*
        g2d.translate(200,200);
        // zastosuj skalowanie
        g2d.scale(.2,.2);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 96);
        g2d.setFont(font);
        for(int i=0;i<12;i++){
            g2d.drawString("Happy new year",150,0);
            g2d.rotate(2*Math.PI/12);
        }
*/

/*
        // zachowaj macierz przekształcenia
        AffineTransform mat = g2d.getTransform();
        // przesuń początek układu
        g2d.translate(200,200);
        // zastosuj skalowanie
        g2d.scale(.2,.2);
        g2d.setStroke(new BasicStroke(50, CAP_ROUND, JOIN_MITER));
        for(int i=0;i<12;i++){
            //g2d.drawString("Happy new year",150,0);
            g2d.drawLine(0,0,100,100);
            g2d.rotate(2*Math.PI/12);
        }
        //oddtwórz poprzednie ustawienia transformacji układu współrzędnych
        g2d.setTransform(mat);
*/


/*
        AffineTransform mat = g2d.getTransform();
        GradientPaint grad = new GradientPaint(0,0,new Color(0,255,0),0,100, new Color(0,10,0));
        g2d.setPaint(grad);
        g2d.translate(0,50);
        g2d.scale(0.7,0.5);
        int x[]={286,286,223,200,148,119,69,45,0};
        int y[]={0,131,89,108,79,95,66,80,56};
        g2d.fillPolygon(x,y,x.length);
        g2d.translate(670,0);
        g2d.scale(-1,1);
        g2d.fillPolygon(x,y,x.length);
        g2d.setTransform(mat);
    }
 */
    }

}

