package com.prj.aspect;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;

import com.prj.entity.Account;

public class BeforeAdviceTest {
//	private Account target;
//	private BeforeAdvice advice;
////	private ProxyFactory pf;
//	private AspectJProxyFactory pf;
	
	public static void main(String[] args) {
		Account target;
		BeforeAdvice advice;
		AspectJProxyFactory pf;
		target = new Account();
//		advice = new AccountAdvice();
		pf = new AspectJProxyFactory();
		pf.setTarget(target);
//		pf.addAdvice(advice);
		pf.addAspect(AccountAspect.class);
		Account proxy = (Account)pf.getProxy();
		proxy.getAccountNumber();
		
	}
}
