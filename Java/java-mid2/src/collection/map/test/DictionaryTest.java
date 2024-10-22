package collection.map.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DictionaryTest {
    public static void main(String[] args) {
        Map<String, String> dictionary = new HashMap<>();

        System.out.println("===단어 입력 단계====");
        while (true) {
            System.out.printf("영어 단어를 입력하세요.(종료는 'q')");
            Scanner sc = new Scanner(System.in);
            String englishWord = sc.nextLine();
            if (englishWord.equals("q")) {
                break;
            }
            System.out.println("한글 뜻을 입력하세요:");
            String koreanWord = sc.nextLine();
            dictionary.put(englishWord, koreanWord);
        }

        System.out.println("===단어 검색 단계 ===");
        while (true) {
            System.out.println("찾을 영어 단어를 입력하세요 (종료는 'q)");
            Scanner sc = new Scanner(System.in);
            String wantedEnglishWord = sc.nextLine();
            if (wantedEnglishWord.equals("q")) {
                break;
            }

            if (dictionary.containsKey((wantedEnglishWord))) {
                String findKoreanWord = dictionary.get(wantedEnglishWord);
                System.out.println(wantedEnglishWord + "의 뜻:" + findKoreanWord);
            } else {
                System.out.println(wantedEnglishWord + "은 사전에 없는 단어입니다.");

            }


        }
    }
}
