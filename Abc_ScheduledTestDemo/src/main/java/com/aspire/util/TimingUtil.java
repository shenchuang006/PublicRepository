package com.aspire.util;

import java.util.Calendar;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Scheduled����
 *
 * @author JustryDeng
 * @date 2018��7��9�� ����7:29:11
 */
@Component
public class TimingUtil {
	/**
	 * ��ʱ�ƻ�One
	 *
	 * @throws InterruptedException
	 * @date 2018��7��9�� ����7:29:28
	 */
	@Scheduled(cron = "0/10 * * * * ?")
	@Async("asyncExecutor")// ʹ��asyncExecutor�����������̳߳�
	public void scheduledTestOne() throws InterruptedException {
		long start = System.currentTimeMillis();
		// ʹ���߳�sleep��ģ��˷�������ʱ����ʱ��(Ϊ�˸���ֱ�۵Ľ���˵��,���ﶨ��ʱ��ϴ�)
		Thread.sleep(6000);
		Calendar calendar = Calendar.getInstance();
		System.out.println("One>>>" + calendar.get(calendar.MINUTE) + "��" 
		                       + calendar.get(calendar.SECOND) + "��");
		long end = System.currentTimeMillis();
		System.out.println("��ʱ---------------" + (end - start) + "����\n");
	}

	/**
	 * ��ʱ�ƻ�Two
	 *
	 * @date 2018��7��9�� ����7:29:42
	 */
	@Scheduled(cron = "0/5 * * * * ?")
	@Async("asyncExecutor") // ʹ��asyncExecutor�����������̳߳�
	public void scheduledTestTwo() {
		long start = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		System.out.println("Two>>>" + calendar.get(calendar.MINUTE) + "��" 
		                       + calendar.get(calendar.SECOND) + "��");
		long end = System.currentTimeMillis();
		System.out.println("��ʱ---------------" + (end - start) + "����\n");
	}
}
