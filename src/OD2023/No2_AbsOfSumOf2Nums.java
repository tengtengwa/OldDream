package OD2023;

import java.util.Arrays;
import java.util.Scanner;

public class No2_AbsOfSumOf2Nums {
    public static void main(String[] args) {
        Solution2 s = new Solution2();
        s.getMin();
    }
}

/**
 * <a href="https://blog.csdn.net/qfc_128220/article/details/127418103?ops_request_misc=
 * &request_id=30b116d2180b4657b8218d6c07d29d85&biz_id=&utm_medium=distribute.pc_search_
 * result.none-task-blog-2~blog~koosearch~default-1-127418103-null-null.268^v1^control&ut
 * m_term=%E4%B9%B1%E5%BA%8F%E6%95%B4%E6%95%B0%E5%BA%8F%E5%88%97%E4%B8%A4%E6%95%B0%E4%B9%
 * 8B%E5%92%8C%E7%BB%9D%E5%AF%B9%E5%80%BC%E6%9C%80%E5%B0%8F&spm=1018.2226.3001.4450">题目链接</a>
 * <p>
 *
 * No2、乱序整数序列两数之和绝对值最小
 * 给定一个随机的整数（可能存在正整数和负整数）数组 nums，请你在该数组中找出两个数，其和的绝对值
 * (|nums[x]+nums[y]|)为最小值，并返回这个两个数（按从小到大返回）以及绝对值。
 * 每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 *
 * 暴力法就不提了，时间复杂度是O(n^2)
 *
 * 思路：排序+双指针
 * 1、先对数组排序
 * 2、特判全是非负数、全是非正数的情况，剩下一种case是有正有负
 * 3、双指针，分别指向数组首尾，当两数之和>0时，说明右边的数大，右指针左移；两数之和<0时，左指针右移；
 * 否则两数之和为0，找到了和的最小绝对值以及两个数，直接退出循环即可。
 */
class Solution2 {
    public void getMin() {
        Scanner sc = new Scanner(System.in);
        int n1 = 0, n2 = 0, abs = Integer.MAX_VALUE;
        int[] arr = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = arr.length;

        Arrays.sort(arr);
        if (arr[0] < 0 && arr[n - 1] < 0) {
            n1 = arr[n - 2];
            n2 = arr[n - 1];
            abs = Math.abs(n1 + n2);
        }
        if (arr[0] > 0 && arr[n - 1] > 0) {
            n1 = arr[0];
            n2 = arr[1];
            abs = Math.abs(n1 + n2);
        }
        int l = 0, r = n - 1;
        while (l < r) {
            int sum = arr[l] + arr[r];
            if (Math.abs(sum) < abs) {
                abs = Math.abs(sum);
                n1 = arr[l];
                n2 = arr[r];
            }
            if (sum > 0) {
                r--;
            } else if (sum < 0) {
                l++;
            } else {
                break;
            }
        }
        System.out.println(n1 + " " + n2 + " " + abs);
    }
}