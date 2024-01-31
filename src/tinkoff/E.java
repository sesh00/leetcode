package tinkoff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class E {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        int v = scanner.nextInt(), e = v - 1;

        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }

        int to, from;
        for (int i = 0; i < e; i++) {
            to = scanner.nextInt()-1;
            from = scanner.nextInt()-1;
            adj.get(to).add(from);
            adj.get(from).add(to);
        }

        int[] parent = new int[v];

        int[] depth = new int[v];

        dfs(adj, 0, -1, parent, depth);

        int numQuestions = scanner.nextInt();

        for (int i = 0; i < numQuestions; i++) {
            int v1 = scanner.nextInt()-1, v2 = scanner.nextInt()-1;

            int lca = getLCA(v1, v2, depth, parent);

            if(adj.get(v1).contains(v2)){
                System.out.println(v2+1);
            } else {
                System.out.println(dfsDown(adj, lca, v1, depth)+1);
            }


        }
    }

    private static int dfsDown(ArrayList<ArrayList<Integer>> adj, int s, int e, int[] depth) {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            for (int adjacent : adj.get(current)) {
                if (adjacent == e) {
                    return current;
                }
                if (depth[adjacent] > depth[current]) {
                    stack.push(adjacent);
                }
            }
        }
        return s;
    }



    private static void dfs(ArrayList<ArrayList<Integer>> adj, int s, int p, int[] parent, int[] depth) {
        for (int adjacent : adj.get(s)) {
            if (adjacent != p) {
                parent[adjacent] = s;
                depth[adjacent] = 1 + depth[s];
                dfs(adj, adjacent, s, parent, depth);
            }
        }
    }


    private static int getLCA(int v1, int v2, int[] depth, int[] parent) {
        if (depth[v1] < depth[v2]) {
            int temp = v1;
            v1 = v2;
            v2 = temp;
        }
        while (depth[v1] != depth[v2]) {
            v1 = parent[v1];
        }
        if (v1 == v2) return v1;
        while (v1 != v2) {
            v1 = parent[v1];
            v2 = parent[v2];
        }
        return v1;
    }



}


