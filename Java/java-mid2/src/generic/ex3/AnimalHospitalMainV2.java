package generic.ex3;

import generic.animal.Cat;
import generic.animal.Dog;

public class AnimalHospitalMainV2 {
    public static void main(String[] args) {
        AnimalHospitalV2<Dog> dogHospital = new AnimalHospitalV2<Dog>();
        AnimalHospitalV2<Cat> catHospital = new AnimalHospitalV2<Cat>();
        AnimalHospitalV2<Integer> integer = new AnimalHospitalV2<>(); // 기타 타입도 다 들어올 수있음
    }
}
