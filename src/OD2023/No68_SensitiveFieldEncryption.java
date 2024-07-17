package OD2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 未AC
 */
public class No68_SensitiveFieldEncryption {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(k, sc.nextLine()));
    }

    public static String getResult(int k, String str) {
        List<String> list = new ArrayList<>();
        StringBuilder stack = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '_' && (stack.length() == 0 || stack.charAt(0) != '\"')) {
                list.add(stack.toString());
                stack = new StringBuilder();
            } else if (ch == '\"' && stack.length() != 0) {
                stack.append(ch);
                list.add(stack.toString());
                stack = new StringBuilder();
            } else {
                stack.append(ch);
            }
        }
        if (stack.length() != 0) {
            list.add(stack.toString());
        }
        list = list.stream().filter(s -> !"".equals(s)).collect(Collectors.toList());
        if (k > list.size()) {
            return "ERROR";
        }
        list.set(k, "******");
        StringJoiner sj = new StringJoiner("_");
        for (String s : list) {
            sj.add(s);
        }
        return sj.toString();
    }
}