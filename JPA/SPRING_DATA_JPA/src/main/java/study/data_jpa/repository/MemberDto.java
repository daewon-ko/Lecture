package study.data_jpa.repository;

import lombok.Getter;
import study.data_jpa.entity.Team;

@Getter
public class MemberDto {
    private Long id;
    private String username;
    private Team team;

    public MemberDto(Long id, String username, Team team) {
        this.id = id;
        this.username = username;
        this.team = team;
    }


    public MemberDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
