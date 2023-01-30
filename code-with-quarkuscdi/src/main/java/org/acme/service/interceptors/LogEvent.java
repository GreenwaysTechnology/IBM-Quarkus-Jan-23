package org.acme.service.interceptors;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

//Custom annotation which binds biz logic and interceptors

@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface LogEvent {
}