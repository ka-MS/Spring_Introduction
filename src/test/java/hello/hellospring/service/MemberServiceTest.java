package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    // MemberService memberService = new MemberService();
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // 이렇게 하면 서비스의 레포지토리 객체와 다른 또다른 객체를 생성한 것이 된다 변수가 static이 아니라면 문제가 생길것
    // 이럴때 필요한것이 의존성 주입 서비스의 메모리 객체에 생성자를 만들어 주고 의존성을 주입한다.
    @BeforeEach
    public void beforeEach() {
        memberRepository.clearStore();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        //given 무언가 주어졌을때
        Member member = new Member();
        member.setName("spring");

        //when 이걸 실행했을때
        Long saveId = memberService.join(member);

        //then 이런결과가 나와야해
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){ //서비스단 예외처리에 대한 테스트
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when 이걸 실행했을때
        memberService.join(member1);
        RuntimeException e = Assertions.assertThrows(RuntimeException.class, () -> memberService.join(member2));
        // 에러가 발생하면 성공하도록
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
//        try {
//            memberService.join(member2);
//            Assertions.fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
//        }

        //then 이런결과가 나와야해
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}