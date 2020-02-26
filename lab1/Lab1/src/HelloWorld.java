import java.util.Locale;
import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("tekst");
        System.out.print("...");
        System.out.println("...");
        System.out.printf("String %s int %d double %f", "sdfsdfs", 12345, 12345.0);
        Scanner scan = new Scanner(System.in).useLocale(Locale.US);
        String s = scan.next();
        int i = scan.nextInt();
        System.out.printf(Locale.US,"Wczytano %s , %d",s,i);
    }
}
