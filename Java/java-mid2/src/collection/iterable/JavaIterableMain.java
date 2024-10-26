package collection.iterable;

import java.util.*;

public class JavaIterableMain {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        printAll(list.iterator());
        printAll(set.iterator());

        forEach(list);
        forEach(set);


    }
    private static void forEach(Iterable<Integer> iterable) {
        System.out.println("iterable.getClass() = " + iterable.getClass());

        for (Integer i : iterable) {
            System.out.println(i);
        }
    }

    private static void printAll(Iterator<Integer> iterator) {
        System.out.println("iterator = " + iterator.getClass());
        while (iterator.hasNext()) {
            System.out.println("iterator.next() = " + iterator.next());
        }
    }
}
