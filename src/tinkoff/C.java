package tinkoff;

import java.util.Arrays;
import java.util.Scanner;

public class C {
    static boolean success = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int maxSendDay = Integer.MIN_VALUE;

        Gift[] gifts = new Gift[n];
        for (int i = 0; i < n; i++) {
            int d = scanner.nextInt();
            int c = scanner.nextInt();
            int s = scanner.nextInt();

            if (d + c > s) {
                System.out.println("NO");
                return;
            }

            if(s > maxSendDay) {
                maxSendDay = s;
            }
            gifts[i] = new Gift(d, c, s);
        }

        boolean[] giftMask = new boolean[maxSendDay + 1];
        backtrack(gifts, giftMask);

        if (success) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static void backtrack(Gift[] gifts, boolean[] giftMask) {
        int result = cleanUp(gifts, giftMask);
        if (result == -1) return;
        if (result == 0) success = true;
        if (!success) {
            for (int j = 0; j < gifts.length; j++) {
                Gift gift = gifts[j];
                if (gift != null) {
                    for(int i = gift.start; i < gift.end + 1; i++) {
                        if(!giftMask[i]){
                            boolean[] giftMaskCopy = giftMask.clone();
                            Gift[] giftsClone = gifts.clone();

                            giftMaskCopy[i] = true;
                            giftsClone[j] = null;
                            backtrack(giftsClone, giftMaskCopy);
                        }
                    }
                }
            }
        }
    }


    static int cleanUp(Gift[] gifts, boolean[] giftMask) {
        int result = 0;
        for (int j = 0; j < gifts.length; j++) {
            Gift gift = gifts[j];
            if (gift!= null){
                result++;
                int spaceCount = 0;
                int tmp = 0;
                for(int i = gift.start; i < gift.end + 1; i++) {
                    if(!giftMask[i]){
                        tmp = i;
                        spaceCount++;
                    }
                }
                if (spaceCount == 0){
                    return -1;
                }
                if (spaceCount == 1){
                    giftMask[tmp] = true;
                    gifts[j] = null;
                }
            }
        }
        return result;
    }

    static class Gift {
        int start;
        int end;
        public Gift(int d, int c, int s) {
            this.start = d + c;
            this.end = s;
        }

    }
}
