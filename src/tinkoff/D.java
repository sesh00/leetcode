package tinkoff;


import java.util.*;





 class D {


     class Knapsack {
         static int max(int a, int b) { return (a > b) ? a : b; }
         static int knapSack(int W, int wt[], int val[], int n)
         {
             if (n == 0 || W == 0)
                 return 0;

             if (wt[n - 1] > W)
                 return knapSack(W, wt, val, n - 1);


             else
                 return max(val[n - 1]
                                 + knapSack(W - wt[n - 1], wt,
                                 val, n - 1),
                         knapSack(W, wt, val, n - 1));
         }

     }

     static class Graph {
         private int vertices;
         private List<List<Integer>> adjList;
         private List<Integer> param1;
         private List<Integer> param2;

         public List<Integer> totalSums1;
         public List<Integer> totalSums2;

         public void ComponentSums(List<Integer> totalSums1, List<Integer> totalSums2) {
             this.totalSums1 = totalSums1;
             this.totalSums2 = totalSums2;
         }


         public Graph(int vertices) {
             this.vertices = vertices;
             adjList = new ArrayList<>();
             param1 = new ArrayList<>();
             param2 = new ArrayList<>();

             for (int i = 0; i < vertices; i++) {
                 adjList.add(new ArrayList<>());
             }
         }

         public void addEdge(int v, int w) {
             adjList.get(v).add(w);
             adjList.get(w).add(v);
         }

         public void addParam1(int v, int value) {
             param1.add(v, value);
         }

         public void addParam2(int v, int value) {
             param2.add(v, value);
         }

         public void dfs(int start, boolean[] visited, List<Integer> component) {
             Stack<Integer> stack = new Stack<>();
             stack.push(start);
             visited[start] = true;
             component.add(start);

             while (!stack.isEmpty()) {
                 int currentVertex = stack.pop();

                 for (int neighbor : adjList.get(currentVertex)) {
                     if (!visited[neighbor]) {
                         stack.push(neighbor);
                         visited[neighbor] = true;
                         component.add(neighbor);
                     }
                 }
             }
         }

         public void findConnectedComponentsSums() {
             boolean[] visited = new boolean[vertices];


             List<Integer> totalSums1 = new ArrayList<>();
             List<Integer> totalSums2 = new ArrayList<>();

             for (int v = 0; v < vertices; v++) {
                 if (!visited[v]) {
                     List<Integer> component = new ArrayList<>();
                     dfs(v, visited, component);
                     int[] componentSum = calculateComponentSum(component);
                     totalSums1.add(componentSum[0]);
                     totalSums2.add(componentSum[1]);
                     ComponentSums(totalSums1, totalSums2);
                 }
             }

         }

         public int[] calculateComponentSum(List<Integer> component) {
             int sum1 = 0;
             int sum2 = 0;
             for (int vertex : component) {
                 sum1 += param1.get(vertex);
                 sum2 += param2.get(vertex);
             }
             return new int[]{sum1, sum2};
         }
     }

     public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        int numFriends = scanner.nextInt();
        int numPairs = scanner.nextInt();
        int maxTotalAppetite = scanner.nextInt();

        Graph graph = new Graph(numFriends);

        for (int i = 0; i < numFriends; i++) {
            graph.addParam1(i, scanner.nextInt());
        }

        for (int i = 0; i < numFriends; i++) {
            graph.addParam2(i, scanner.nextInt());
        }

        for (int i = 0; i < numPairs; i++) {
            int friend1 = scanner.nextInt() - 1;
            int friend2 = scanner.nextInt() - 1;
            graph.addEdge(friend1, friend2);
        }

        graph.findConnectedComponentsSums();

        int[] sums1Array = graph.totalSums1.stream().mapToInt(Integer::intValue).toArray();
        int[] sums2Array = graph.totalSums2.stream().mapToInt(Integer::intValue).toArray();

        System.out.print(Knapsack.knapSack(maxTotalAppetite, sums2Array, sums1Array, sums1Array.length));


    }

}



