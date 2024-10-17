package collection.set.test;

import java.util.HashSet;

public class RectangleTest {
    public static void main(String[] args) {
        HashSet<Rectangle> rectangles = new HashSet<>();

        rectangles.add(new Rectangle(10, 10));
        rectangles.add(new Rectangle(20, 20));
        rectangles.add(new Rectangle(20, 20));

        for (Rectangle rectangle : rectangles) {
            System.out.println("rectangle = " + rectangle);
        }
    }
}
