package qualification;

import java.util.Arrays;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] scores = new int[n];

            for (int j = 0; j < n; j++) {
                scores[j] = scanner.nextInt();
            }

            Arrays.sort(scores);

            int minDifference = Integer.MAX_VALUE;
            for (int j = 1; j < n; j++) {
                int currentDifference = scores[j] - scores[j - 1];
                minDifference = Math.min(minDifference, currentDifference);
            }

            System.out.println(minDifference);
        }
    }
}
