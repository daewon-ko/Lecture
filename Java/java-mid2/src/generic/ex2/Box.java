package generic.ex2;

public class Box <T>{
    private T Value;

    public T getValue() {
        return Value;
    }

    public void setValue(T value) {
        Value = value;
    }
}
