package generic.ex1;

public class GenericBox <T>{
    private T Value;

    public T getValue() {
        return Value;
    }

    public void setValue(T value) {
        Value = value;
    }
}
