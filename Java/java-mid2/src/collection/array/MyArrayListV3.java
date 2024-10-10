package collection.array;

import java.util.Arrays;

public class MyArrayListV3 {
    private static final int DEFAULT_CAPACITY = 5;

    private Object [] elementData;
    private int size =0;

    public MyArrayListV3() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayListV3(int initialCapacity) {
        elementData = new Object[initialCapacity];
    }

    public int size() {
        return size;
    }

    public void add(Object object) {
        // 코드 추가
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = object;
        size++;
    }

    public void add(int index, Object e) {
        if(size == elementData.length) {
            grow();
        }
        //데이터 이동
        shiftRightFrom(index);


        elementData[index] = e;
        size++;

    }

    // 코드 추가
    public Object remove(int index) {
        Object oldValue = get(index);
        // 데이터 이동
        shiftLeftFrom(index);
        size--;
        elementData[size] = null;
        return oldValue;

    }

    // 요소의 index부터 마지막까지 왼쪽으로 밀기
    private void shiftLeftFrom(int index) {
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }

    }

    // 코드 추가, 요소의 마지막부터 인덱스까지 오른쪽으로 밀기
    private void shiftRightFrom(int index) {
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }


    }


    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity * 2;

//        Object[] newArr = new Object[newCapacity];
//        for (int i = 0; i < elementData.length; i++) {
//            newArr[i] = elementData[i];
//        }

        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public Object get(int index) {
        return elementData[index];
    }

    public Object set(int index, Object element) {
        Object oldValue = get(index);
        elementData[index] = element;
        // 이전 값 반환
        return oldValue;
    }

    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {

        return Arrays.toString(Arrays.copyOf(elementData, size)) + "size = " + size
                + ", capacity " + elementData.length;
    }


}
