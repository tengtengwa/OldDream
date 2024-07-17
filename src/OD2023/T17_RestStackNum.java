package OD2023;

import java.util.*;

public class T17_RestStackNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        getRes(arr);
    }

    private static void getRes(int[] arr) {
        Deque<Integer> queue = new LinkedList<>();
        queue.addFirst(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            checkStack(arr[i], queue);
        }
        StringJoiner sj = new StringJoiner(" ");
        while (queue.size() > 0) {
            sj.add(String.valueOf(queue.removeFirst()));
        }
        System.out.println(sj);
    }

    private static void checkStack(int num, Deque<Integer> queue) {
        int count = 0;
        int target = num;
        for (int curNum : queue) {
            target -= curNum;
            count++;
            if (target == 0) {
                while (count > 0) {
                    queue.pollFirst();
                    count--;
                }
                checkStack(num << 1, queue);
                return;
            } else if (target < 0) break;
        }
        queue.addFirst(num);
    }
}
