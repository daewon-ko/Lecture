package study.data_jpa.repository;

import lombok.Getter;
import study.data_jpa.entity.Team;

@Getter
public class MemberDto {
    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }


}
