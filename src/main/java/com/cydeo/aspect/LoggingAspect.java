package com.cydeo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j // with this annotation we can get same commented variable below.
// @Slf4j = Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
public class LoggingAspect {
    // This is an old way so instead of writing these we can use @Slf4j annotation.
//    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);



}
