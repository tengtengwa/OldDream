package OD2023;

import java.util.Arrays;
import java.util.Scanner;

public class No65_Alibaba5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int k = sc.nextInt();
        System.out.println(getResult(arr, k));
    }

    public static int getResult(int[] arr, int k) {
        int max;
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        max = sum;  //数字中有负数，max初始值要么置为MIN，要么在这里初始化
        for (int r = k, l = 0; r < n; r++) {
            sum += arr[r] - arr[l++];
            max = Math.max(max, sum);
        }
        return max;
    }
}

