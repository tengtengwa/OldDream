package OD2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class No66_CountWildGoose {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(getResult(str));
    }

    public static int getResult(String str) {
        List<Integer> qIdx = new ArrayList<>();
        int u = 0, a = 0, c = 0;
        //记录每个完整的"quack"的索引范围[q,k]
        List<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            switch (ch) {
                case 'q':
                    qIdx.add(i);
                    break;
                case 'u':
                    if (u + 1 <= qIdx.size()) {
                        u++;
                    }
                    break;
                case 'a':
                    if (a + 1 <= u) {
                        a++;
                    }
                    break;
                case 'c':
                    if (c + 1 <= a) {
                        c++;
                    }
                    break;
                case 'k':
                    if (c > 0) {
                        list.add(new Integer[]{qIdx.remove(0), i});
                        u--;
                        a--;
                        c--;
                    }
                    break;
                default:
                    return -1;
            }
        }
        if (list.size() == 0) {
            return -1;
        }
        int ans = 1;
        for (int i = 0; i < list.size(); i++) {
            int count = 1;
            int end = list.get(i)[1];
            for (int j = i + 1; j < list.size(); j++) {
                int nStart = list.get(j)[0];
                if (nStart < end) {
                    count++;
                } else {    //list中每个范围起始索引是升序排序，所以可以跳过后面不重复的范围
                    break;
                }
            }
            ans = Math.max(count, ans);
        }
        return ans;
    }
}

