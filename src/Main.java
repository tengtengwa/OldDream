import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = Arrays.stream(sc.nextLine().split(":"))
                .reduce((a, b) -> a + b)
                .orElse("");
        int[] res = getResult(str);
        System.out.println("" + res[0] + res[1] + ":" + res[2] + res[3]);
    }

    private static int[] getResult(String str) {
        HashSet<Character> set = new HashSet<>();
        int[] arr = new int[4];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            arr[i] = ch - '0';
            set.add(ch);
        }
        int[] orderedArr = set.stream().sorted()
                .mapToInt(a -> a - '0')
                .toArray();
        for (int i = 3; i >= 0; i--) {
            int curInt = arr[i];
            int nextInt = -1;
            for (int k : orderedArr) {
                if (k > curInt) {
                    nextInt = k;
                    break;
                }
            }
            if (nextInt == -1
                    || i == 2 && nextInt > 5
                    || i == 1 && arr[0] == 2 && nextInt > 3
                    || i == 0 && nextInt > 2) {
                arr[i] = orderedArr[0];
            } else {
                arr[i] = nextInt;
                return arr;
            }
        }
        return arr;
    }
}