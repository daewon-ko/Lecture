package collection.set;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class MyHashSetV2 {
    static final int DEFAULT_INITIAL_CAPACITY = 16;

    private LinkedList<Object>[] buckets;

    private int size = 0;
    private int capacity = DEFAULT_INITIAL_CAPACITY;

    public MyHashSetV2() {
        initBuckets();
    }

    public MyHashSetV2(int capacity) {
        this.capacity = capacity;
        initBuckets();
    }

    private void initBuckets() {
        this.buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            this.buckets[i] = new LinkedList<>();
        }
    }

    public boolean add(Object value) {
        int hashIndex = hashIndex(value);
        LinkedList<Object> bucket = buckets[hashIndex];
        if (bucket.contains(value)) {
            return false;
        }
        bucket.add(value);
        size++;
        return true;
    }

    public boolean remove(Object value) {
        int hashIndex = hashIndex(value);
        LinkedList<Object> bucket = buckets[hashIndex];
        boolean result = bucket.remove(value);// 숫자를 넣으면 Index 위치에 값을 삭제하므로, 객체형으로 넘겨야 해당 값이 있는 것을 삭제
        if (result) {
            size--;
            return true;
        } else return false;
    }

    public int size() {
        return size;
    }

    public boolean contains(Object searchValue) {
        int hashIndex = hashIndex(searchValue);
        LinkedList<Object> bucket = buckets[hashIndex];
        return bucket.contains(searchValue);
    }

    private int hashIndex(Object value) {
        // hashCode값이 음수가 나올 수도 있으므로 방지해주기 위해 절댓값 이용
        return Math.abs(value.hashCode()) % capacity;
    }

    @Override
    public String toString() {
        return "MyHashSetV2{" +
                "buckets=" + Arrays.toString(buckets) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }
}
