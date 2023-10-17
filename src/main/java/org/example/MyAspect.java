package org.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class MyAspect {

    @Around("(execution(* org.example.Operator.*(..)) ||" +
            "execution(* org.example.food_items..*.*(..)) ||" +
            "execution(* org.example.ingredients..*.*(..)) ||" +
            "execution(* org.example.machines..*.*(..))) &&" +
            "!execution(* *.lambda*(..))")
    public Object logTimes(final ProceedingJoinPoint point) throws Throwable {
        final Method method = ((MethodSignature) point.getSignature()).getMethod();
        final long start = System.nanoTime();
        try {
            final Object result = point.proceed();
            final long nano = System.nanoTime() - start;
            final long millis = nano / 1000000;
            System.out.println("After doSomething() method success - " + method.getName() + " " + millis);
            return result;
        } catch (final Throwable ex) {
            final long nano = System.nanoTime() - start;
            System.out.println("After doSomething() method failed - " + method.getName());
            throw ex;
        }
    }
}
