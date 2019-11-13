package com.aspire.util;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//�̳߳�����
@Configuration 
@EnableAsync 
public class ThreadPoolConfig {

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// ���ú����߳���
		executor.setCorePoolSize(5);
		// ��������߳���
		executor.setMaxPoolSize(5);
		// ���ö��д�С
		executor.setQueueCapacity(99999);
		// �����̳߳��е��̵߳�����ǰ׺
		executor.setThreadNamePrefix("async-executor-");

		// rejection-policy����pool�Ѿ��ﵽmax size��ʱ����δ���������
		// CALLER_RUNS���������߳���ִ�����񣬶����ɵ��������ڵ��߳���ִ��
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// ִ�г�ʼ��
		executor.initialize();
		return executor;
	}
}
