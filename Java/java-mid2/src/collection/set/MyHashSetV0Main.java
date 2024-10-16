package collection.set;

public class MyHashSetV0Main {
    public static void main(String[] args) {
        MyHashSet0 set = new MyHashSet0();
        set.add(1); // O(1)
        set.add(2); // O(N)
        set.add(3); // O(N)
        set.add(4);// O(N)
        set.add(5);// O(N)
        System.out.println(set);

        boolean ressult = set.add(4);
        System.out.println("중복 데이터 저장 결과" + ressult);
        System.out.println(set);

        System.out.println("set.contains(3) = " + set.contains(3));// O(N)
        System.out.println("set.contains(4) = " + set.contains(4));
    }
}
