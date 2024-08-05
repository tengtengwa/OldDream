package OD2024;

import java.util.PriorityQueue;
import java.util.Scanner;

public class B_33 {
    private static final int[][] offsets = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int[][] matrix = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        //dist[i]存储从坐标0到i的所有路径中，最高评分的路径的分数
        //其中 i 是将二维坐标一维化后的值，比如(x,y)坐标一维化后为 x * c + y; (c是列数)
        int[] dist = new int[r * c];
        //(0,0)到(0,0)的路径评分为matrix[0][0]
        dist[0] = matrix[0][0];

        //优先队列中存储位置的一维坐标，根据路径评分降序排序
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> dist[b] - dist[a]);
        pq.add(0);
        while (pq.size() > 0) {
            //上个位置的一维坐标
            int oldCo = pq.poll();
            //注意这里坐标的计算和列数c有关
            int x = oldCo / c;
            int y = oldCo % c;
            if (x == r - 1 && y == c - 1) {
                break;
            }
            for (int[] offset : offsets) {
                int newX = x + offset[0];
                int newY = y + offset[1];
                //注意边界条件
                if (newX < 0 || newX >= r || newY < 0 || newY >= c) {
                    continue;
                }
                //新的一维坐标
                int newCo = newX * c + newY;
                //这里新坐标权值=min(上个位置的最大评分，新坐标权值)
                int newW = Math.min(dist[oldCo], matrix[newX][newY]);
                //新权值比之前记录的大，则进行更新
                if (newW > dist[newCo]) {
                    dist[newCo] = newW;
                    pq.add(newCo);
                }
            }
        }
        System.out.println(dist[r * c - 1]);
    }
}
