package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code

        try {


            Team team = new Team();
            team.setName("teamA");
            em.persist(team);


            Member member1 = new Member();
            member1.setUserName("member1");
            member1.setTeam(team);

            em.persist(member1);

            em.flush();
            em.clear();


            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("member1.getTeam().getClass() = " + findMember.getTeam().getClass());




//
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
