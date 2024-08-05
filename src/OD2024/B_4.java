package OD2024;

import java.util.*;

public class B_4 {
    static class TreeNode {
        int num; // 当前节点的值
        int childSum; // 当前节点的左子树+右子树的和
        TreeNode left;
        TreeNode right;

        public TreeNode(int num) {
            this.num = num;
            this.childSum = 0;
            this.left = null;
            this.right = null;
        }
    }

    // 中序遍历序列
    static int[] midOrder;

    // 前序遍历序列
    static int[] preOrder;

    // 记录中序遍历序列中，序列元素值所在位置，本题中可能存在重复元素，因此某个序列元素值可能有多个位置
    static HashMap<Integer, ArrayList<Integer>> midIndexMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        midOrder = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        preOrder = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = midOrder.length;
        for (int i = 0; i < n; i++) {
            int num = midOrder[i];
            midIndexMap.putIfAbsent(num, new ArrayList<>());
            midIndexMap.get(num).add(i);
        }

        TreeNode root = buildTree(0, n - 1, 0, n - 1);
        StringJoiner sj = new StringJoiner(" ");
        getMidOrder(root, sj);
        System.out.println(sj);
    }

    private static TreeNode buildTree(int midL, int midR, int preL, int preR) {
        if (midR < midL || preR < preL) {
            return null;
        }
        int rootNum = preOrder[preL];
        TreeNode root = new TreeNode(rootNum);
        ArrayList<Integer> list = midIndexMap.get(rootNum);
        for (int idx : list) {
            if (idx < midL || idx > midR) {
                continue;
            }
            int leftLen = idx - midL;
            if (!equal(midL, preL + 1, leftLen)) {
                continue;
            }
            int rightLen = midR - idx;
            root.left = buildTree(midL, idx - 1, preL + 1, preL + leftLen);
            root.right = buildTree(idx + 1, midR, preR - rightLen + 1, preR);
            root.childSum = (root.left == null ? 0 : root.left.childSum + root.left.num)
                    + (root.right == null ? 0 : root.right.childSum + root.right.num);
            break;
        }
        return root;
    }

    private static boolean equal(int midL, int preL, int len) {
        int[] arrMid = Arrays.copyOfRange(midOrder, midL, midL + len);
        int[] arrPre = Arrays.copyOfRange(preOrder, preL, preL + len);
        Arrays.sort(arrMid);
        Arrays.sort(arrPre);
        for (int i = 0; i < arrMid.length; i++) {
            if (arrMid[i] != arrPre[i]) {
                return false;
            }
        }
        return true;
    }

    private static void getMidOrder(TreeNode root, StringJoiner sj) {
        if (root == null) {
            return;
        }
        getMidOrder(root.left, sj);
        sj.add(root.childSum + "");
        getMidOrder(root.right, sj);
    }
}
