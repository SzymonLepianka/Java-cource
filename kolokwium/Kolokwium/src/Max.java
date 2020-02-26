import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Max {
    private static final int THREADS = 4;
    private static final int ITEMS = 100000000;
    static double[] array;// = new double[ITEMS];
    static BlockingQueue<double[]> results = new ArrayBlockingQueue<double[]>(THREADS);
    static MaxCalc[] threads = new MaxCalc[THREADS];

    public static void main(String[] args) throws InterruptedException {
        initArray(ITEMS);
        parallelMax(THREADS);
        parallelMaxrowno(THREADS);//nie do końca :(
    }
    static void initArray(int size) {
        array = new double[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = r.nextDouble()*2000-1000;
            //System.out.println(array[i]);
        }
    }
    static void parallelMax(int cnt) throws InterruptedException {
        int equal = ITEMS / cnt;
        for (int i = 0; i < cnt; i++) {
            threads[i] = new MaxCalc(i * equal, (i + 1) * equal-1);
        }
        double t1 = System.nanoTime() / 1e6;
        for (MaxCalc mc : threads) {
            mc.start();
        }
        double t2 = System.nanoTime() / 1e6;
        /*for (MaxCalc mc : threads) {
            results.put(mc.max);
        }*/

        double []m = new double[3];
        m[0] = -2000.0;
        m[1] = -2000.0;
        m[2] = -2000.0;
        for (MaxCalc mc : threads) {
            double []rt = results.take();
            if (rt[0] > m[0]) {
                m[0] = rt[0];
                if (rt[1] > m[1]) {
                    m[1] = rt[1];
                    if (rt[2] > m[2]) {
                        m[2] = rt[2];
                    }
                }
                else
                if (rt[1] > m[2]) {
                    m[1] = rt[2];
                }
            }
            else
            if (rt[0] > m[1]) {
                m[0] = rt[1];
                if (rt[1] > m[2]) {
                    m[1] = rt[2];
                }
            }
            else
            if (rt[0] > m[2]) {
                m[0] = rt[2];
            }
        }
        double t3 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "SEKWENCYJNIE: size = %d cnt=%d >  t2-t1=%f t3-t1=%f max=%f, %f, %f\n",
                array.length,
                cnt,
                t2 - t1,
                t3 - t1,
                m[0], m[1], m[2]);
    }static void parallelMaxrowno(int cnt) throws InterruptedException {
        int equal = ITEMS / cnt;
        for (int i = 0; i < cnt; i++) {
            threads[i] = new MaxCalc(i * equal, (i + 1) * equal-1);
        }
        double t1 = System.nanoTime() / 1e6;
        for (MaxCalc mc : threads) {
            mc.run();
        }
        double t2 = System.nanoTime() / 1e6;
        /*for (MaxCalc mc : threads) {
            results.put(mc.max);
        }*/

        double []m = new double[3];
        m[0] = -2000.0;
        m[1] = -2000.0;
        m[2] = -2000.0;
        for (MaxCalc mc : threads) {
            double []rt = results.take();
            if (rt[0] > m[0]) {
                m[0] = rt[0];
                if (rt[1] > m[1]) {
                    m[1] = rt[1];
                    if (rt[2] > m[2]) {
                        m[2] = rt[2];
                    }
                }
                else
                if (rt[1] > m[2]) {
                    m[1] = rt[2];
                }
            }
            else
            if (rt[0] > m[1]) {
                m[0] = rt[1];
                if (rt[1] > m[2]) {
                    m[1] = rt[2];
                }
            }
            else
            if (rt[0] > m[2]) {
                m[0] = rt[2];
            }
        }
        double t3 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "RÓWNOLEGLE: size = %d cnt=%d >  t2-t1=%f t3-t1=%f max=%f, %f, %f\n",
                array.length,
                cnt,
                t2 - t1,
                t3 - t1,
                m[0], m[1], m[2]);
    }
    static class MaxCalc extends Thread {
        int start;
        int end;
        double []max = new double[3];

        MaxCalc(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            max[0] = array[start];
            max[1] = array[start];
            max[2] = array[start];
            for (int i = start+1; i < end; i++) {
                if (array[i] > max[0]) {
                    max[0] = array[i];
                }
                else if (array[i] > max[1]) {
                    max[1] = array[i];
                }
                else if (array[i] > max[2]) {
                    max[2] = array[i];
                }
            }
            results.add(max);
            //System.out.printf(Locale.US, "%d-%d max=%f, %f, %f\n", start, end, max[0], max[1], max[2]);
        }
    }



}
