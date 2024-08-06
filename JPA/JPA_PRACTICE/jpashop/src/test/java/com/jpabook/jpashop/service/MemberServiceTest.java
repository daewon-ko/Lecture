package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepositoryOld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepositoryOld memberRepositoryOld;

    @DisplayName("회원가입")
    @Test
    @Rollback(value = true)
    void test() {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepositoryOld.findOne(savedId));

    }

    @DisplayName("")
    @Test()
    void 중복_회원_예외() {
        //given
        Member member = new Member();
        member.setName("kim1");
        Member member2 = new Member();
        member2.setName("kim1");

        //when
        memberService.join(member);


        assertThatThrownBy(() -> memberService.join(member2)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 회원입니다.");


    }


}