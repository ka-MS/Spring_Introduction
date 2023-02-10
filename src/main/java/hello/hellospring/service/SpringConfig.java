package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링빈 직접 주입 방법
public class SpringConfig {

//    private DataSource dataSource; //스프링부트가 관리하는 데이터소스를 빈으로 생성
//    @Autowired // DI 생성자 주입
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private EntityManager em; //jpa를 사용하려면 엔티티매니저 생성해줘야함
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean // 1. 멤버서비스를 스프링빈에 올리고
    public MemberService memberService() { // 3.서비스에 레포지토리를 주입해줌
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

//    @Bean // 2. 멤버리포는 스프링빈에 올림
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
//        // 다른 코드는 변경하지 않고 db변경 완료
//
//    }

}
