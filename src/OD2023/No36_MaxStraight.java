package OD2023;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

public class No36_MaxStraight {
    private static final Map<String, Integer> str2Idx = new HashMap<>();
    private static final Map<Integer, String> idx2Str = new HashMap<>();
    private static final String NO_CHAIN = "NO-CHAIN";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str1 = sc.nextLine().split("-");
        String[] str2 = sc.nextLine().split("-");
        System.out.println(getRes(str1, str2));
    }

    private static String getRes(String[] str1, String[] str2) {
        for (int i = 3; i <= 10; i++) {
            String str = String.valueOf(i);
            str2Idx.put(str, i);
            idx2Str.put(i, str);
        }
        str2Idx.put("J", 11);
        str2Idx.put("Q", 12);
        str2Idx.put("K", 13);
        str2Idx.put("A", 14);
        idx2Str.put(11, "J");
        idx2Str.put(12, "Q");
        idx2Str.put(13, "K");
        idx2Str.put(14, "A");
        int[] count = new int[15];
        for (String s: str1) {
            Integer idx = str2Idx.get(s);
            if (idx == null) {
                continue;
            }
            count[idx]++;
        }
        for (String s: str2) {
            Integer idx = str2Idx.get(s);
            if (idx == null) {
                continue;
            }
            count[idx]++;
        }

        String ans = NO_CHAIN;
        StringJoiner sj = new StringJoiner("-");
        int l = 3, r = 3;
        int max = 0;
        while (r <= count.length) {
            if (r == count.length || count[r] >= 4) {
                int len = r - l;
                r = r + 1;
                l = r;
                if (len < 5 || len < max) {
                    sj = new StringJoiner("-");
                    continue;
                }
                max = len;
                ans = sj.toString();
                sj = new StringJoiner("-");
            } else {
                sj.add(idx2Str.get(r++));
            }
        }
        return ans;
    }
}