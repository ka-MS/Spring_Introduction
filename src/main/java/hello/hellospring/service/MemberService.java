package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//@Service // 어노테이션을 안달아주면 순수자바클래스임, 어노테이션을 달아줘야 스프링 컨테이너에 등록하게됨

public class MemberService { //서비스클래스는 비즈니스의존적으로(join) 네이밍 설계 레포지토리는 좀더 개발관점으로 네이밍
    private final MemberRepository memberRepository;
//    @Autowired // 멤버서비스를 생성할 때 멤버리포지토리를 넣어줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return
     */
    @Transactional // db를 저장하거나 수정할 때 jpa를 쓰려면 트랜잭션이 있어야함 트랜잭션 안에서 실행되어야함
    public Long join(Member member) {
//        long start = System.currentTimeMillis(); // 실행시간 검증
        // 같은 이름이 있는 중복회원은 안되는조건
        // optional로 감싸면 optional 안에 멤버객체가 있는 것 null이 있는 자료의 경우 많이 사용
//        Optional<Member> result = memberRepository.findByName(member.getName());
        // 컨트롤 알트 v 하면 자동으로 속성과 변수 작성
        // 컨트롤 알트 쉬프트 t하면 refactor 자동완성 메뉴 나옴 extract method선택하면 메소드로 생성해줌
        try {
            validateDuplicateMember(member); // 중복회원 검증
            memberRepository.save(member);
            return member.getId();
        }finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("회원가입 시간 : " + timeMs + "ms");
        }

    }

    private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName())
            .ifPresent(m -> { //null이 아니고 값이 있으면 동작하도록 ifPresent
        throw new IllegalArgumentException("이미 존재하는 이름입니다.");
    });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers(){
            return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
