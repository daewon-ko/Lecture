package generic.ex3;

public class AnimalHospitalV2<T> {
    private T animal;

    public void setAnimal(T animal) {
        this.animal = animal;
    }

    public void checkUP() {
        // T타입을 메서드 정의시점에서는알수없음 따라서 Object의 기본 메서드만 제공
        animal.toString();
        animal.equals(null);


    }

    public T getBigger(T target) {
        return null;
    }
}
