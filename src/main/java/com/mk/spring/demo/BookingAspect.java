package com.mk.spring.demo;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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
}
