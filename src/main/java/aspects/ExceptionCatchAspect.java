package aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Aspect
public class ExceptionCatchAspect {

    private static Logger log = Logger.getLogger(LoggerBeforeAspect.class);
/*
    @Around(value = "execution(public * *Rest(..))")
     Response afterException(ProceedingJoinPoint pjp) {
        String error=null;
        try {
            pjp.proceed();
        }catch (Throwable throwable) {
            error = throwable.getMessage();
            log.info("Timestamp: "+ LocalDateTime.now()+
                    " | Clase origen : "+ pjp.getTarget().getClass().getSimpleName()+
                    " | Operación: " + pjp.getSignature().getName()+
                    " | error: " + error);
        }
        return Response.ok(error).build();
    }
*/
}
