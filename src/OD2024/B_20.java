package OD2024;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class B_20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] scores = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int k = sc.nextInt();

        System.out.println(maxSlidingWindow(scores, k));
    }

    private static int maxSlidingWindow(int[] scores, int k) {
        int n = scores.length;
        Deque<Integer> deque = new LinkedList<>();
        int[] dp = new int[n];
        dp[0] = scores[0];
        deque.addLast(0);
        //防止k>n时，下面一行deque访问越界。所以这里要取Math.min(n, k)
        for (int i = 1; i < Math.min(n, k); i++) {
            dp[i] = dp[deque.peek()] + scores[i];
            //注意这里是dp中当前元素和deque末尾元素比较，将比当前小的元素索引全部移除
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }
        for (int i = k; i < n; i++) {
            dp[i] = dp[deque.peek()] + scores[i];
            //也是dp中当前元素和deque末尾元素比较
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
            //队首索引超出窗口时，将其移除
            while (deque.peek() < i + 1 - k) {
                deque.pollFirst();
            }
        }
        return dp[n - 1];
    }
}
