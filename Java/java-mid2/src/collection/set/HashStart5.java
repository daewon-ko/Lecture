package collection.set;

import java.util.Arrays;
import java.util.LinkedList;

public class HashStart5 {
    static final int CAPACITY = 10;

    public static void main(String[] args) {
        // {1,2,5,8,14,99}
        // 링크드리스트 '배열'을 생성
        // 해쉬충돌이 가끔일어나는 것을 가정하므로 ArrayList가 아닌 LinkedList로 구성
        LinkedList<Integer>[] buckets = new LinkedList[CAPACITY];
        System.out.println("Arrays.toString(buckets) = " + Arrays.toString(buckets));

        for (int i = 0; i < CAPACITY; i++) {
            // 배열 내부에 링크드리스트 자체를 생성
            buckets[i] = new LinkedList<>() ;
        }
        System.out.println("Arrays.toString(buckets) = " + Arrays.toString(buckets));

        add(buckets, 1);
        add(buckets, 2);
        add(buckets, 5);
        add(buckets, 8);
        add(buckets, 14);
        add(buckets, 99);
        add(buckets, 9); // 해시충돌
        System.out.println("Arrays.toString(buckets) = " + Arrays.toString(buckets));


        // 검색
        int searchValue = 9;
        boolean result = contains(buckets, searchValue);

        System.out.println("buckets.contains("+searchValue+") = " + result);
    }

    private static boolean contains(LinkedList<Integer> [] buckets, int searchValue) {
        int hashIndex = hashIndex(searchValue);
        LinkedList<Integer> bucket = buckets[hashIndex]; // O(1)
        return bucket.contains(searchValue); // O(N)
    }

    public static void add(LinkedList<Integer>[] buckets, int value) {
        int hashIndex = hashIndex(value);
        LinkedList<Integer> bucket = buckets[hashIndex]; // O(1)
        if (!bucket.contains(value)) { // O(N) -> 중복 체크
            bucket.add(value);
        }

    }

    public static int hashIndex(int value) {
        return value % CAPACITY;
    }

}
