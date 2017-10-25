package com.mk.spring.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mkanwar on 06/09/17.
 */
@Aspect
@Component
public class BookingAspect {
    @Before("execution(public * findAllBookings())")
    public void beforeFindingBookings(){
        System.out.println("I am about to find all bookings!");
        System.out.println("================================");
    }
    @After("execution(public * findAllBookings())")
    public void afterFindingBookings(){
        System.out.println("================================");
        System.out.println("I have found all the bookings!");
    }
    @Before("execution(* com.mk.spring.demo.BookingService.book(..)) && args(paramName,..)")
    public void beforebook(JoinPoint joinPoint, List<String> paramName ){
        System.out.println("I am about to book following bookings!");
        paramName.forEach(System.out::println);
        System.out.println("================================");
    }
}
