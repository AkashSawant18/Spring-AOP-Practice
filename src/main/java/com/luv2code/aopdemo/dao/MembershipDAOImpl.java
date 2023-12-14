package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{

	@Override
	public boolean addMember() {
		System.out.println("Doing my DB work In Membership Account");
		return true;
		
	}

	@Override
	public void goToSleep() {
		System.out.println("I am going to Sleep");
	}

}
