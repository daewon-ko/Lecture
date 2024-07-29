package hellojpa;

import jakarta.persistence.*;

@Entity
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USERNAME")
    private String userName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(final Team team) {
        this.team = team;
    }


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }


    public void setUserName(final String userName) {
        this.userName = userName;
    }
//    public Team getTeam() {
//        return team;
//
//    public void changeTeam(final Team team) {
//    }
//        this.team = team;
//        team.getMembers().add(this);
//    }

    public Member() {
    }


}
