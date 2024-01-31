package tinkoff;

import java.util.Scanner;
import java.util.regex.*;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String regex = "code\\d+";
        String result = input.replaceAll(regex, "???");
        System.out.println(result);

    }
}


