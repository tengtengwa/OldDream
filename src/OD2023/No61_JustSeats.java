package OD2023;

import java.util.Arrays;
import java.util.Scanner;

public class No61_JustSeats {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums));
    }

    public static int getResult(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                continue;
            }
            if ((i == 0 || 0 == nums[i - 1]) && (i == nums.length - 1 || 0 == nums[i + 1])) {
                nums[i] = 1;
                ans++;
            }
        }
        return ans;
    }
}

