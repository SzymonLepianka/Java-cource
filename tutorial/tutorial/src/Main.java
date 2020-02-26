import java.util.Scanner;

public class Main {
    byte b = 127; //-128 do 127
    short s = 32767; //-32768 do 32767
    //int i = -2147483648; //-2147483648 do 2147483647
    long l = 4656456456456456L;
    float f = 4564.44444444F;
    double d = 3.3453454355353534534545;
    char c = 'a';
    String string = "tekst tekst tekst";

    public static void main(String[] args) {
        int wynik = 5;
        System.out.println("Wynik = " + wynik);
        int[] tab = new int[7];
        tab[0] = 6;
        tab[1] = 7;
        tab[2] = 8;
        tab[3] = 9;
        tab[4] = 5;
        for (int i : tab) {
            System.out.println(i);
        }
        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[i]);
        }
        //int table[] = new int [7];
        //int table[]={1,2,3,4};
        int[][] table = {{1,2,3},{4,5,6}};
        for (int[] i : table) {
            for (int j : i) {
                System.out.println(j);
            }
        }

        Scanner scanner = new Scanner(System.in);
        String name="";
        name = scanner.next();
        System.out.println(name);


    }
}
