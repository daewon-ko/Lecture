package hellojpa;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;
    @Column(name = "USERNAME")
    private String userName;

    //    @Column(name = "TEAM_ID")
//    private Long teamId;
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", updatable = false, insertable = false)
    private Team team;


    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

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
//    }
//
//    public void changeTeam(final Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }

    public Member() {
    }
}
