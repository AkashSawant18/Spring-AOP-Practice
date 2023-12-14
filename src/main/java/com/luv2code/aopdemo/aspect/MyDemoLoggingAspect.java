package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	// Point-cut Expression

//	//@Before("execution(public void add*())")
//	@Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
//	public void beforeAddAccountAdvice() {
//		System.out.println("Executing @Before advice on addAccoiunt()");
//	}

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("Executing @Before advice on addAccoiunt()");

		// Display Method signature
		MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();

		System.out.println("Method " + methodSignature);

		// Display Method arguments

		// get args

		Object[] args = theJoinPoint.getArgs();

		// loop through args
		for (Object tempArg : args) {
			System.out.println(tempArg);

			if (tempArg instanceof Account) {
				// downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;

				System.out.println("Account Name :" + theAccount.getName());
				System.out.println("Account level " + theAccount.getLevel());
			}
		}

	}

	// Add a new advice for after returning the method

	@AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))", returning = "result")
	public void afterReturningfindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("Executing After returning on method : " + method);

		// print out the results of the method call
		System.out.println("Result is : " + result);

		// lets post process the data
		// convert the account names to uppercase
		convertAccountNamesToUppercase(result);
		System.out.println("Result is : " + result);

	}

	private void convertAccountNamesToUppercase(List<Account> result) {
		// loop through accounts

		for (Account tempAccount : result) {
			// get uppercase version of name
			String theUppercaseName = tempAccount.getName().toUpperCase();
			// update the name on accuont
			tempAccount.setName(theUppercaseName);
		}

	}

	@AfterThrowing(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {

		// print which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("Executing After throwing on method : " + method);

		// log the exception
		System.out.println("the Exception is " + theExc);

	}

	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccoutsAdvice(JoinPoint theJoinPoint) {
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("Executing After (finally) on method : " + method);
	}

	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		// print which method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		System.out.println("Executing After throwing on method : " + method);

		// get begin timestamp
		long begin = System.currentTimeMillis();

		// execute the method
		Object result = null;

		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the exception
			System.out.println(e.getMessage());

			// user custom message
			result = "Major accident!! but no worries , your private aop helicopter is on the way";
		}

		// get end timestamp
		long end = System.currentTimeMillis();

		// compute duration and display it
		long duration = end - begin;
		System.out.println("Duration :" + duration / 1000.0 + "seconds");

		return null;

	}

}
