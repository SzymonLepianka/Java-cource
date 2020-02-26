import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BoundingBox {
    double xmin;
    double ymin;
    double xmax;
    double ymax;

    BoundingBox(){}

    BoundingBox(double xmin, double ymin, double xmax, double ymax){
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }


    public String toString() {
        return "{" + "xMin=" + xmin + ", xMax=" + xmax + ", yMin=" + ymin + ", yMax=" + ymax + '}';
    }

    void addPoint(double x, double y) {
        if (isEmpty()) {
            xmin = xmax = x;
            ymin = ymax = y;
        } else {
            xmin = Math.min(xmin, x);
            xmax = Math.max(xmax, x);
            ymin = Math.min(ymin, y);
            ymax = Math.max(ymax, y);
        }
    }

    boolean contains(double x, double y) {
        return !isEmpty() && (xmin <= x && x <= xmax) && (ymin <= y && y <= ymax);
    }

    boolean contains(BoundingBox bb){
        return !(bb.isEmpty()) && contains(bb.xmin, bb.ymin) && contains(bb.xmax, bb.ymax);
    }

    boolean intersects(BoundingBox bb){
        if (bb.isEmpty() || this.isEmpty()){
            return false;
        }
        Rectangle2D.Double rect1 = new Rectangle2D.Double(this.xmin,this.ymin,this.xmax-this.xmin,this.ymax-this.ymin);
        Rectangle2D.Double rect2 = new Rectangle2D.Double(bb.xmin,bb.ymin,bb.xmax-bb.xmin,bb.ymax-bb.ymin);
        if(rect1.intersects(rect2)){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb){
        if (isEmpty()) {
            return bb;
        }
        if(bb.isEmpty()) {
            return this;
        }
        BoundingBox temp = new BoundingBox();
        temp.xmin = Math.min(xmin, bb.xmin);
        temp.xmax = Math.max(xmax, bb.xmax);
        temp.ymin = Math.min(ymin, bb.ymin);
        temp.ymax = Math.max(ymax, bb.ymax);
        return temp;
    }
    /**
     * Sprawdza czy BB jest pusty
     * @return
     */
    boolean isEmpty() {
        return Double.isNaN(xmin) && Double.isNaN(xmax) && Double.isNaN(ymin) && Double.isNaN(ymax);
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX(){
        if (isEmpty()) {
            throw new RuntimeException("Not implemented");
        }
        return (xmin + xmax) / 2;
    }
    /**
     * Oblicza i zwraca współrzędną y środka
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY(){
        if (isEmpty()) {
            throw new RuntimeException("Not implemented");
        }
        return (ymin + ymax) / 2;
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox bbx){
        if (isEmpty() || bbx.isEmpty()) {
            throw new RuntimeException("Not implemented");
        }
        double centerX = getCenterX();
        double centerY = getCenterY();
        double bbCenterX = bbx.getCenterX();
        double bbCenterY = bbx.getCenterY();
        return Math.sqrt(Math.pow((centerX-bbCenterX),2) +Math.pow((centerY-bbCenterY),2));
    }

}
