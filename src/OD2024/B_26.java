package OD2024;

import java.util.Scanner;

public class B_26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        String s1 = str[0];
        String s2 = str[1];

        int[] pre = new int[s1.length() + 1];
        int[] cur = new int[s1.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            pre[i] = i;
        }
        for (int row = 1; row <= s2.length(); row++) {
            cur[0] = row;
            for (int col = 1; col <= s1.length(); col++) {
                if (s1.charAt(col - 1) == s2.charAt(row - 1)) {
                    cur[col] = pre[col - 1] + 1;
                } else {
                    cur[col] = Math.min(cur[col - 1], pre[col]) + 1;
                }
            }
            pre = cur;
            cur = new int[s1.length() + 1];
        }
        System.out.println(pre[s1.length()]);
    }
}
