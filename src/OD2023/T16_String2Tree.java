package OD2023;

import java.util.*;

/**
 * <a href="https://blog.csdn.net/qfc_128220/article/details/130774170?ops_request_misc=&request_id=
 * 782be426e011423abea8acb0d1e17c21&biz_id=&utm_medium=distribute.pc_search_result.none-tas
 * k-blog-2~blog~koosearch~default-1-130774170-null-null.268^v1^control&utm_term=%E8%AF%84%
 * E8%AE%BA%E8%BE%93%E5%87%BA%E8%BD%AC%E6%8D%A2&spm=1018.2226.3001.4450">评论转换输出</a>
 */
public class T16_String2Tree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(",");
        getRes(strs);
    }

    private static void getRes(String[] strs) {
        //这个list的下标+1对应树的第i层，每一层由一个列表构成，表示这一层的所有节点的集合。
        List<List<String>> tree = new ArrayList<>();
        Deque<String> queue = new LinkedList<>(Arrays.asList(strs));
        int level = 1;
        while (!queue.isEmpty()) {
            //当前层还没有创建列表，先创建
            if (tree.size() < level) {
                tree.add(new ArrayList<>());
            }
            //加入当前根节点
            tree.get(0).add(queue.removeFirst());
            int subNum = Integer.parseInt(queue.removeFirst());
            //递归处理下一层
            dfs(level + 1, queue, tree, subNum);
        }
        System.out.println(tree.size());
        for (List<String> curLevel : tree) {
            System.out.println(String.join(" ", curLevel));
        }
    }

    private static void dfs(int curLevel, Deque<String> queue, List<List<String>> tree, int subNum) {
        //当前层有subNum个节点，就处理subNum次
        for (int i = 0; i < subNum; i++) {
            if (tree.size() < curLevel) {
                tree.add(new ArrayList<>());
            }
            tree.get(curLevel - 1).add(queue.removeFirst());
            int subCount = Integer.parseInt(queue.removeFirst());
            //当前节点的子评论数subCount>0时，才需要递归处理下一层
            if (subCount > 0) {
                dfs(curLevel + 1, queue, tree, subCount);
            }
        }
    }
}
