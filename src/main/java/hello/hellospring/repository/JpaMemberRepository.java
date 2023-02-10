package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; 
    //jpa는 엔티티매니저로 모든게 동작함 커넥션정보 연결 db연결등을 다 해줌
    // jpa를 사용하려면 em을 주입받아야함

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member); // 이렇게하면 인서트쿼리 다 넣어주고 set id 까지 다 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // jpql쿼리언어 -> 객체를 대상으로 쿼리를 날리면 sql로 번역됨 , 객체 자체를 select함
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }
}
