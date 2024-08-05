package OD2024;

import java.util.*;

public class B_18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int capacity = Integer.parseInt(sc.nextLine());
        int n = Integer.parseInt(sc.nextLine());
        LFUCache lfuCache = new LFUCache(capacity);
        //读取后续n个操作
        for (int i = 1; i <= n; i++) {
            String[] str = sc.nextLine().split(" ");
            //这里通过数组长度判断put/get操作，也可以通过第0个索引的字符串判断
            if (str.length == 3) {
                lfuCache.put(str[1], Integer.parseInt(str[2]), i);
            } else if (str.length == 2) {
                lfuCache.get(str[1], i);
            }
        }
        lfuCache.printCaches();
    }

    private static class LFUCache {
        private static int capacity;

        //缓存中剩余的空间
        private static int freeCap;

        private static final HashMap<String, Cache> name2File = new HashMap<>();

        //按访问次数从少到多，访问时间从老到新（从小到大）对文件排序
        private static final PriorityQueue<Cache> pq = new PriorityQueue<>((a, b) ->
                a.visitCount == b.visitCount ? (a.visitTime - b.visitTime)
                        : a.visitCount - b.visitCount);

        public LFUCache(int capacity) {
            LFUCache.capacity = capacity;
            freeCap = capacity;
        }

        public void put(String fileName, int fileSize, int visitTime) {
            if (name2File.containsKey(fileName) || fileSize > capacity) {
                return;
            }
            Cache cache = new Cache(fileName, fileSize, visitTime);
            if (freeCap < cache.size) {
                //LFUCache剩余空间不够时，需要将已有缓存移除，直到满足当前新缓存cache
                while (freeCap < cache.size) {
                    if (pq.size() == 0) {
                        return;
                    }
                    //移除时需要将map和pq中的同一个对象都移除
                    Cache removed = pq.poll();
                    name2File.remove(removed.name);
                    freeCap += removed.size;
                }
            }
            //新加入时也要分别存储
            name2File.put(fileName, cache);
            pq.offer(cache);
            freeCap -= fileSize;
        }

        public void get(String fileName, int visitTime) {
            Cache cache = name2File.get(fileName);
            if (cache == null) {
                return;
            }
            //更新cache访问次数和时间
            cache.visitCount++;
            cache.visitTime = visitTime;
            //这个cache在map和优先队列中是同一个对象，修改了上面两个属性后优先队列并未重新排序
            //因此这里通过将cache重新加入队列的方式进行重新排序
            pq.remove(cache);
            pq.add(cache);
        }

        public void printCaches() {
            Collection<Cache> values = name2File.values();
            //没有缓存时，打印NONE
            if (values.size() == 0) {
                System.out.println("NONE");
            }
            StringJoiner sj = new StringJoiner(",");
            //剩余缓存字典序排序输出
            values.stream().sorted((a, b) -> a.name.compareTo(b.name))
                    .forEach(a -> sj.add(a.name));
            System.out.println(sj);
        }
    }

    private static class Cache {
        String name;
        int size;
        int visitCount;
        //访问时间，值越大访问时间越新
        int visitTime;

        public Cache(String name, int size, int visitTime) {
            this.name = name;
            this.size = size;
            this.visitCount = 0;
            this.visitTime = visitTime;
        }
    }
}
