package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // optional은 java 8에 생긴 기능으로
    // 널을 그대로 반환하는게 아닌 optional로 감싸서 반환하는 방식 사용
    List<Member> findAll();
}
