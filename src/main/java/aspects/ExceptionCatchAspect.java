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

    @Around(value = "execution(public * *Rest(..))")
     Response afterException(ProceedingJoinPoint pjp) {
        String error;
        Response r;
        try {
            pjp.proceed();
            r = Response.ok().build();
        }catch (Throwable throwable) {
            error = throwable.getMessage();
            log.info("Timestamp: "+ LocalDateTime.now()+
                    " | Clase origen : "+ pjp.getTarget().getClass().getSimpleName()+
                    " | Operación: " + pjp.getSignature().getName()+
                    " | error: " + error);
            r = Response.status(Response.Status.CONFLICT).entity(error).build();
        }
        return r;
    }

}
