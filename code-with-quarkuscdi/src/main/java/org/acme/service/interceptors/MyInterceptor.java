package org.acme.service.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@LogEvent
public class MyInterceptor {
    //method
    @AroundInvoke
    public Object logEvent(InvocationContext invocationContext) throws Exception {
        System.out.println("logs");
        System.out.println("Method : " + invocationContext.getMethod().getName());
        System.out.println("Arguments: " + invocationContext.getParameters());
        System.out.println("Target " + invocationContext.getTarget().getClass().getName());
        return invocationContext.proceed();
    }
}
