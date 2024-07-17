package OD2023;

import java.util.Scanner;

public class No62_IncreasedSerialNums {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(getResult(str));
    }

    /**
     * 注意，这题不能通过先将字母全部去除，再计算剩下的纯数字字符串中非严格连续递增子序列。
     * eg：a1b2c3，实际上答案是1，去除字母后，字符串变成了123，长度就是3。
     */
    public static int getResult(String str) {
        int max = 0;
        int l = 0, r = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch > '9' || ch < '0') { //字母
                max = Math.max(max, r - l);
                r++;
                l = r;
            } else {    //数字
                if (l < r) {
                    if (ch >= str.charAt(r - 1)) {
                        max = Math.max(max, r - l + 1);
                    } else {
                        max = Math.max(max, r - l);
                        l = r;
                    }
                } else {
                    max = Math.max(1, max);
                }
                r++;
            }
        }
        return max;
    }
}

