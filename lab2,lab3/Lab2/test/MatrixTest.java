import java.util.Random;

import static org.junit.Assert.*;

import org.junit.Test;


public class MatrixTest {

    @Test
    public void testMatrixRowsCols() {
        Random rand = new Random();
        int a = rand.nextInt(5);
        int b = rand.nextInt(5);
        Matrix testing = new Matrix(a, b);
        testing.asArray();
        assertEquals(testing.rows, a);
        assertEquals(testing.cols, b);
    }

    @Test
    public void testMatrixDouble() {
        Matrix testing = new Matrix(new double[][]{{1, 2, 3}, {5, 6}, {7, 8}, {9, 10}});
        assertEquals(testing.get(1, 1), 6, 0.0);
        assertEquals(testing.get(1, 0), 5, 0.0);
        assertEquals(testing.rows, 4);
        assertEquals(testing.cols, 3);
    }

    @Test
    public void asArray() {
        double[][] d = new double[][]{{1, 2, 6}, {3, 4, 5}};
        Matrix expected = new Matrix(d);
        assertEquals(expected.asArray().length, expected.rows);
        assertEquals(expected.asArray()[0].length, expected.cols);
    }

    @Test
    public void get() {
        Matrix expected = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        assertEquals(expected.get(1, 1), 4, 0.1);
        assertEquals(expected.get(0, 2), 6, 0.1);
    }

    @Test
    public void set() {
        Matrix expected = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        expected.set(0, 0, 12);
        assertEquals(expected.get(0, 0), 12, 0.1);
    }

    @Test
    public void testToString() {
        Matrix expected = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        String s = expected.toString();
        int k = 0, l = 0, r = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ',') {
                k++;
            }
            if (s.charAt(i) == '[') {
                l++;
            }
            if (s.charAt(i) == ']') {
                r++;
            }
        }
        assertEquals(k, 4);
        assertEquals(l, 3);
        assertEquals(r, 3);
    }

    @Test
    public void reshape() {
        Matrix expected = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        expected.reshape(1, 6);
        expected.reshape(2, 3);
        try {
            expected.reshape(3, 3);
        } catch (Exception a) {
            return;
        }
        fail("błąd");
    }

    @Test
    public void shape() {
        Matrix expected = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        assertEquals(expected.shape().length, 2);
        assertEquals(expected.shape()[0], expected.rows);
        assertEquals(expected.shape()[1], expected.cols);
    }

    @Test
    public void add() {
        Matrix test = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        Matrix test2 = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        test2 = test2.mul(-1);
        Matrix expected = test.add(test2);
        double diff = expected.frobenius();
        assertEquals(diff, 0, 1e-5);
    }

    @Test
    public void sub() {
        Matrix test = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        Matrix expected = test.sub(test);
        double diff = expected.frobenius();
        assertEquals(diff, 0, 1e-5);
    }

    @Test
    public void mul() {
        Matrix test = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        Matrix test2 = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        Matrix expected = test.mul(test2);
        assertEquals(expected.get(0, 0), 1, 1e-5);
        assertEquals(expected.get(1, 1), 16, 1e-5);
        assertEquals(expected.get(0, 2), 36, 1e-5);
    }

    @Test
    public void div() {
        Matrix test = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        Matrix expected = test.div(test);
        double diff = expected.frobenius();
        assertEquals(diff, Math.sqrt(test.cols * test.rows), 1e-5);
    }

    @Test
    public void testAdd() {
        Matrix test = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        Matrix expected = test.add(3);
        assertEquals(expected.get(0, 0), 4, 1e-5);
        assertEquals(expected.get(1, 1), 7, 1e-5);
        assertEquals(expected.get(0, 2), 9, 1e-5);
    }

    @Test
    public void testSub() {
        Matrix test = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        Matrix expected = test.sub(3);
        assertEquals(expected.get(0, 0), -2, 1e-5);
        assertEquals(expected.get(1, 1), 1, 1e-5);
        assertEquals(expected.get(0, 2), 3, 1e-5);
    }

    @Test
    public void testMul() {
        Matrix test = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        Matrix expected = test.mul(3);
        assertEquals(expected.get(0, 0), 3, 1e-5);
        assertEquals(expected.get(1, 1), 12, 1e-5);
        assertEquals(expected.get(0, 2), 18, 1e-5);
    }

    @Test
    public void testDiv() {
        Matrix test = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        Matrix expected = test.div(3);
        assertEquals(expected.get(0, 0), 0.3333333333333, 1e-5);
        assertEquals(expected.get(1, 1), 1.3333333333333, 1e-5);
        assertEquals(expected.get(0, 2), 2, 1e-5);
    }

    @Test
    public void dot() {
        Matrix test = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        Matrix test2 = new Matrix(new double[][]{{1, 2}, {3, 4}, {5, 6}});
        Matrix expected = test.dot(test2);
        assertEquals(expected.rows, test.rows);
        assertEquals(expected.cols, test2.cols);
        assertEquals(expected.get(0, 0), 37, 1e-5);
        assertEquals(expected.get(1, 1), 52, 1e-5);
    }

    @Test
    public void frobenius() {
        Matrix test = new Matrix(new double[][]{{1, 2, 6}, {3, 4, 5}});
        double diff = test.frobenius();
        assertEquals(diff, 9.53939201, 1e-5);
    }
}