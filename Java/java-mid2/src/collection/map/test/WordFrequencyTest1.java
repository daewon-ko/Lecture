package collection.map.test;

import java.util.HashMap;
import java.util.Map;

public class WordFrequencyTest1 {
    public static void main(String[] args) {
        String text = "orange banana apple apple banana apple";

        Map<String, Integer> map = new HashMap<>();

        String[] words = text.split(" ");
//        for (String word : words) {
//            Integer cnt = map.get(word);
//            if (cnt == null) {
//                map.put(word, 1);
//            }
//
//            cnt++;
//            map.put(word, cnt);
//
//        }


        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }




    }
}
