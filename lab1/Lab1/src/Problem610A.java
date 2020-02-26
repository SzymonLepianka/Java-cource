import java.util.Scanner;

public class Problem610A {
    public static void main(String[] args) {
        System.out.print("KIJ: ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n > 2000000000 || n < 1) {
            return;
        }
        if (n / 2 < 3 || n % 2 == 1) {
            System.out.print("0");
            return;
        }
        int i = n / 2 - 1;
        int count = 0;
        for (int j = 1; j < i; j++) {
            count++;
            i--;
        }
        System.out.println(count);
    }
}
