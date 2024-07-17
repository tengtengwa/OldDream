package OD2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class No58_SplitString {
    public static void main(String[] args) {
        //这题可能有一些特殊字符，Java读取使显示为空格，所以只能将分隔符设置为\n，并且使用sc.next()读取
        Scanner sc = new Scanner(System.in).useDelimiter("[\n]");
        String str = sc.next();
        System.out.println(getResult(str));
    }

    private static int getResult(String str) {
        char[] charArr = str.toCharArray();
        int n = charArr.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + charArr[i - 1];
        }
        List<Integer> ans = new ArrayList<>();
        recursive(ans, charArr, 0, 0, prefix);
        if (ans.size() == 0) {
            return 0;
        } else if (ans.size() == 1) {
            return ans.get(0);
        } else {
            return -1;
        }
    }

    private static void recursive(List<Integer> ans, char[] charArr, int start, int count, int[] prefix) {
        if (start == charArr.length) {
            ans.add(count);
            return;
        }
        for (int end = start + 1; end <= charArr.length; end++) {
            if (isSxh(prefix[end] - prefix[start])) {
                recursive(ans, charArr, end, count + 1, prefix);
            }
        }
    }

    private static boolean isSxh(int num) {
        if (num < 100 || num > 999) {
            return false;
        }
        int g = num % 10;
        int s = (num / 10) % 10;
        int b = (num / 100) % 10;
        return b * b * b + s * s * s + g * g * g == num;
    }
}
