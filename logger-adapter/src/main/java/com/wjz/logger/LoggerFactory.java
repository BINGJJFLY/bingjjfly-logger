package com.wjz.logger;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import com.wjz.logger.log4j.Log4jLogger;

public class LoggerFactory {

	private static Constructor<? extends Logger> loggerConstructor;

	static {
		List<Class<? extends Logger>> candidates = Arrays.asList(Log4jLogger.class);
		for (Class<? extends Logger> candidate : candidates) {
			try {
				setImplementation(candidate);
				break;
			} catch (Exception e) {
			}
		}
	}
	
	public static Logger getLogger(Class<?> clazz) {
		return getLogger(clazz.getName());
	}

	public static Logger getLogger(String className) {
		try {
			return loggerConstructor.newInstance(className);
		} catch (Exception e) {
			return null;
		}
	}

	private static void setImplementation(Class<? extends Logger> candidate) throws Exception {
		if (loggerConstructor == null) {
			Constructor<? extends Logger> constructor = candidate.getConstructor(String.class);
			Logger logger = constructor.newInstance(LoggerFactory.class.getName());
			if (logger.isDebugEnabled()) {
				logger.debug("Logging initialized using '" + candidate + "' adapter.");
			}
			loggerConstructor = constructor;
		}
	}
}
