package OD2023;

import java.util.Scanner;


public class No40_PrintReverseNum {
    private static final String SPACE = "    ";


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        getRes(sc.nextInt());
    }

    private static void getRes(int lines) {
        int cur = 1;
        for (int i = 1; i <= lines; i++) {    //每行i个数字
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < lines - i; k++) {
                sb.append(SPACE);
            }
            if ((i & 1) == 0) {     //偶数，逆序
                for (int j = cur + i - 1; j >= cur; j--) {
                    sb.append(j);
                    int tem = j;
                    for (int m = 0; m < 3; m++) {
                        if (tem / 10 <= 0) {
                            sb.append("*");
                        }
                        tem /= 10;
                    }
                    if (j != i) sb.append(SPACE);
                }
            } else {    //奇数，正序
                for (int j = cur; j < cur + i; j++) {
                    sb.append(j);
                    int tem = j;
                    for (int m = 0; m < 3; m++) {
                        if (tem / 10 <= 0) {
                            sb.append("*");
                        }
                        tem /= 10;
                    }
                    if (j != cur + i - 1) sb.append(SPACE);
                }
            }
            cur += i;
            System.out.println(sb);
        }
    }
}