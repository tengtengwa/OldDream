package OD2023;

import java.util.Scanner;

public class No37_EncodeInt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(getRes(sc.nextLong()));
    }

    private static String getRes(long num) {
        StringBuilder sb = new StringBuilder();
        String bin = Long.toBinaryString(num);
        int end = bin.length();
        while (end >= 8) {
            String last7 = "1" + bin.substring(end - 7, end);
            sb.append(Integer.toHexString(Integer.parseInt(last7, 2)).toUpperCase());
            end -= 7;
        }
        if (end > 0) {
            int lastInt = Integer.parseInt(bin.substring(0, end), 2);
            String lastStr = Integer.toHexString(lastInt).toUpperCase();
            if (lastStr.length() <= 1) {
                lastStr = "0" + lastStr;
            }
            sb.append(lastStr);
        }
        return sb.toString();
    }
}