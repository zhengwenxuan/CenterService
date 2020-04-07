package cn.hjw.common;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

/**
 * 日志工具类
 * 
 * @author lzh
 * @version 1.0
 */
public class LoggerUtil {
	//private static LoggerUtil lu = null;
	private Logger logger = null;
	public LoggerUtil(Class<?> c) {
		this.logger = Logger.getLogger(c);
		
	}

	public LoggerUtil(Object o) {
		this.logger = Logger.getLogger(o.getClass());
		
	}
	/**
	 * <b>得到日志对象</b>
	 * 
	 * @return Logger型
	 */
	public Logger getLogger() {
		return this.logger;
	}

	/**
	 * <b>输出info级别的日志</b><br>
	 * 用于输出一般的日志
	 * 
	 * @param o
	 *            日志信息
	 */
	public void info(Object o) {
		// Logger logger=getLogger();
		logger.info(o);
	}

	/**
	 * <b>输出debug级别的日志</b><br>
	 * 用于输出一般的日志
	 * 
	 * @param o
	 *            日志信息
	 */
	public void debug(Object o) {
		// Logger logger=getLogger();
		logger.debug(o);
	}

	/**
	 * <b>输出error级别的日志</b><br>
	 * 用于输出错误性质的日志。如果是要输出异常（Exception）类的日志，直接传入Exception对象即可
	 * 
	 * @param o
	 */
	public void error(Object o) {
		if (o instanceof Exception) {
			logger.error(getTrace((Exception) o));
		} else {
			logger.error(o);
		}
	}

	/**
	 * 
	 * @param t
	 * @return 字符串
	 */
	private String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}
}
