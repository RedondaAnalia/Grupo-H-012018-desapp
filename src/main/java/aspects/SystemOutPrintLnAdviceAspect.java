package aspects;

import model.exceptions.SystemOutPrintLnException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SystemOutPrintLnAdviceAspect {

    @Around("execution(public * model..*.*(..))")
    public Object aroundGreeting(ProceedingJoinPoint joinPoint)throws Throwable {
        if(joinPoint.getSignature().getName().equals("System.out.Println"))
            throw new SystemOutPrintLnException();
        return joinPoint.proceed();
    }
}
