package study.data_jpa.repository;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.data_jpa.entity.Member;
import study.data_jpa.entity.Team;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private TeamJpaRepository teamJpaRepository;

    @Autowired
    EntityManager entityManager;


    @DisplayName("")
    @Test
    void testMember() {
        //given
        Member member = new Member("memberA");


        //when
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedMember.getId()).get();

        //then
        assertThat(savedMember.getId()).isEqualTo(member.getId());
        assertThat(savedMember.getUsername()).isEqualTo(findMember.getUsername());
        assertThat(savedMember).isEqualTo(findMember);

    }

    @DisplayName("")
    @Test
    void test() {
        //given
        Member member1 = new Member("memberA");
        Member member2 = new Member("memberB");

        Team team1 = new Team("teamA");

        member1.changeTeam(team1);
        member2.changeTeam(team1);

        memberRepository.save(member1);
        memberRepository.save(member2);
        teamJpaRepository.save(team1);


        entityManager.flush();
        entityManager.clear();

        //when

        List<MemberDto> memberDto = memberRepository.findMemberDto();



        //then
//        assertThat(memberDto.size()).isEqualTo(2);


    }


}