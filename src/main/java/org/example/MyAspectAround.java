package org.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class MyAspectAround {

    @Before("execution(* org.example.Operator.*(..))")
    public void beforeDoSomething(final JoinPoint point) throws Throwable {
        final Method method = ((MethodSignature) point.getSignature()).getMethod();
        final long start = System.nanoTime();
        try {
//            final Object result = point.proceed();
            final long nano = System.nanoTime() - start;
            final long millis = nano / 1000000;
            System.out.println("After doSomething() method success - " + method.getClass().getName() + " " + method.getName() + " " + millis);
//            return result;
        } catch (final Throwable ex) {
            final long nano = System.nanoTime() - start;
            System.out.println("After doSomething() method failed - " + method.getClass().getName() + " " + method.getName());
            throw ex;
        }
    }
}
