package qualification;


import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int maxDominoes = Math.min((n * m - 1) / 2, 28);

        System.out.println(maxDominoes);
    }
}

