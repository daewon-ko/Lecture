
package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import com.jpabook.jpashop.repository.MemberRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// java가 제공하는 Transactoinal, Spring이 제공하는 Transactional 2개가 존재 후자가 더 좋다. (쓸 수 있는 옵션때문에)
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


//    private final MemberRepositoryOld memberRepositoryOld;

        private final MemberRepository memberRepository;

    //회원가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        /**
         * 동시성을 고려해서 member의 name등을 Unique로 제약조건을 거는 것이 권장됨.
         */
        //Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

    }

    //회원 전체 조회
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).get();
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setName(name);

    }
}
