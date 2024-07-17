package OD2023;

import java.util.*;

public class T19_MaxBroadcastResp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[][] sides = new int[s][2];
        for (int i = 0; i < s; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            sides[i][0] = u;
            sides[i][1] = v;
        }
        int src = sc.nextInt();
        System.out.println(getResult(sides, n, src));
    }

    public static int getResult(int[][] sides, int n, int src) {
        int max = 0;
        Deque<Integer> queue = new LinkedList<>();
        queue.add(src);
        int[][] visit = new int[n + 1][n + 1];
        //邻接链表
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] side : sides) {
            Integer u = side[0];
            Integer v = side[1];
            map.putIfAbsent(u, new ArrayList<>());
            map.get(u).add(v);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int u = queue.removeFirst();
                List<Integer> list = map.get(u);
                if (list == null) {
                    continue;
                }
                for (int v : list) {
                    if (visit[u][v] == 0 && !queue.contains(v)) {
                        queue.add(v);
                    }
                    visit[u][v] = 1;
                    visit[v][u] = 1;
                }
            }
            max++;
        }
        return (max - 1) << 1;
    }
}