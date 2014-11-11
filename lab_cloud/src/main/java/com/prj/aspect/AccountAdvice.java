package com.prj.aspect;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class AccountAdvice implements MethodBeforeAdvice{

	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println("IT WORKS!");
	}

}
