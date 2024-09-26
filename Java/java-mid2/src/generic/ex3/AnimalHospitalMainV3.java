package generic.ex3;

import generic.animal.Cat;
import generic.animal.Dog;

public class AnimalHospitalMainV3 {
    public static void main(String[] args) {
        AnimalHospitalV3<Dog> dogHospital = new AnimalHospitalV3<>();
        AnimalHospitalV3<Cat> catHospital = new AnimalHospitalV3<>();
        Dog dog = new Dog("멍멍이1", 100);
        Cat cat = new Cat("냐옹이1", 300);
        //개 병원
         dogHospital.setAnimal(dog);
         dogHospital.checkUP();
        //고양이 병원
         catHospital.setAnimal(cat);
         catHospital.checkUP();
        // 문제1 해결: 개 병원에 고양이 전달
//         dogHospital.set(cat); // 다른 타입 입력: 컴파일 오류
        //문제2해결:개 타입 반환
        dogHospital.setAnimal(dog);
        Dog biggerDog = dogHospital.bigger(new Dog("멍멍이2", 200));
        System.out.println("biggerDog = " + biggerDog);
    }
}
