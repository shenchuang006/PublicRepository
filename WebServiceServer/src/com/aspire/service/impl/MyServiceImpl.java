package com.aspire.service.impl;

import javax.jws.WebService;

import com.aspire.service.MyService;
//endpointInterfaceָ����ʵ�ֽӿڵ�ȫ����
@WebService(endpointInterface="com.aspire.service.MyService")
public class MyServiceImpl implements MyService {

	@Override
	public int add(int a, int b) {
		System.out.println(a+"+"+b+"="+(a+b));
		return a+b;
	}

	@Override
	public int minus(int a, int b) {
		System.out.println(a+"-"+b+"="+(a-b));
		return a-b;
	}
	
}
