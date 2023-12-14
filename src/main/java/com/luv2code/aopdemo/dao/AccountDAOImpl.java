package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Repository;

import com.luv2code.aopdemo.Account;

@Repository
public class AccountDAOImpl implements AccountDAO {

	private String name;

	private String serviceCode;
	
	

	@Override
	public void addAccount(Account theAccount, boolean vipFlag) {
		System.out.println(getClass() + " Doing my DB work");
	}

	@Override
	public boolean doWork() {
		System.out.println(getClass() + "doWork()");
		return false;
	}

	public String getName() {
		System.out.println(getClass() + " getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + " setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + " getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + " setServiceCode()");
		this.serviceCode = serviceCode;
	}
	
	public  List<Account> findAccount(){
		
		return findAccount(false);
	}
	
	@Override
	public List<Account> findAccount( boolean tripWire) {
		
		if (tripWire) {
			throw new RuntimeException("No soup for you");
		}
		
		List<Account> myAccounts = new ArrayList<>();

		// Create sample account
		Account temp1 = new Account("John", "silver");
		Account temp2 = new Account("Akash", "Platinum");
		Account temp3 = new Account("Madhu", "Gold");
		
		//Add them to account list
		myAccounts.add(temp1);
		myAccounts.add(temp2);
		myAccounts.add(temp3);
		
		return myAccounts;
	}

}
