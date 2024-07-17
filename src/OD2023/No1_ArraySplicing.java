package OD2023;

import java.util.*;
import java.util.stream.Collectors;

public class No1_ArraySplicing {
    public static void main(String[] args) {
        Solution1 s = new Solution1();
        s.getNewArray();
    }
}

/**
 * <a href="https://blog.csdn.net/qfc_128220/article/details/127418403?ops_request_misc=&request_id=df543e7b2ee74ebeb0e670ed1843e27a&biz_id=&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~koosearch~default-1-127418403-null-null.268^v1^control&utm_term=%E6%95%B0%E7%BB%84%E6%8B%BC%E6%8E%A5&spm=1018.2226.3001.4450">题目链接</a>
 * <p>
 *
 * No1、数组拼接
 * <p>
 *
 * 题目描述：
 * 现在有多组整数数组，需要将它们合并成一个新的数组。
 * 合并规则，从每个数组里按顺序取出固定长度的内容合并到新的数组中，取完的内容会删除掉，如果该行不足固定长度或者
 * 已经为空，则直接取出剩余部分的内容放到新的数组中，继续下一行。
 * <p>
 * 输入描述：
 * 第一行是每次读取的固定长度，0 < 长度 < 10
 * 第二行是整数数组的数目，0 < 数目 < 1000
 * 第3-n行是需要合并的数组，不同的数组用回车换行分隔，数组内部用逗号分隔，最大不超过100个元素。
 * <p>
 * 输出描述：
 * 输出一个新的数组，用逗号分隔。
 *
 *
 * 思路：
 * 1、将输入的所有数组读入一个二维列表中
 * 2、接着遍历二维列表，每次从取出的一维列表中移除前subLen个元素，并将移除的元素加入结果列表中，如果一维列表中元素
 * 少于subLen个，则移除剩下的元素并加入结果列表
 * 3、打印结果列表
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(N)，N为数组长度
 */

class Solution1 {
    public void getNewArray() {
        Scanner sc = new Scanner(System.in);
        int subLen = Integer.parseInt(sc.nextLine());
        int arrNums = Integer.parseInt(sc.nextLine());
        List<List<String>> arrs = new LinkedList<>();
        while (arrNums > 0) {
            arrs.add(Arrays.stream(sc.nextLine().split(","))
                    .collect(Collectors.toList()));
            arrNums--;
        }
        System.out.println(getAns(arrs, subLen));
    }

    private String getAns(List<List<String>> arrs, int subLen) {
        List<String> res = new ArrayList<>();
        while (!arrs.isEmpty()) {
            List<String> cur = arrs.remove(0);
            res.addAll(removeRange(cur, subLen));
            if (cur.size() > 0) {
                arrs.add(cur);
            }
        }
        StringJoiner sb = new StringJoiner(",");
        for (String num : res) {
            sb.add(num);
        }
        return sb.toString();
    }

    /**
     * 返回移除的列表
     */
    private List<String> removeRange(List<String> list, int r) {
        if (list.size() < r) {
            r = list.size();
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            res.add(list.remove(0));
        }
        return res;
    }
}
