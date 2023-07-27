package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
public class Member {
    private Long id;
    private String username;
    private int age;

    public Member(final String username, final int age) {
        this.username = username;
        this.age = age;
    }

    public Member() {
    }
}
