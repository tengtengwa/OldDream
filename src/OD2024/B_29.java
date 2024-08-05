package OD2024;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class B_29 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        String charsStr = strs[0];
        int len = Integer.parseInt(strs[1]);
        int[] ans = new int[1];
        char[] chars = charsStr.toCharArray();
        //非法字符检查
        for (char ch : chars) {
            if (ch < 'a' || ch > 'z') {
                System.out.println(0);
                return;
            }
        }
        //排序是剪枝的前提
        Arrays.sort(chars);
        recursive(chars, new LinkedList<>(), new boolean[chars.length], ans, len);
        System.out.println(ans[0]);
    }

    private static void recursive(char[] chars, LinkedList<Character> cur, boolean[] used, int[] ans, int len) {
        if (cur.size() == len) {
            ans[0]++;
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            //跳过已使用字符
            if (used[i]) {
                continue;
            }
            //同层剪枝，注意这里的条件!used[i - 1]，同一层前一个数是没用过的
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) {
                continue;
            }
            //相邻元素剪枝
            if (cur.size() > 0 && chars[i] == cur.get(cur.size() - 1)) {
                continue;
            }
            used[i] = true;
            cur.add(chars[i]);

            recursive(chars, cur, used, ans, len);

            used[i] = false;
            cur.removeLast();
        }
    }
}
