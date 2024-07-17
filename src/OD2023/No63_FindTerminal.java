package OD2023;

import java.util.Arrays;
import java.util.Scanner;

public class No63_FindTerminal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(arr));
    }
    public static int getResult(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length / 2; i++) {
            min = Math.min(recursive(arr, i, 1), min);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    public static int recursive(int[] arr, int curIdx, int step) {
        int next = arr[curIdx] + curIdx;
        int n = arr.length;
        if (next == n - 1) {
            return step + 1;
        } else if (next > n - 1) {
            return Integer.MAX_VALUE;
        } else {
            return recursive(arr, next, step + 1);
        }
    }

}

