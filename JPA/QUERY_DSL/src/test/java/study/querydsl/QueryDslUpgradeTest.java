package study.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.entity.QMember;

import java.util.List;

import static study.querydsl.entity.QMember.member;

@SpringBootTest
@Transactional
public class QueryDslUpgradeTest {

    @Autowired
    private EntityManager em;
    private JPAQueryFactory queryFactory;
    @PersistenceUnit
    EntityManagerFactory emf;

    @DisplayName("")
    @Test
    void test() {
        //given
        QMember member = QMember.member;
        List<MemberDto> result = queryFactory
                .select(Projections.bean(MemberDto.class
                        , member.username, member.age))
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

}
