package cn.hjw.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;

public class SessionFactoryUtil {
	//private static LoggerUtil log = new LoggerUtil(SessionFactoryUtil.class);
	private static final String RESOURCE = "cn/config/mybatis-config.xml";
	private static SqlSessionFactory sqlSessionFactory = null;
	public static void initSqlSessionFactory() throws RuntimeException,
			Exception {
		try {

			if (sqlSessionFactory == null) {
				InputStream inputStream = Resources
						.getResourceAsStream(RESOURCE);
				sqlSessionFactory = new SqlSessionFactoryBuilder()
						.build(inputStream);
				
			}

		} catch (IOException ioex) {
			ioex.printStackTrace();
			//log.error(ioex);
		}
	}

	public static SqlSession getSession() {
		SqlSession sqlsession = (sqlSessionFactory != null) ? SqlSessionManager
				.newInstance(sqlSessionFactory) : null;
				
				
		return sqlsession;
	}
	public static SqlSession openSession() {
		SqlSession sqlsession = (sqlSessionFactory != null) ? sqlSessionFactory.openSession(false): null;

		return sqlsession;
	}
	
}