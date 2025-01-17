package OD2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class A_57 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int minAverageLost = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(nums, minAverageLost));
    }

    public static String getResult(int[] nums, int minAverageLost) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        ArrayList<int[]> ans = new ArrayList<>();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int sum = preSum[j] - preSum[i];
                int curLen = j - i;
                int lost = curLen * minAverageLost;
                if (sum <= lost) {
                    if (maxLen <= curLen) {
                        if (maxLen < curLen) {
                            ans = new ArrayList<>();
                        }
                        ans.add(new int[]{i, j - 1});
                        maxLen = curLen;
                    }
                }
            }
        }

        if (ans.size() == 0) return "NULL";

        ans.sort((a, b) -> a[0] - b[0]);

        StringJoiner sj = new StringJoiner(" ");
        for (int[] an : ans) sj.add(an[0] + "-" + an[1]);
        return sj.toString();
    }
}