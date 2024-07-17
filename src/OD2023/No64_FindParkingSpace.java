package OD2023;

import java.util.Arrays;
import java.util.Scanner;

public class No64_FindParkingSpace {
    //1,0,0,0,0,1,0,0,1,0,1
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(arr));
    }
    public static int getResult(int[] arr) {
        int max = 0;
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int last1 = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                last1 = i;
            } else {
                left[i] = last1 == -1 ? Integer.MAX_VALUE : i - last1;
            }
        }
        last1 = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 1) {
                last1 = i;
            } else {
                right[i] = last1 == -1 ? Integer.MAX_VALUE : last1 - i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                max = Math.max(max, right[i]);
            } else if (i == n - 1) {
                max = Math.max(max, left[i]);
            } else {
                int min = Math.min(left[i], right[i]);
                max = Math.max(max, min);
            }
        }
        return max;
    }
}

