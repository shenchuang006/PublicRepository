package com.aspire.client;

import com.aspire.service.impl.MyService;
import com.aspire.service.impl.MyServiceImplService;

public class WebServiceClientTest {
	public static void main(String[] args) {
		// ����һ�����ڲ���MyService�ӿ�ʵ���Ĺ���
		MyServiceImplService myServiceImplService = new MyServiceImplService();
		// �õ�MyService�ӿ�ʵ��
		MyService myServiceImpl = myServiceImplService.getMyServiceImplPort();
		// ����MyService�ӿڵķ�������
		int addRes = myServiceImpl.add(100, 123);
		System.out.println(addRes);
		System.out.println("------------�����ָ���------------");
		int minusRes = myServiceImpl.minus(100, 123);
		System.out.println(minusRes);
	} 
}
