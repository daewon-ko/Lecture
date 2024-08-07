package study.data_jpa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.data_jpa.repository.MemberDto;
import study.data_jpa.repository.MemberRepository;

import java.util.List;

@RestController
public class HelloController {
    private final MemberRepository memberRepository;

    public HelloController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/dto")
    public ResponseEntity<List<MemberDto>> getDtoList() {

        return ResponseEntity.ok(memberRepository.findMemberDto());
    }

}
