package aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
public class LoggerBeforeAspect {

    private static Logger log = Logger.getLogger(LoggerBeforeAspect.class);

    @Before("execution(public * *Rest(..)) && within(service..*)")
    public void logger(JoinPoint jp){

        log.info("Timestamp: "+ LocalDateTime.now()+
                " | User: " +
                " | Clase origen : "+ jp.getTarget().getClass().getSimpleName()+
                " | Operación: " + jp.getSignature().getName()+
                " | parámetros: " + Arrays.toString(jp.getArgs())
        );
    }
}
