package cn.hjw.common;

import java.util.Properties;




/**
 * 常量类
 * @author feng
 *
 */
public class Constant {	
	public static Properties P; //资源文件
	//public static final String USERNAME="huanjianwa";//体检中心webservice访问认证用
	//public static final String PASSWORD="160113";//体检中心webservice访问认证用
	public static String getText(String key){
		return P.getProperty(key);
	}
}
