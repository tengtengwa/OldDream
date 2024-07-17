package OD2023;

import java.util.*;

public class No53_OptionalCourse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] course1 = sc.nextLine().split(";");
        String[] course2 = sc.nextLine().split(";");
        getResult(course1, course2);
    }

    public static void getResult(String[] course1, String[] course2) {
        //学号->学生
        Map<String, Student> map = new HashMap<>();
        parseStudentInfo(map, 1, course1);
        parseStudentInfo(map, 2, course2);

        //过滤出所有双修的学生
        Student[] stus = map.values().stream()
                .filter(stu -> stu.score1 != -1 && stu.score2 != -1)
                .toArray(Student[]::new);
        if (stus.length == 0) {
            System.out.println("NULL");
            return;
        }
        Map<String, List<Student>> class2StuMap = new HashMap<>();
        for (Student stu : stus) {
            class2StuMap.putIfAbsent(stu.cid, new ArrayList<>());
            class2StuMap.get(stu.cid).add(stu);
        }
        class2StuMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(e -> {
                    System.out.println(e.getKey()); //先打班级
                    List<Student> list = e.getValue();
                    //再打排序后每个班的学生
                    StringJoiner sj = new StringJoiner(";");
                    list.stream().sorted((s1, s2) -> {
                        return s1.getSumScore() != s2.getSumScore() ?
                                s2.getSumScore() - s1.getSumScore() :
                                s1.pid.compareTo(s2.pid);
                    }).forEach(stu -> {
                        sj.add(stu.pid);
                    });
                    System.out.println(sj);
                });
    }

    private static void parseStudentInfo(Map<String, Student> map, int wCourse, String[] course) {
        Arrays.stream(course).forEach(a -> {
            String[] info = a.split(",");
            String pid = info[0];
            int score = Integer.parseInt(info[1]);
            if (!map.containsKey(pid)) {
                Student stu = new Student(pid, pid.substring(0, 5));
                if (wCourse == 1) {
                    stu.score1 = score;
                } else {
                    stu.score2 = score;
                }
                map.put(pid, stu);
            } else {
                if (wCourse == 1) {
                    map.get(pid).score1 = score;
                } else {
                    map.get(pid).score2 = score;
                }
            }

        });
    }

    private static class Student {
        String pid; //学号
        String cid; //班号
        int score1 = -1;
        int score2 = -1;

        public Student(String pid, String cid) {
            this.pid = pid;
            this.cid = cid;
        }

        public int getSumScore() {
            return score1 + score2;
        }
    }
}