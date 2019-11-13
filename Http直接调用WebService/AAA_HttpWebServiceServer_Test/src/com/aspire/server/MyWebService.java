package com.aspire.server;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.aspire.model.User;

/**
 * �����ṩ��WebService����
 *
 * @author JustryDeng
 * @DATE 2018��9��22�� ����8:58:42
 */
@WebService
public class MyWebService {
	
	/**
	 * ��β���Ϊ��������,���β���Ϊ����
	 *
	 * @DATE 2018��9��22�� ����8:59:07
	 */
	public User userMethod(@WebParam(name ="name") String name, 
					@WebParam(name ="age") Integer age,
					@WebParam(name ="motto") String motto) {
		System.out.println("---> ��userMethod�����ˣ�");
		User user = new User();
		user.setMyAge(age * 100);
		user.setMyName(name + "���˧");
		user.setMyMotto(motto + "������ô����!");
		return user;
	}

}