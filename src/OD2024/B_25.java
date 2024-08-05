package OD2024;

import java.util.*;

public class B_25 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int k = Integer.parseInt(sc.nextLine());

        ArrayList<LinkedList<Integer>> lists = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            // 本地测试，以空行作为输入截止条件
            if (line.length() == 0) break;

            Integer[] nums =
                    Arrays.stream(line.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

            lists.add(new LinkedList<>(Arrays.asList(nums)));
        }

        // 窗口矩阵，k行n列，每一列对应一个窗口，这里将二维矩阵一维化，方便后面赋值
        int[] windows = new int[k * n];
        // 窗口矩阵中正在赋值的索引位置
        int idx = 0;
        // 正在从第level个列表中取值
        int level = 0;

        // 当窗口矩阵填满后，结束循环
        while (idx < windows.length) {
            // 当前轮次是否发生了"借"动作
            boolean flag = false;

            // 从第level个列表中取前n个元素
            for (int i = 0; i < n; i++) {
                windows[idx++] = lists.get(level).removeFirst();

                // 如果第level个列表没有元素了，则继续切到下一个列表中"借"
                if (lists.get(level).size() == 0 && lists.size() > 1) {
                    lists.remove(level); // 删除空列表
                    level %= lists.size(); // 防止越界
                    flag = true; // 发生了"借"动作
                }
            }

            // 如果没有发生"借"动作，则需要切到下一行
            if (!flag) {
                level = (level + 1) % lists.size(); // 防止越界
            }
        }

        StringJoiner sj = new StringJoiner(" ");

        // 遍历窗口矩阵的每一列
        for (int j = 0; j < n; j++) { // 遍历列号
            for (int i = 0; i < k; i++) { // 遍历行号
                sj.add(windows[i * n + j] + ""); // 将每一列的元素进行拼接
            }
        }

        System.out.println(sj);
    }
}
