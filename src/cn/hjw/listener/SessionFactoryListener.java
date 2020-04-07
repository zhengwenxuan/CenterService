package cn.hjw.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import cn.hjw.util.SessionFactoryUtil;




public class SessionFactoryListener implements ServletContextListener {
	//private static LoggerUtil log = new LoggerUtil(SessionFactoryListener.class);	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		try {	
			System.out.println("============初始化sqlSessionFactory===========");
			SessionFactoryUtil.initSqlSessionFactory();

		} catch (RuntimeException rex) {
			rex.printStackTrace();			
			//log.error(rex);

		} catch (Exception ex) {
			ex.printStackTrace();
			//log.error(ex);

		}

	}

}
