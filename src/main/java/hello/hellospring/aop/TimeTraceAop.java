package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 컴포넌트 스캔으로 빈생성 해 줘도 되지만 만들어 놓은 config 설정에
// @bean 으로 직접 생성코드를 작성해주면 명시적으로 확인할 수 있기에 좋다
@Aspect
@Component
public class TimeTraceAop {

    // 공통관심사를 타겟팅해 줄 수 있다. execution
    @Around("execution(* hello.hellospring..*(..))") // 패키지 하위에 다 적용해 라는 의미
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " : " + timeMs + "ms");
        }

    }
}


