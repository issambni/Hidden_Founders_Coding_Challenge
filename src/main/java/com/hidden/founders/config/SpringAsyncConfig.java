package com.hidden.founders.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import com.hidden.founders.component.CustomAsyncExceptionHandler;

@Configuration
	@EnableAsync
	public class SpringAsyncConfig implements AsyncConfigurer {
	/**
	 * Configuration for the dislikes Async Component.
	 */
		@Override
	    public Executor getAsyncExecutor() {
	        return new SimpleAsyncTaskExecutor();
	    }

		@Override
		public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
			return new CustomAsyncExceptionHandler();
		}
	}


