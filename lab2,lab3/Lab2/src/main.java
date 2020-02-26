import java.util.Arrays;

public class main {
    public static void main(String[] args) {

        Matrix m = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6}, {7, 8}, {9, 10}});
        Matrix m1 = new Matrix(new double[][]{{1, 4, 5, 9}, {55, 63}, {34, 56}, {12}});
        double[][] tab = m.asArray();
        System.out.println(m.get(0,0));
        System.out.println(m.get(0,1));
        m.set(0,0,69);
        System.out.println(m.get(0,0));
        System.out.println(m.toString());
        int[] b = m.shape();
        System.out.println(Arrays.toString(b));
        Matrix m2 = m1.add(m);
        Matrix m3 = m1.sub(m);
        Matrix m4 = m1.mul(m);
        Matrix m5 = m1.div(m);
        Matrix m22 = m1.add(5);
        Matrix m33 = m1.sub(5);
        Matrix m44 = m1.mul(5);
        Matrix m55 = m1.div(5);
        Matrix m6 = m1.dot(m);
        System.out.println(m.frobenius());


        //kartkówka
        Matrix mkart = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix col = mkart.getColumn(0);
        System.out.println(col.toString());
    }
}