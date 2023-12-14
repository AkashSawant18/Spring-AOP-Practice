package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Pointcut;


@Component
@Aspect
@Order(1)
public class MyCloudLogAsyncAspect {
	
	
	
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void myCloudLogAsync() {
		System.out.println("Logging to cloud in async fashion!!! ");
	}
}
