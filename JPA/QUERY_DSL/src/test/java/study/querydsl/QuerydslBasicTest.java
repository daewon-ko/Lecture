package study.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;
import study.querydsl.entity.Team;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.*;
import static org.assertj.core.api.Assertions.*;
import static study.querydsl.entity.QMember.*;
import static study.querydsl.entity.QTeam.team;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {
    @Autowired
    private EntityManager em;
    private JPAQueryFactory queryFactory;
    @PersistenceUnit
    EntityManagerFactory emf;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

    }

    @DisplayName("")
    @Test
    void startJpql() {
        //given
        Member findByJpql = em.createQuery("select m from Member m where m.username =:username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        //when

        //then
        assertThat(findByJpql.getUsername()).isEqualTo("member1");

    }

    @DisplayName("")
    @Test
    void startQueryDsl() {
        //given
        QMember m = new QMember("m");

        //when
        Member findMember = queryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("member1"))
                .fetchOne();

        //then
        assertThat(findMember.getUsername()).isEqualTo("member1");

    }


    @DisplayName("")
    @Test
    void startQuerydsl3() {

        //given
        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        //when, then
        assertThat(findMember.getUsername()).isEqualTo("member1");


    }


    @DisplayName("")
    @Test
    void search() {

        //given
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1")
                        .and(member.age.eq(10)))
                .fetchOne();

        //when

        //then

    }

    @DisplayName("")
    @Test
    void searchAndParam() {

        //given
        List<Member> result1 = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1")
                        , member.age.eq(10)
                ).fetch();


        //when

        //then

    }

    //정럴
    @DisplayName("")
    @Test
    void sort() {

        //given
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();


        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);
        //when, then
        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();

    }

    // 페이징
    @DisplayName("")
    @Test
    void paging1() {
        //given
        List<Member> result = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();
        for (Member member1 : result) {
            System.out.println("member1 = " + member1);
        }

        //when

        //then

    }

    @DisplayName("")
    @Test
    void paging2() {
        /**
         * getTotal
         */

        //given
        List<Member> members = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();
        Long count = queryFactory
                .select(member.count())
                .from(member)
                .fetchOne();
        //when

        System.out.println("count = " + count);

        //then

    }


    //집합
    @DisplayName("")
    @Test
    void aggregation() throws Exception {
        //given
        List<Tuple> result = queryFactory
                .select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()

                ).from(member)
                .fetch();

        //when
        Tuple tuple = result.get(0);

        //then
        assertThat(tuple.get(member.count())).isEqualTo(4);
        assertThat(tuple.get(member.age.sum())).isEqualTo(100);
        assertThat(tuple.get(member.age.avg())).isEqualTo(25);
        assertThat(tuple.get(member.age.max())).isEqualTo(40);
        assertThat(tuple.get(member.age.min())).isEqualTo(10);

    }

    @DisplayName("")
    @Test
    void group() {
        //given
        List<Tuple> result = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();
        //when
        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);
        //then

        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15);
        assertThat(teamB.get(team.name)).isEqualTo("teamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35);


    }

    @DisplayName("")
    @Test
    void join() {
        //given
        QMember member = QMember.member;
        QTeam team = QTeam.team;

        //when
        List<Member> members = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(members).extracting("username")
                .containsExactly("member1", "member2");

        //then

    }

    @DisplayName("")
    @Test
    void join_on_filtering() {

        //given


        //when
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team)
                .on(team.name.eq("teamA"))
                .fetch();

        //then

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }

    }

    @DisplayName("")
    @Test
    void join_on_no_relation() {

        //given
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));

        //when
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team)
                .on(team.name.eq(member.username))
                .fetch();
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }

        //then

    }

    @DisplayName("")
    @Test
    void fetch_join_no() {
        em.flush();
        em.clear();
        //given
        Member member1 = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        boolean isLoaded = emf.getPersistenceUnitUtil().isLoaded(member1.getTeam());
        assertThat(isLoaded).isFalse();
        //when

        //then

    }

    @DisplayName("")
    @Test
    void fetchJoinUse() {
        em.flush();
        em.clear();
        //given
        Member findMember = queryFactory
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();

        boolean isLoaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(isLoaded).isTrue();


        //when

        //then

    }

    @DisplayName("")
    @Test
    void subQuery
            () {

        //given
        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        select(memberSub.age.max())
                                .from(memberSub)
                )).fetch();

        //when, then
        assertThat(result).extracting("age")
                .containsExactly(40);


    }

    @DisplayName("")
    @Test
    void
    subQuery_goe() {
        //given
        QMember memberSub = new QMember("memberSub");

        //when, then
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.goe(
                        select(memberSub.age.avg())
                                .from(memberSub)
                )).fetch();

        assertThat(result).extracting("age")
                .containsExactly(30, 40);

    }

    @DisplayName("")
    @Test
    void subQuery_In() {
        //given
        QMember memberSub = new QMember("memberSub");

        //when
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.in(
                        select(memberSub.age)
                                .from(memberSub)
                                .where(memberSub.age.gt(10))
                )).fetch();

        assertThat(result).extracting("age")
                .containsExactly(20, 30, 40);

        //then

    }

    @DisplayName("")
    @Test
    void subquery_select() {
        QMember memberSub = new QMember("memberSub");

        //given
        List<Tuple> result = queryFactory
                .select(member.username,
                        select(memberSub.age.avg())
                                .from(memberSub))
                .from(member)
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("username = " + tuple.get(member.username));
            System.out.println("age = " + tuple.get(select(memberSub.age.avg()).from(memberSub)));
        }


        //when

        //then

    }


}
