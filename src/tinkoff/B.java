package tinkoff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = scanner.nextInt();
        }

        Map<Integer, Integer> bookCountMap = new HashMap<>();

        for (int height : heights) {
            bookCountMap.put(height, bookCountMap.getOrDefault(height, 0) + 1);
        }

        int[] stackHeights = new int[bookCountMap.size()];
        int index = 0;

        for (int count : bookCountMap.values()) {
            stackHeights[index++] = count;
        }

        Arrays.sort(stackHeights);

        System.out.println(stackHeights.length);

        for (int height : stackHeights) {
            System.out.print(height + " ");
        }
    }
}
