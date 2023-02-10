package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {

    // findByName라는 이름을 쓰면 -> 메서드 이름 규칙이있음
    // jpql select m from Member m where m.name = ? 라는 쿼리를 자동으로 짜줌
    @Override
    Optional<Member> findByName(String name);
    // 이러면 구현 끝.. 인터페이스 만으로도 개발완료..
}
