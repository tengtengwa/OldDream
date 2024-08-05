package OD2024;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class B_7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 基站数量（节点数）
        int m = sc.nextInt(); // 基站对数量（边数）

        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            int p = sc.nextInt();

            edges[i] = new Edge(x, y, (p == 0 ? z : 0));
        }

        System.out.println(kruskal(edges, n));
    }

    private static int kruskal(Edge[] edges, int n) {
        int minWeight = 0;
        Arrays.sort(edges, Comparator.comparingInt(a -> a.weight));
        UFSet set = new UFSet(n + 1);
        for (Edge edge : edges) {
            if (set.find(edge.from) != set.find(edge.to)) {
                minWeight += edge.weight;
                set.union(edge.from, edge.to);
            }
            if (set.count == 2) {
                return minWeight;
            }
        }
        return -1;
    }

    private static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static class UFSet {
        int n;
        int[] fa;
        int count;  //连通分量数量

        public UFSet(int n) {
            this.n = n;
            count = n;
            fa = new int[n + 1];
            for (int i = 0; i < fa.length; i++) {
                fa[i] = i;
            }
        }

        public int find(int x) {
            if (x != fa[x]) {
                return (fa[x] = find(fa[x]));
            }
            return x;
        }

        public void union(int x, int y) {
            int faX = find(x);
            int faY = find(y);
            if (faX != faY) {
                fa[faY] = faX;
                count--;
            }
        }
    }
}
