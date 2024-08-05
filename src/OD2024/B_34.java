package OD2024;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class B_34 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] balls = Arrays.stream(sc.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] arr = Arrays.stream(sc.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        int i = 0, j = 0;
        while (j < arr.length) {
            if (deque.isEmpty() || (deque.peekFirst() != arr[j] && deque.peekLast() != arr[j])) {
                //已经全部放入，但还没有全部取出，此时就无法按照给定顺序全部取出
                if (i == balls.length) {
                    System.out.println("NO");
                    return;
                }
                deque.addLast(balls[i++]);
                continue;
            }
            if (deque.peekFirst() == arr[j]) {
                deque.pollFirst();
                j++;
                sb.append("L");
                continue;
            }
            if (deque.peekLast() == arr[j]) {
                deque.pollLast();
                j++;
                sb.append("R");
            }
        }
        System.out.println(sb);
    }
}
