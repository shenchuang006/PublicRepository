package com.aspire.server;

import javax.xml.ws.Endpoint;

/**
 * ����WebService
 *
 * @author JustryDeng
 * @DATE 2018��9��22�� ����9:06:19
 */
public class MyServer {
	
	public static void main(String[] args) {
		// �Լ�����һ�����ʵ�ַ
		String address = "http://127.0.0.1:9527/webservice/test";
		Endpoint.publish(address, new MyWebService());
	}
}

