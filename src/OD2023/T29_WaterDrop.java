package OD2023;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class T29_WaterDrop {
    /*
     * 二叉搜索树：
     * 若它的左子树不为空，则左子树上所有节点的值都小于根节点的值；
     * 若它的右子树不为空，则右子树上所有节点的值都大于根节点的值；
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 输入的节点值
        int[] arr = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 第一个节点为根节点
        TreeNode root = new TreeNode(arr[0]);

        // 保存节点
        Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
        // 加入根节点
        deque.push(root);
        // 当前节点的前一个节点
        TreeNode preNode = new TreeNode(-1);
        // 是否满足二叉搜索树属性
        boolean flag = true;
        // 判断并构造二叉搜索树
        for (int i = 1; i < arr.length; i++) {
            // 当前节点的前一个节点
            TreeNode node = deque.peekLast();
            // 当前节点
            TreeNode currentNode = new TreeNode(arr[i]);
            // 前一个节点的值小于当前节点的值
            while (!deque.isEmpty() && deque.peekLast().value < currentNode.value) {
                node = deque.removeLast();
                // 如果队列不为空，更新前一个节点值
                if (!deque.isEmpty()) {
                    preNode = deque.peekLast();
                }
            }

            // 小的值放在左子树
            if (node.value < currentNode.value) {
                node.right = currentNode;
                preNode = node;
            } else {
                // 不满足二叉搜索树属性直接跳出
                if (currentNode.value < preNode.value) {
                    flag = false;
                    break;
                }
                // 大的值放在右子树
                node.left = currentNode;
            }
            // 将当前的值加入队列
            deque.addLast(currentNode);
        }

        // 如果满足二叉搜索树特性，获取左子树的最左节点，右子树的最右节点
        if (flag) {
            TreeNode leftNode = root;
            while (leftNode.left != null || leftNode.right != null) {

                if (leftNode.left != null) {
                    leftNode = leftNode.left;
                } else {
                    leftNode = leftNode.right;
                }
            }
            TreeNode rightNode = root;
            while (rightNode.left != null || rightNode.right != null) {

                if (rightNode.right != null) {
                    rightNode = rightNode.right;
                } else {
                    rightNode = rightNode.left;
                }
            }

            // 1 表示符合二叉搜索树
            // leftNode 左侧地面呈现的伞坠数字值
            // rightNode 右侧地面呈现的伞坠数字值
            StringBuilder builder = new StringBuilder();
            builder.append(1).append(" ")
                    .append(leftNode.value == root.value ? 0 : leftNode.value).append(" ")
                    .append(rightNode.value == root.value ? 0 : rightNode.value);
            System.out.println(builder);
        } else {
            // 不符合二叉搜索树时直接输出0
            System.out.println("0 0 0");
        }
        return;
    }

    // 定义一棵树
    static class TreeNode {

        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }
}
