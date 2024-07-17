package OD2023;

import java.util.Arrays;
import java.util.Scanner;

public class No52_DiningRoom {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int m = Integer.parseInt(sc.nextLine());
        int[] pArr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(m, pArr));
    }

    public static int getResult(int m, int[] pArr) {
        int l = 0, r = Arrays.stream(pArr).max().orElse(0);
        while (l <= r) {
            int mid = (r + l) >> 1;
            int rest = m - pArr[0] + mid;
            for (int i = 1; i < pArr.length; i++) {
                rest = rest - pArr[i];
                if (rest < 0) {
                    break;
                }
                rest += mid;
            }
            if (rest < 0) {
                l = mid + 1;
            } else if (rest > 0) {
                r = mid - 1;
            } else break;
        }
        return l;
    }
}