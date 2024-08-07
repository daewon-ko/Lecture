package study.data_jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.data_jpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);


    @Query("select m From Member m where m.username =:username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m From Member m ")
    List<Member> findUsernameList();

    @Query("select new study.data_jpa.repository.MemberDto(m.id, m.username, m.team) from Member m")
    List<MemberDto> findMemberDto();
}
