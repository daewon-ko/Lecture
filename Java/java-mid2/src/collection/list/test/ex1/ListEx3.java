package collection.list.test.ex1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ListEx3 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        System.out.println("n개의 정수를 입력하세요 (종료 0)");
        Scanner sc = new Scanner(System.in);

        int sum = 0;
        double average = 0;
        int cnt = 0;
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            sum+=n;
            cnt++;
        }

        System.out.println("입력한 정수의 합계: " + sum);
        System.out.println("입력한 정수의 평균: " + (double) (sum / cnt));

    }
}
