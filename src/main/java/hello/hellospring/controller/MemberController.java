package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 스프링컨테이너가 componentscan을 통해 해당 spring bean으로 객체를 생성하여 담아두고 관리함
public class MemberController {
    // 멤버컨트롤러가 멤버서비스를 통해서 회원가입과 조회등을 할 수 있어야한다.
    // 그걸 컨트롤러가 서비스를 의존한다라고 함
    // 컨트롤러에서 외부요청을 받고 서비스에서 비즈니스 로직을 만들고 레포지토리에서 데이터를 저장하는 방식을 사용
    // controller -> memberService -> memberRepository
    private final MemberService memberService;

    @Autowired // 생성자에서 쓰면 멤버컨드롤러가 생성될때 스프링빈에 등록되어있는 멤버서비스 객체를 넣어줌 의존성 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        System.out.println("member : " + member.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }


}
