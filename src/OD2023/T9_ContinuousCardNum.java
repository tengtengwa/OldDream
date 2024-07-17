package OD2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class T9_ContinuousCardNum {
    private static int max = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[] colors = sc.nextLine().split(" ");
        List<Card> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(new Card(nums[i], colors[i]));
        }
        dfs(list, new int[nums.length], null, 0);
        System.out.println(max);
    }

    private static void dfs(List<Card> list, int[] usd, Card last, int curLen) {
        max = Math.max(curLen, max);
        for (int i = 0; i < list.size(); i++) {
            if (usd[i] == 1) continue;
            Card card = list.get(i);
            if (last != null && (last.num != card.num && !last.color.equals(card.color))) {
                continue;
            }
            usd[i] = 1;
            dfs(list, usd, card, curLen + 1);
            usd[i] = 0;
        }
    }

    private static class Card {
        int num;
        String color;

        public Card(int num, String color) {
            this.num = num;
            this.color = color;
        }
    }
}