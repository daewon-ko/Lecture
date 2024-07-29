package org.example.jpql;

import jakarta.persistence.*;

import java.util.Collection;
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

            Team team1 = new Team();
            team1.setName("teamB");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setAge(10);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAge(20);

            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setAge(30);

            member1.changeTeam(team);
            member2.changeTeam(team);
            member3.changeTeam(team1);


            em.persist(team);
            em.persist(team1);
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();


            int resultcnt = em.createQuery("update Member  m set m.age = 20").executeUpdate();
            System.out.println("resultcnt = " + resultcnt);


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

