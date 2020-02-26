import java.awt.*;

public class XmasTreeTrunk implements XmasShape {
    int x;
    int y;
    Color trunkColor;

    XmasTreeTrunk(int x, int y, Color trunkColor) {
        this.x = x;
        this.y = y;
        this.trunkColor = trunkColor;
    }
    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(trunkColor);
        g2d.fillRect(x, y, 70, 100);
    }
}