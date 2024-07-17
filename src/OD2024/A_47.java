package OD2024;

import java.util.Scanner;

public class A_47 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int ans = -1;
        int l = 0, r = -1;
        int letterNum = 0;
        int digitNum = 0;
        while (r < str.length() - 1) {
            r++;
            if (Character.isLetter(str.charAt(r))) {
                letterNum++;
                while (letterNum > 1) {
                    if (Character.isLetter(str.charAt(l++))) {
                        letterNum--;
                    }
                }
            } else {
                digitNum++;
            }
            if (letterNum == 1 && digitNum > 0) {
                ans = Math.max(ans, r - l + 1);
            }
        }
        System.out.println(ans);
    }
}