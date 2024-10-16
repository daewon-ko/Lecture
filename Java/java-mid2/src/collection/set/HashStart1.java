package collection.set;

import java.util.Arrays;

public class HashStart1 {
    public static void main(String[] args) {
        Integer[] inputArray = new Integer[4];
        inputArray[0] = 1;
        inputArray[1] = 2;
        inputArray[2] = 5;
        inputArray[3] = 6;

        System.out.println("Arrays.toString(inputArray) = " + Arrays.toString(inputArray));

        int searchValue = 6;

        // 4번 반복 - > O(N)
        for (Integer i : inputArray) {
            if (i == searchValue) {
                System.out.println(i);
            }
        }
    }
}
