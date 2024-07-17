package OD2024;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 解密犯罪时间
 *
 * <a href="https://blog.csdn.net/qfc_128220/article/details/127634016">解密犯罪时间</a>
 */
public class A_68 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = Arrays.stream(sc.nextLine().split(":"))
                .reduce((a, b) -> a + b)
                .orElse("");
        int[] res = getResult(str);
        System.out.println("" + res[0] + res[1] + ":" + res[2] + res[3]);
    }

    private static int[] getResult(String str) {
        //set对字符去重
        HashSet<Character> set = new HashSet<>();
        int[] arr = new int[4];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            arr[i] = ch - '0';
            set.add(ch);
        }
        //排序去重后的字符数组
        int[] orderedArr = set.stream().sorted()
                .mapToInt(a -> a - '0')
                .toArray();
        for (int i = 3; i >= 0; i--) {
            int curInt = arr[i];
            //寻找比当前位置大的数
            int nextInt = -1;
            for (int k : orderedArr) {
                if (k > curInt) {
                    nextInt = k;
                    break;
                }
            }
            //如果没找到更大的数，或者不合法，则替换为最小的数
            if (nextInt == -1
                    || i == 2 && nextInt > 5
                    || i == 1 && arr[0] == 2 && nextInt > 3
                    || i == 0 && nextInt > 2) {
                arr[i] = orderedArr[0];
            } else {
                //找到更大的数，替换为该数后即是结果
                arr[i] = nextInt;
                return arr;
            }
        }
        return arr;
    }
}