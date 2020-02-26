import java.awt.*;

public class XmasTreeBranch implements XmasShape {
    int x;
    int y;
    int z;
    Color branchColor;


    XmasTreeBranch(int x, int y, int z, Color branchColor) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.branchColor = branchColor;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(branchColor);
        int tabX[] = {0, 200 - z, z - 200};
        int tabY[] = {z, 250, 250};
        g2d.fillPolygon(tabX, tabY, tabX.length);
    }
}