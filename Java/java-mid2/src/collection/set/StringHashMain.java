package collection.set;

public class StringHashMain {
    static final int CAPACITY = 10;

    public static void main(String[] args) {
        // char
        char charA = 'A';
        char charB = 'B';
        System.out.println("charA = " + (int) charA);
        System.out.println("charB = " + (int) charB);


        // hashCode
        System.out.println("hashCode('A') = " + hashCode("A"));
        System.out.println("hashCode('B') = " + hashCode("B"));
        System.out.println("hashCode('AB') = " + hashCode("AB"));

        //hashIndex
        int hashCode = hashCode("A");
        int hashIndex = hashIndex(hashCode);
        System.out.println("hashIndex (A) = " + hashIndex(hashCode("A")));


    }

    static int hashIndex(int value) {
        return value % CAPACITY;
    }

    static int hashCode(String str) {
        char[] charArray = str.toCharArray();
        int sum = 0;
        for (char c : charArray) {
            sum += (int) c;
        }
        return sum;

    }
}
