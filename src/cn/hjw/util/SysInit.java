package cn.hjw.util;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

import cn.hjw.common.Constant;


/**
 * 系统初始化
 * @author lzh
 *
 */
public class SysInit extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void init() throws ServletException {
		    initLog4j();
			initConfig();
		
	}
	public void initLog4j(){
		try{
			System.setProperty("webapp.root", getServletContext().getRealPath("/"));
			PropertyConfigurator.configure(getServletContext().getRealPath("/")+"WEB-INF/log4j.properties");
			System.out.println("================Log4j加载成功==============");
		}catch(Exception e){
			System.out.println("================Log4j加载失败==============");
		}
	}
	
	/**
	 * 初始化配置文件
	 */
	public void initConfig(){
		try{			
			
			InputStream in = this.getClass().getResourceAsStream("/cn/config/SysSetings.properties");
			Constant.P = new Properties();
			Constant.P.load(in);
			in.close();
			System.out.println("================配置文件加载成功==============");
		}catch(Exception e){
			System.out.println("================配置文件加载失败==============");
		}
	}
	
	
	

}
