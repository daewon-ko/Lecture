package collection.array;

public class MyArrayListV3BadMain {
    public static void main(String[] args) {
        MyArrayListV3 numberList = new MyArrayListV3();

        //숫자만입력하기를 기대
        numberList.add(1);
        numberList.add(2);
        numberList.add("문자3"); // 문자 입력
        System.out.println("numberList = " + numberList);

        Integer number1 = (Integer) numberList.get(0);
        Integer number2 = (Integer) numberList.get(1);
//        Integer number3 = (Integer) numberList.get(2); // 예외 발생

    }
}
