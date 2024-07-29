package hellojpa;

import jakarta.persistence.*;


public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code

        try {

//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Member> query = cb.createQuery(Member.class);
//            Root<Member> m = query.from(Member.class);
//            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("userName"), "kim"));
//            List<Member> resultList = em.createQuery(cq).getResultList();


            Member member = new Member();
            member.setUserName("Hello");
            em.persist(member);

            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember = " + findMember);
//            System.out.println("userName = " + userName);

            Member proxy = em.getReference(Member.class, member.getId());
            System.out.println("proxy.getClass() = " + proxy.getClass());
////            System.out.println(findMember.getClass().getName());

            tx.commit();

//            System.out.println("member = " + member);

        } catch (Exception e) {
            tx.rollback();
        }

        finally {
            em.close();
        }
        emf.close();
    }
}
