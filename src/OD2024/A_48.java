package OD2024;

import java.util.Scanner;

public class A_48 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = Integer.parseInt(sc.nextLine());
        String[] arr = sc.nextLine().split("-");
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);

        StringBuilder subSb = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            String cur = arr[i];
            subSb.append(cur);
        }
        if (subSb.length() == 0) {

        } else if (subSb.length() <= k) {
            sb.append("-").append(handleSubstring(subSb.toString()));
        } else {
            int start = 0;
            while (start + k <= subSb.length()) {
                String subStr = subSb.substring(start, start + k);
                sb.append("-").append(handleSubstring(subStr));
                start += k;
            }
            if (start < subSb.length()) {
                String subStr = subSb.substring(start);
                sb.append("-").append(handleSubstring(subStr));
            }
        }
        System.out.println(sb);
    }

    private static String handleSubstring(String str) {
        int upperNum = 0;
        int lowerNum = 0;
        for (char ch : str.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upperNum++;
            } else if (Character.isLowerCase(ch)) {
                lowerNum++;
            }
        }

        if (upperNum > lowerNum) {
            return str.toUpperCase();
        } else if (upperNum < lowerNum) {
            return str.toLowerCase();
        } else {
            return str;
        }
    }
}
