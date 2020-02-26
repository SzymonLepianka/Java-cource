import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        Matrix m = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6}, {7, 8}, {9}});
        Matrix m1 = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6}, {7, 8}, {9}});
        double[][] tab = m.asArray();
        System.out.println(m.get(0,0));
        System.out.println(m.get(0,1));
        m.set(0,0,69);
        System.out.println(m.get(0,0));
        System.out.println(m.toString());
        int[] b = m.shape();
        System.out.println(Arrays.toString(b));
        Matrix m2 = m1.add(m);
    }
}