package collection.set.test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UniqueNamesTest2 {
    public static void main(String[] args) {

        Integer[] inputArr = {30, 20, 20, 10, 10};
        List<Integer> list = List.of(inputArr);
        Set<Integer> set = new LinkedHashSet<>(list);

        for (Integer i : set) {
            System.out.println(i);
        }



    }
}
