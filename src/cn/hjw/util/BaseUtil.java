package cn.hjw.util;

import java.util.List;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;

import cn.hjw.common.LoggerUtil;

import com.google.gson.Gson;



/**
 * Service基类
 * 各Service类需继承此类
 * 
 * 定义service类中的共通“方法”、“对象”等
 * @author feng
 *
 */
public class BaseUtil {
	//日志对象
		protected  LoggerUtil log = null;
		protected  Gson gson = null;
		private ObjectFactory objectFactory=null;
		protected BaseUtil(Class<?> cls){
				log = new LoggerUtil(cls);
				gson = new Gson();
				objectFactory=new DefaultObjectFactory();
			}
		/**
		 * 
		 * @param cls 对象类型
		 * @return
		 */
		protected Object getObject(Class<?> cls){
			return objectFactory.create(cls);
		}
		/**
		 * 
		 * @param cls 对象类型
		 * @param argTypes 参数类型
		 * @param argV 参数值
		 * @return
		 */
		protected Object getObject1(Class<?> cls,List<Class<?>> argTypes,List<Object> argV){
			return objectFactory.create(cls, argTypes, argV);
		}
}
