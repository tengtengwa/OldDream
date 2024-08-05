package OD2024;

import java.util.Arrays;
import java.util.Scanner;

public class B_6 {
    private static class Card {
        int num;
        String color;

        public Card(int num, String color) {
            this.num = num;
            this.color = color;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine()
                        .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        String[] colors = sc.nextLine().split(" ");
        Card[] cards = new Card[nums.length];
        for (int i = 0; i < nums.length; i++) {
            cards[i] = new Card(nums[i], colors[i]);
        }
        //ans用数组类型而不是int类型是因为：基本类型在dfs调用结束后会释放掉，而数组是引用类型，dfs调用完不会释放。
        int[] ans = new int[1];
        boolean[] used = new boolean[cards.length];
        dfs(cards, used, null, 0, ans);
        System.out.println(ans[0]);
    }

    private static void dfs(Card[] cards, boolean[] used, Card last, int count, int[] ans) {
        ans[0] = Math.max(ans[0], count);
        for (int i = 0; i < cards.length; i++) {
            if (used[i]) {
                continue;
            }
            Card cur = cards[i];
            if (last != null && cur.num != last.num && !cur.color.equals(last.color)) {
                continue;
            }
            used[i] = true;
            dfs(cards, used, cur, count + 1, ans);
            used[i] = false;
        }
    }
}
