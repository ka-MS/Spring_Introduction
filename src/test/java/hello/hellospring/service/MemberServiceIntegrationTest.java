package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    // 테스트케이스에서는 필드인젝션을 해도 괜찮음
    // 가장 마지막 작업이고 다른곳에서 테스트코드를 가져다 쓸것이 아니기때문
    // 테스트는 반복할 수 있어야한다. -> db에 저장되어버리면 반복케이스가 안될것임
    // @Transactional을 테스트케이스에 사용하면 넣었던 데이터가 반영이 되지않고 롤백하여 반영시키지 않음
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given 무언가 주어졌을때
        Member member = new Member();
        member.setName("spring4");

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