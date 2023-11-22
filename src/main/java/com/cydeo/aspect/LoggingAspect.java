package com.cydeo.aspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j // with this annotation we can get same commented variable below.
// @Slf4j = Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
public class LoggingAspect {

    // This is an old way so instead of writing these we can use @Slf4j annotation.
//    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SimpleKeycloakAccount userDetails = (SimpleKeycloakAccount) authentication.getDetails();
        return userDetails.getKeycloakSecurityContext().getToken().getPreferredUsername();
    }

    @Pointcut("execution(* com.cydeo.controller.ProjectController.*(..)) || execution(* com.cydeo.controller.TaskController.*(..))")
    public void anyProjectAndTaskControllerPC() {}

    @Before("anyProjectAndTaskControllerPC()")
    public void beforeAnyProjectAndTaskControllerAdvice(JoinPoint joinPoint) {
        log.info("Before -> Method: {}, User: {}"
                , joinPoint.getSignature().toShortString()
                , getUsername());
    }

}
