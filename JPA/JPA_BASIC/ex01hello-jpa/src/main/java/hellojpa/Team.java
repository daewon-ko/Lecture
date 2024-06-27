package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;


    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    /**
     * add할때 NPE 피하기 위해 new ArrayList로 할당해주는 것이 일종의 관례
     */
    private List<Member> members = new ArrayList<>();


    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(final List<Member> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
