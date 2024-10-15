package collection.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JavaListPerformanceTest {
    public static void main(String[] args) {
        int size = 50_000;
        System.out.println("ArrayList 추가");
        addFirst(new ArrayList<>(), size);
        addMid(new ArrayList<>(), size);  // 찾는데 O(1), 데이터 밀기 O(N)

        List<Integer> arrayList = new ArrayList<>();
        addLast(arrayList, size); // 찾는데 O(1), 데이터 추가 O(1)

        int loop = 10000;
        System.out.println("ArrayList 조회");
        getIndex(arrayList, 10000, 0);
        getIndex(arrayList, 10000, size/2);
        getIndex(arrayList, 10000, size - 1);

        System.out.println("ArrayList 검색");
        search(arrayList, 10000, 0);
        search(arrayList, 10000, size/2);
        search(arrayList, 10000, size-1);

        System.out.println("LinkedList 추가");
        addFirst(new LinkedList(), size);
        addMid(new LinkedList(), size); // 찾는데 O(1), 데이터 추가O(1)

        List<Integer> linkedList = new LinkedList<>();
        addLast( linkedList , size); // // 찾는데 O(N), 데이터 밀기 O(1)

        System.out.println("LinkedList 조회");
        getIndex(linkedList, 10000, 0);
        getIndex(linkedList, 10000, size / 2);
        getIndex(linkedList, 10000, size - 1);

        System.out.println("LinkedList 검색");
        search(linkedList, 10000, 0);
        search(linkedList, 10000, size/2);
        search(linkedList, 10000, size-1);


    }



    private static void search(List<Integer> list, int loop, int findValue) {
        long startTime = System.currentTimeMillis();
        for(int i=0; i<loop; i++) {
            list.indexOf(findValue);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("findValue: " + findValue + ", 반복: " + loop + ", 계산시간 : " + (endTime - startTime) + "ms");
    }

    private static void getIndex(List<Integer> list, int loop, int index) {
        long startTime = System.currentTimeMillis();
        for(int i=0; i<loop; i++) {
            list.get(index);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("index:" + index + ", 반복: " + loop + ", 계산시간 : " + (endTime - startTime) + "ms");
    }

    private static void addFirst(List list, int size) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            list.add(0, i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("앞에 추가 - 크기 : " + size + ", 계산 시간: " + (endTime - startTime) + "ms");
    }

    private static void addMid(List list, int size) {

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            list.add(i / 2, i);   // 가운데 위치쯤 추가
        }
        long endTime = System.currentTimeMillis();
        System.out.println("가운데 추가 - 크기 : " + size + ", 계산 시간: " + (endTime - startTime) + "ms");
    }

    private static void addLast(List list, int size) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("뒤에 추가 - 크기 : " + size + ", 계산 시간: " + (endTime - startTime) + "ms");
    }
}
