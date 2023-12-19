package qualification;

import java.util.ArrayList;
import java.util.Scanner;


public class I {

    static ArrayList<Integer>[] adjList;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] sequence = new int[n];
        adjList = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            sequence[i] = scanner.nextInt();
            adjList[i] = new ArrayList<>();
        }

        buildGraph(sequence);

        ArrayList<ArrayList<Integer>> connectedComponents = findConnectedComponents(n);

        int result = findMaxNonOverlappingLength(connectedComponents);

        if (connectedComponents.size() == 1) {
            System.out.println(connectedComponents.get(0).size());
        } else {
            System.out.println(result);
        }


    }



    static void buildGraph(int[] sequence) {
        for (int i = 0; i < sequence.length; i++) {
            for (int j = i + 1; j < sequence.length; j++) {
                if (Math.abs(sequence[i] - sequence[j]) == 1 || Math.abs(sequence[i] - sequence[j]) % 7 == 0) {
                    adjList[i].add(j);
                }
            }
        }
    }

    static ArrayList<ArrayList<Integer>> findConnectedComponents(int n) {
        ArrayList<ArrayList<Integer>> connectedComponents = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ArrayList<Integer> component = new ArrayList<>();
                dfs(i, component);
                connectedComponents.add(component);
            }
        }

        return connectedComponents;
    }

    static void dfs(int node, ArrayList<Integer> component) {
        visited[node] = true;
        component.add(node);

        for (int neighbor : adjList[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor, component);
            }
        }
    }

    static int findMaxNonOverlappingLength(ArrayList<ArrayList<Integer>> connectedComponents) {
        int result = 0;

        for (int i = 0; i < connectedComponents.size(); i++) {
            for (int j = i + 1; j < connectedComponents.size(); j++) {
                if (!hasIntersection(connectedComponents.get(i), connectedComponents.get(j))) {
                    result = Math.max(result, connectedComponents.get(i).size() + connectedComponents.get(j).size());
                }
            }
        }

        return result;
    }

    static boolean hasIntersection(ArrayList<Integer> component1, ArrayList<Integer> component2) {
        for (int node : component1) {
            if (component2.contains(node)) {
                return true;
            }
        }
        return false;
    }

}
