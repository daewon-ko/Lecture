package collection.set.test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class UniqueNamesTest1 {
    public static void main(String[] args) {

        Integer[] inputArr = {30, 20, 20, 10, 10};
        Set<Integer> set = new HashSet<>();
        for (Integer i : inputArr) {
            set.add(i);
        }

        for (Integer i : set) {
            System.out.println(i);
        }



    }
}
