package com.wjz.logger.log4j;

import org.junit.Test;

import com.wjz.logger.Logger;
import com.wjz.logger.LoggerFactory;

public class Log4jLoggerTest {

	@Test
	public void test() {
		Logger logger = LoggerFactory.getLogger(Log4jLoggerTest.class);
		logger.info("hello world");
	}
}
