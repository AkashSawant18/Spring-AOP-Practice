package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(TrafficFortuneService theTrafficFortuneService, AccountDAO theAccountDAO,
			MembershipDAO theMembershipDAO) {
		return runner -> {
			// demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);

			// demoTheAfterReturningAdvice(theAccountDAO);

			// demoTheAfterThrowingAdvice(theAccountDAO);

			// demoTheAfterAdvice(theAccountDAO);

			// demoTheAroundAdvice(theTrafficFortuneService);

			demoTheAroundAdviceHandleException(theTrafficFortuneService);

		};
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("Main program ; demoTheAroundAdviceHandleException");
		System.out.println("Calling getFortune()");
		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);
		System.out.println("my fortune is :" + data);
		System.out.println("Finished");

	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("Main program ; demoTheAroundAdvice");
		System.out.println("Calling getFortune()");
		String data = theTrafficFortuneService.getFortune();
		System.out.println("my fortune is :" + data);
		System.out.println("Finished");
	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		// call method to to find account
		List<Account> theAccount = null;

		try {
			// add a boolean flag to simulate exception
			boolean tripWire = false;
			theAccount = theAccountDAO.findAccount(tripWire);
		} catch (Exception exc) {
			System.out.println("Main program :... caught exception :" + exc);
		}
		// Display the accounts
		System.out.println("Main Program : demoTheAfterThrowingAdvice");
		System.out.println("------------------");

		System.out.println(theAccount);
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {

		// call method to to find account
		List<Account> theAccount = null;

		try {
			// add a boolean flag to simulate exception
			boolean tripWire = true;
			theAccount = theAccountDAO.findAccount(tripWire);
		} catch (Exception exc) {
			System.out.println("Main program :... caught exception :" + exc);
		}
		// Display the accounts
		System.out.println("Main Program : demoTheAfterThrowingAdvice");
		System.out.println("------------------");

		System.out.println(theAccount);
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
		// call method to to find account
		// List<Account> theAccount = theAccountDAO.findAccount();

		// Display the accounts
		System.out.println("Main Program : demoTheAfterReturningAdvice");
		System.out.println("------------------");

		// System.out.println(theAccount);
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
		Account myAccount = new Account();
		myAccount.setName("Akash");
		myAccount.setLevel("Platinum");
		theAccountDAO.addAccount(myAccount, true);
		theMembershipDAO.addMember();
		theAccountDAO.doWork();
		theMembershipDAO.goToSleep();

		// call the accountdao getter/setter methods
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");
		theAccountDAO.getName();
		theAccountDAO.getServiceCode();

	}

}
