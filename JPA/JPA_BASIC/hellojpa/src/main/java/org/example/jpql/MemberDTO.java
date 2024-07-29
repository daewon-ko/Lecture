package org.example.jpql;

public class MemberDTO {

    private int age;


    private String username;

    public MemberDTO( String username, int age) {

        this.age = age;
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
