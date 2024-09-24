package generic.ex1;

public class BoxMain3 {
    public static void main(String[] args) {
        GenericBox<Integer> integerBox = new GenericBox<>();
        integerBox.setValue(10);
//        integerBox.setValue("문자100"); // 컴파일 오류
        Integer integer = integerBox.getValue();
        System.out.println("integer = " + integer);

        GenericBox<String> stringBox = new GenericBox<>();
        stringBox.setValue("문자열100");
        String str = stringBox.getValue();
        System.out.println("str = " + str);

        GenericBox<Double> doubleBox = new GenericBox<>();
        doubleBox.setValue(10.0);
        Double doubleNumber = doubleBox.getValue();
        System.out.println("doubleNumber = " + doubleNumber);
    }


}
