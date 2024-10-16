package collection.set.member;

import collection.set.MyHashSetV2;

public class HashAndEqualsMain2 {
    public static void main(String[] args) {
        MyHashSetV2 set = new MyHashSetV2(10);
        MemberOnlyHash m1 = new MemberOnlyHash("A");
        MemberOnlyHash m2 = new MemberOnlyHash("A");

        System.out.println("m1.hashCode() = " + m1.hashCode());
        System.out.println("m2.hashCode() = " + m2.hashCode());
        System.out.println("m1.equals(m2) = " + m1.equals(m2));

        // 참조값 출력
        System.out.println("System.identityHashCode(m1) = " + System.identityHashCode(m1));
        System.out.println("System.identityHashCode(m2) = " + System.identityHashCode(m2));

        // 중복 등록
        // equals 메서드를 오버라이드 하지 않았기 때문에
        // 객체의 참조값으로 비교를 하므로 중복 등록이 되어버린다.
        set.add(m1);
        set.add(m2);

        System.out.println(set);

        // 검색 실패
        MemberOnlyHash searchValue = new MemberOnlyHash("A");
        boolean result = set.contains(searchValue);
        System.out.println("searchValue.hashCode() = " + searchValue.hashCode());
        System.out.println(result);
    }
}
