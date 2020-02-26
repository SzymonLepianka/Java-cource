import java.awt.*;

public class Star implements XmasShape{

    int[] a  = {205, 245, 369, 270, 298, 205, 105, 135, 50, 160, 205};
    int[] b = {43, 145, 155, 228, 336, 276, 336, 228, 155, 145, 43};
    Color color;
    double scale;
    int x;
    int y;

    Star(int x, int y, double scale, Color color){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.color = color;
    }
    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillPolygon(a, b, 11);
    }
}
