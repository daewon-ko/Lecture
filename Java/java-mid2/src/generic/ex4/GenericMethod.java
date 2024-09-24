package generic.ex4;

public class GenericMethod {

    public static Object objMethod(Object object) {
        System.out.println("object = " + object);
        return object;
    }

    public static <T> T getGenericMethod(T t) {
        System.out.println("generic print: " + t);
        return t;
    }
    public static <T extends  Number> T numberMethod(T t) {
        System.out.println("bound print: " + t);
        return t;
    }

}
