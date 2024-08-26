package study.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.dto.UserDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.Team;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.*;
import static study.querydsl.entity.QMember.member;

@SpringBootTest
@Transactional
public class QueryDslUpgradeTest {

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
    void simpleProjection() {

        //given
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }

        //when

        //then

    }

    @DisplayName("")
    @Test
    void tupleProjection() {

        //given
        List<Tuple> fetch = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();
        for (Tuple tuple : fetch) {
            String username = tuple.get(member.username);
            Integer age = tuple.get(member.age);
            System.out.println("username = " + username);
            System.out.println("age = " + age);
        }

        //when

        //then

    }

    @DisplayName("")
    @Test
    void findDtoByJPQL() {

        //given
        List<MemberDto> resultList = em.createQuery("SELECT  new study.querydsl.dto.MemberDto(m.username, m.age) FROM Member m", MemberDto.class)
                .getResultList();

        for (MemberDto memberDto : resultList) {
            System.out.println("memberDto = " + memberDto);
        }
        //when

        //then

    }


    @DisplayName("")
    @Test
    void findDtoByQueryDsl() {
        //given
        QMember member = QMember.member;
        /**
         * bean방식은 getter, setter를 통해 값이 주입
         */
        List<MemberDto> result = queryFactory
                .select(Projections.bean(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();

        List<MemberDto> result2 = queryFactory
                .select(Projections.fields(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();

        //when

        //then

    }



    @DisplayName("")
    @Test
    void findDtoByConstructor() {

        //given
        List<MemberDto> result = queryFactory
                .select(Projections.constructor(MemberDto.class,
                        member.username,
                        member.age))

                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }

        //when

        //then

    }


    @DisplayName("")
    @Test
    void findUserDtoByQueryDsl() {
        //given
        QMember member = QMember.member;
        QMember memberSub = new QMember("memberSub");

        /**
         * bean방식은 getter, setter를 통해 값이 주입
         */
        List<UserDto> result = queryFactory
                .select(Projections.fields(UserDto.class,
                        member.username.as("name"),

                        ExpressionUtils.as(select(memberSub.age.max())
                                .from(memberSub), "age"
                        )))
                .from(member)
                .fetch();

        for (UserDto userDto : result) {
            System.out.println("userDto = " + userDto);
        }

        //when

        //then

    }

    @DisplayName("")
    @Test
    void findDtoByQueryProjection() {

        //given
        List<MemberDto> result = queryFactory
                .select(new QMemberDto(member.username, member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }

        //when

        //then

    }

    @DisplayName("")
    @Test
    void dynamicQuery_BooleanBuilder() {

        //given
        String usernameParam = "member1";
        Integer ageParam = null;

        List<Member> result = searchMember1(usernameParam, ageParam);

        Assertions.assertThat(result.size()).isEqualTo(1);

        //when

        //then

    }

    private List<Member> searchMember1(String usernameCond, Integer ageCond) {
        BooleanBuilder builder = new BooleanBuilder();

        if(usernameCond != null) {
            builder.and(member.username.eq(usernameCond));
        }

        if(ageCond != null) {
            builder.and(member.age.eq(ageCond));
        }

        return queryFactory
                .selectFrom(member)
                .where(
                        builder
                )
                .fetch();

    }

    @DisplayName("")
    @Test
    void dynamicQuery_WhereParam() {

        //given

        String usernameParam = "member1";
        Integer ageParam = null;


        List<Member> result = searchMember2(usernameParam, ageParam);

        Assertions.assertThat(result.size()).isEqualTo(1);
        //when

        //then

    }

    private List<Member> searchMember2(String usernameCond, Integer ageCond) {
        return queryFactory
                .selectFrom(member)
                .where(usernameEq(usernameCond), ageEq(ageCond))
                .fetch();

    }

    private BooleanExpression ageEq(Integer ageCond) {

        return ageCond != null ? member.age.eq(ageCond) : null;

    }

    private BooleanExpression usernameEq(String usernameCond) {

        return usernameCond == null ? null : member.username.eq(usernameCond);

    }

    private Predicate allEq(String usernameCond, Integer ageCond) {
        return usernameEq(usernameCond).and(ageEq(ageCond));

    }


    @DisplayName("")
    @Test
    void bulkUPdate() {

        //given
        long count = queryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();

        em.flush();
        em.clear();
        //when

        //then

    }

    @DisplayName("")
    @Test
    void bulkAdd() {

        //given
        queryFactory
                .update(member)
                .set(member.age, member.age.add(1))
                .execute();

        //when

        //then

    }

    @DisplayName("")
    @Test
    void bulkDelete() {

        //given
        long count = queryFactory
                .delete(member)
                .where(member.age.gt(18))
                .execute();

        //when

        //then

    }

    @DisplayName("")
    @Test
    void sqlFunction() {

        //given
        List<String> result = queryFactory
                .select(Expressions.stringTemplate("function('replace', {0}, {1}, {2})",
                        member.username, "member", "M"))
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }


        //when

        //then

    }

    @DisplayName("")
    @Test
    void sqlFunction2() {

        //given
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
//                .where(member.username.eq(Expressions.stringTemplate("function('lower',{0})",
//                        member.username)))
                .where(member.username.eq(member.username.lower()))
                .fetch();


        //when

        //then

    }








}
