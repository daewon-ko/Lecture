package generic.ex5;

import generic.animal.Animal;
import generic.animal.Cat;
import generic.animal.Dog;

public class WildCardMain2 {
    public static void main(String[] args) {
        Box<Object> objBox = new Box<>();
        Box<Animal> animalBox = new Box<>();
        Box<Dog> dogBox = new Box<>();
        Box<Cat> catBox = new Box<>();

        //Animal 포함 상위 타입 전달 가능
        writeBox(objBox);
        writeBox(animalBox);

        Animal animal = animalBox.getValue();
        System.out.println("animal = " + animal);
    }

    static void writeBox(Box<? super Animal> box) {
        box.setValue(new Dog("멍멍이", 100));

    }

}
