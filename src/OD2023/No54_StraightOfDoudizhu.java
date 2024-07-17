package OD2023;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 自己的双指针解法，暂未AC
 */
public class No54_StraightOfDoudizhu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] cards = sc.nextLine().split(" ");
        getResult(cards);
    }

    public static void getResult(String[] cards) {
        HashSet<String> set = new HashSet<>(Arrays.asList(cards));
        List<String> list = set.stream()
                .sorted((a, b) -> mapToNum(a) - mapToNum(b))
                .collect(Collectors.toList());
        int l = 0, r = 0;
        String last = list.get(0);
        boolean hasRes = false;
        while (true) {
            r++;
            if (r < list.size()) {
                if (mapToNum(list.get(r)) - mapToNum(last) != 1) {
                    if (r - l >= 5) {
                        printRes(list, l, r);
                        hasRes = true;
                    }
                    l = r;
                }
            } else {
                if (r - l >= 5) {
                    printRes(list, l, r);
                    hasRes = true;
                }
                break;
            }
            last = list.get(r);
        }
        if (!hasRes) {
            System.out.println("No");
        }
    }

    private static void printRes(List<String> list, int l, int r) {
        StringJoiner sj = new StringJoiner(" ");
        for (int i = l; i < r; i++) {
            sj.add(list.get(i));
        }
        System.out.println(sj);
    }

    private static int mapToNum(String card) {
        switch (card) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            case "2":
                return 66;
            default:
                return Integer.parseInt(card);
        }
    }
}