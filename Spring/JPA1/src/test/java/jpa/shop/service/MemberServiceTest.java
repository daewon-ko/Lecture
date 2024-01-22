package jpa.shop.service;

import jpa.shop.domain.Member;
import jpa.shop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입")
    public void signUp() {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        assertThat(member).isEqualTo(memberRepository.findOne(savedId));
    }

    @Test()
    @DisplayName("중복된 회원가입시 예외가 발생한다.")
    public void duplicateSignUp() {

        //given
        Member member1 = new Member();
        member1.setName("Kim");

        Member member2 = new Member();
        member2.setName("Kim");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class,
                () -> {
                    memberService.join(member2);
                });
    }
}
