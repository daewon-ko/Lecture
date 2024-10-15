package collection.list.test.ex1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ListEx2 {
    public static void main(String[] args) {
        System.out.println("n개의 정수를 입력하세요. 종료 0 ");
        Scanner sc= new Scanner(System.in);

        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        while (true) {

            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            list.add(n);
        }

        for(int i=0; i<list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(",");
            }

        }


        System.out.println(sb.toString());



    }
}
