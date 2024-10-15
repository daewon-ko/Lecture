package collection.list.test.ex1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListEx1 {
    public static void main(String[] args) {
        List<Integer> students = Arrays.asList(90, 80, 70, 60, 50);


        int total = students.stream().mapToInt(i -> i).sum();
        double average = (double) total / students.size();


        System.out.println("점수 총합: " + total);
        System.out.println("점수 평균: " + average);

    }
}
