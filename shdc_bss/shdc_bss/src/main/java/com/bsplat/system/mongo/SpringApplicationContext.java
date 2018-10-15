package com.bsplat.system.mongo;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bsplat.system.common.exception.SysException;
import com.bsplat.system.mongo.util.ResourceUtils;

public class SpringApplicationContext implements ApplicationContextAware {
	private static final Logger logger = Logger
			.getLogger(SpringApplicationContext.class);
	private static ApplicationContext applicationContext;
	private static String[] fileNames;
	private static final int DEFAULT_REFRESH_DELAY = 60000;
	private static long lastChecked = System.currentTimeMillis();

	private static long refreshDelay = 60000L;

	public SpringApplicationContext(String[] fileNames, long refreshDelay) {
		this.fileNames = fileNames;
		this.refreshDelay = refreshDelay;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		try {
			return getApplicationContext().getBean(name);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("E_Spring_BeansException");
		}
	}

	public static Map getBeansOfType(Class clz) {
		try {
			return getApplicationContext().getBeansOfType(clz);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("E_Spring_BeansException");
		}
	}

	public static boolean containsBean(String name) {
		try {
			return getApplicationContext().containsBean(name);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("E_Spring_BeansException");
		}
	}

	public static boolean reloadingRequired() {
		boolean reloading = false;

		long now = System.currentTimeMillis();

		if (now > lastChecked + refreshDelay) {
			lastChecked = now;
			logger.debug("begin check config hasChanged:");

			if (ResourceUtils.getAndMarkChangedFiles(fileNames).size() > 0) {
				reloading = true;
			}
			logger.debug("end check config hasChanged:" + reloading);
		}

		return reloading;
	}

	static {
		try {
			// new
			// ClassPathXmlApplicationContext("classpath*:spring/framework/applicationContext.xml");
			applicationContext = new ClassPathXmlApplicationContext(fileNames);
			// ResourceUtils.getAndMarkFiles(fileNames);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.applicationContext = ctx;

	}
}
