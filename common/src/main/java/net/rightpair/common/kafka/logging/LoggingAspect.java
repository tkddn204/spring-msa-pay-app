package net.rightpair.common.kafka.logging;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("!test")
@RequiredArgsConstructor
public class LoggingAspect {
    private final LoggingProducer loggingProducer;

    @Before("execution(* net.rightpair.*.adapter.in.web.*.*(..))")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        loggingProducer.sendMessage("logging", "Before executing method: " + methodName);
        // Produce Access log
    }
}
