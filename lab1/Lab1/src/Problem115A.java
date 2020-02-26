import java.util.Scanner;

public class Problem115A {
    public static void main(String[] args) {
        System.out.print("ILE?:");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if (n > 2000 || n < 1) {
            return;
        }
        int[] employees = new int[n];
        int i = 0;
        while (i < n) {
            employees[i] = scan.nextInt();
            if ((employees[i] <= n && employees[i] >= 1 && employees[i] != i + 1) || employees[i] == -1) {
                i++;
            } else {
                return;
            }
        }
        int x, count = 0, j;

        for (i = 0; i < n; i++) {
            x = 1;
            j = employees[i];
            while (j != -1) {
                x++;
                j = employees[j - 1];
            }
            if (count < x) {
                count = x;
            }
        }
        System.out.println(count);
    }
}
