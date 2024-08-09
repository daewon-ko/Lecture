package study.data_jpa.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.data_jpa.entity.Member;
import study.data_jpa.entity.Team;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private TeamJpaRepository teamJpaRepository;


    @Autowired
    EntityManager em;


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
    void testQuery() {
        //given
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);


        memberRepository.save(member1);
        memberRepository.save(member2);


        //when

        List<Member> result = memberRepository.findUser("AAA", 10);


        //then
        assertThat(result.get(0)).isEqualTo(member1);

    }

    @DisplayName("")
    @Test
    void findUsernameList() {
        //given
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);


        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<String> usernameList = memberRepository.findUsernameList();

        //then
        assertThat(usernameList).hasSize(2);
        assertThat(usernameList).contains(member1.getUsername(), member2.getUsername());

    }

    @DisplayName("")
    @Test
    void test() {
        //given
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);

        Team team = new Team("teamA");
        teamJpaRepository.save(team);

        member1.changeTeam(team);


        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }

        //then

    }

    @DisplayName("")
    @Test
    void paging() {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));


        //when

        Page<Member> page = memberRepository.findByAge(age, pageRequest);
        Page<MemberDto> toMap = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));


        //then
        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();


        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();


    }

    @DisplayName("")
    @Test
    void bulkUpdateTest() {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member1", 19));
        memberRepository.save(new Member("member1", 20));
        memberRepository.save(new Member("member1", 21));
        memberRepository.save(new Member("member1", 40));

        //when
        int result = memberRepository.bulkAgePlus(20);

        //then
        assertThat(result).isEqualTo(3);

    }

    @DisplayName("")
    @Test
    void findMemberLazy() {

        //given
        Team teamA = new Team("teaA");
        Team teamB = new Team("teaB");

        teamJpaRepository.save(teamA);
        teamJpaRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member1", 10, teamB);

        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        //when
        List<Member> members = memberRepository.findEntityGraphByUsername("member1");
        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
        }
        //then

    }


    @DisplayName("")
    @Test
    void queryHint() {
        //given
        Member member1 = memberRepository.save(new Member("member1", 10));
        em.flush();
        em.clear();
        //when
        Member findMember = memberRepository.findReadOnlyByUsername("member1");
        findMember.setUsername("member2");
        em.flush();

        //then

    }

    @DisplayName("")
    @Test

    void lock(){
    //given
        Member member1 = memberRepository.save(new Member("member1", 10));
        em.flush();
        em.clear();

    //when
        List<Member> result = memberRepository.findLockByUsername("member1");

        //then

    }

}