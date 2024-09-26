package generic.test.ex3.unit;

public class Shuttle<T extends BioUnit> {
    private T type;

    public void  in(T t) {
        this.type =t;
    }

    public T out() {
        return type;
    }

    public void showInfo() {
        System.out.println("이름: "+type.getName()+", HP: "+type.getHp());

    }
}
