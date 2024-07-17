package OD2023;

import java.util.Arrays;
import java.util.Scanner;

public class No50_MaxSerialSubserial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int target = sc.nextInt();
        System.out.println(getRes(nums, target));
    }

    private static int getRes(int[] nums, int target) {
        if (target <= 0) {
            return -1;
        }
        int n = nums.length;
        int l = 0, r = 0;
        int cur = nums[0];
        int max = -1;
        while (true) {
            if (cur < target) {
                r++;    //r先++，再判断，防止越界
                if (r < n) {
                    cur += nums[r];
                } else {
                    break;
                }
            } else if (cur > target) {
                cur -= nums[++l - 1];
            } else {
                max = Math.max(max, r - l + 1);
                r++;
                l++;
                //更新窗口索引后立马判断是否越界，越界直接退出循环
                if (r < n) {
                    cur += nums[r] - nums[l - 1];
                } else {
                    break;
                }
            }
        }
        return max;
    }
}