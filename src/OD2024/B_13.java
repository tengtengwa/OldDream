package OD2024;

import java.util.Arrays;
import java.util.Scanner;

public class B_13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int wa = sc.nextInt();
        int wb = sc.nextInt();
        int wt = sc.nextInt();
        int pa = sc.nextInt();
        int pb = sc.nextInt();

        // 背包承重, 这里减去必然需要装入的一件A货物和一件B货物
        wt -= wa + wb;

        // 物品的重量
        int[] w = new int[] {wa, wb};

        // 物品的价值
        int[] p = new int[] {pa, pb};

        // maxP是装满承重为 wt 的背包的最大价值
        int maxP = getResult(wt, 2, w, p);

        if (maxP >= 0) {
            // 如果maxP是非负数，则存在装满背包的方案，注意最后返回结果需要加上初始装上车的一件A和一件B的利润
            System.out.println(maxP + pa + pb);
        } else {
            // 如果maxP是负数，则说明不存在装满wt的方案，此时直接直接0
            System.out.println(0);
        }
    }

    /**
     * 完全背包
     *
     * @param bag 背包承重
     * @param n 物品种数
     * @param w 物品的重量数组
     * @param p 物品的价值数组
     * @return 装满背包的最大价值
     */
    public static int getResult(int bag, int n, int[] w, int[] p) {
        // dp[i]表示装满承重为 i 的背包的最大价值
        int[] dp = new int[bag + 1];

        // 装满背包的背包问题，初始化时需要将除了dp[0]外的dp元素值设为负无穷
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) { // 遍历物品
            for (int j = w[i]; j <= bag; j++) { // 遍历背包承重，完全背包这里要正序遍历
                dp[j] = Math.max(dp[j], dp[j - w[i]] + p[i]);
            }
        }

        // 返回装满承重为bag的背包的最大价值
        return dp[bag];
    }
}