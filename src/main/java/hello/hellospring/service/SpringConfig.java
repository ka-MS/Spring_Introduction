package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링빈 직접 주입 방법
public class SpringConfig {

    @Bean // 1. 멤버서비스를 스프링빈에 올리고
    public MemberService memberService() {
        return new MemberService(memberRepository()); // 3.서비스에 레포지토리를 주입해줌
    }

    @Bean // 2. 멤버리포는 스프링빈에 올림
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
