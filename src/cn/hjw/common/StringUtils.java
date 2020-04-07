package cn.hjw.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


/**
 * 字符串处理
 * 
 * @author lzh
 * 
 */
public class StringUtils {
	public static Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	private static Type typeToken = new TypeToken<Map<String, Object>>() {
	}.getType();

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	@SuppressWarnings("rawtypes")
	public static boolean listIsNull(List list) {
		if (list != null && list.size() > 0)
			return true;
		return false;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String emptyConvert(String str, String str1) {
		return isNotEmpty(str) ? str : str1;
	}

	// add by fyg
	public static String notNull(String str) {
		if (str == null)
			return "";
		else
			return str.trim();
	}

	// add by fyg
	public static String iso2gb(String s) {
		if (s == null)
			return "";
		try {
			return new String(s.getBytes("ISO-8859-1"), "GBK");
		} catch (Exception e) {
			return s;
		}
	}

	// add by fyg
	public static String iso2utf(String s) {
		if (s == null)
			return "";
		try {
			return new String(s.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			return s;
		}
	}

	// add by fyg
	public static String gb2iso(String s) {
		if (s == null)
			return "";
		try {
			return new String(s.getBytes("GBK"), "ISO-8859-1");
		} catch (Exception e) {
			return s;
		}
	}

	public static String trim(String str) {
		return str == null ? null : str.trim();
	}
	public static String trim(Object str) {
		return str == null ? null : ((String)str).trim();
	}
	public static final String EMPTY = "";

	public static String join(String[] array) {
		return join(array, null);
	}

	public static String join(String[] list, String separator) {
		separator = separator == null ? EMPTY : separator;
		StringBuffer buff = new StringBuffer(5 * list.length);
		for (int i = 0; i < list.length; i++) {
			String s = list[i];
			if (i > 0) {
				buff.append(separator);
			}
			if (s != null) {
				buff.append(s);
			}
		}
		return buff.toString();

	}

	/**
	 * 
	 * @param ids
	 * @return
	 */
	public static String arraySql(String[] ids) {
		String sql = "";
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				if (ids[i] != null && ids[i].trim().length() > 0) {
					if (i == ids.length - 1) {
						sql += "'" + ids[i] + "'";
					} else {
						sql += "'" + ids[i] + "',";
					}
				}
			}
		} else {
			return "''";
		}
		return sql;
	}

	/**
	 * 
	 * @param ids
	 * @return
	 */
	public static String arraySql(String ids) {
		if (ids != null && ids.length() > 0) {
			return arraySql(ids.split(","));
		} else {
			return "''";
		}
	}

	/**
	 * 
	 * @param ids
	 * @return
	 */
	public static List<String> arraySqlList(String ids) {
		String[] strs = ids.split(",");
		List<String> sqls = new ArrayList<String>();
		for (String str : strs) {
			if (str.length() > 0)
				sqls.add(str);
		}
		return sqls;
	}

	/**
	 * uuid
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 转换成timestamp
	 * 
	 * @param obj
	 */
	public static Timestamp convTimestamp(Object obj) {
		if (obj == null)
			return null;
		return (Timestamp) obj;
	}

	/**
	 * 转换成Integer
	 * 
	 * @param obj
	 * @return
	 */
	public static Integer convInteger(Object obj) {
		if (obj == null)
			return null;
		return Integer.valueOf(String.valueOf(obj));
	}

	/**
	 * 转换成Double
	 * 
	 * @param obj
	 * @return
	 */
	public static Double convDouble(Object obj) {
		if (obj == null)
			return Double.valueOf("0");
		return Double.valueOf(String.valueOf(obj));
	}

	/**
	 * 转换成String
	 * 
	 * @param obj
	 * @return
	 */
	public static String convString(Object obj) {
		if (obj == null)
			return "";
		return String.valueOf(obj);
	}

	/**
	 * 获取根目录
	 * 
	 * @return
	 */
	public static String getWebContextPath() {
		String webPath = new File(new File(StringUtils.class.getResource("/")
				.getPath()).getParent()).getParent();
		return webPath;
	}

	/**
	 * response字符串消息
	 * 
	 * @param response
	 * @param msg
	 */
	public static void responseMessage(HttpServletResponse response, Object obj) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			out = response.getWriter();
			out.print(new Gson().toJson(obj));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 解析inputStream
	 * 
	 * @param is
	 * @return
	 */
	public static String handleRequest(InputStream is) {
		String result = "";
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(is);
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			result = new String(bos.toByteArray(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String doubleTrans(double d) {
		return String.valueOf((long) d);
		/*
		 * if (Math.round(d) - d == 0) { return String.valueOf((long) d); }
		 * 
		 * return String.valueOf(d);
		 */
	}

	public static String doubleTrans(Object d) {
		if (d == null) {
			return "";
		}
		double b = Double.valueOf(String.valueOf(d));
		return doubleTrans(b);
	}

	/**
	 * 获取list中连续数字的次数
	 * 
	 * @Title: continueNum
	 * @Description: TODO(list中的数据有序)
	 * @param @return 参数说明
	 * @return String 返回类型
	 * @throws
	 */
	public static int continueNum(List<Map<String, Object>> list) {
		int num = 0;
		for (int i = 0; i < list.size(); i++) {
			if (i != list.size() - 1) {
				if (Integer.valueOf(StringUtils.doubleTrans(list.get(i).get(
						"sign_number"))) + 1 == Integer.valueOf(StringUtils
						.doubleTrans(list.get(i + 1).get("sign_number")))) {
					num += 1;
				} else {
					num = 1;
				}
			} else {
				if (list.size() == 1) {
					num = 1;
					break;
				}
				if (list.size() > 1
						&& Integer.valueOf(StringUtils.doubleTrans(list.get(i)
								.get("sign_number"))) - 1 == Integer
								.valueOf(StringUtils.doubleTrans(list
										.get(i - 1).get("sign_number")))) {
					num += 1;
				}
			}
		}
		return num + 1;
	}

	

	

	/**
	 * 解析参数
	 * 
	 * @Title: analysisJsonPara
	 * @Description: TODO(字符串解析成map集合)
	 * @param @param jsonPara 参数说明
	 * @return void 返回类型
	 * @throws
	 */
	public static Map<String, Object> analysisJsonPara(String jsonPara) {
		return gson.fromJson(jsonPara, typeToken);
	}

	/**
	 * 转化参数
	 * 
	 * @Title: conversion
	 * @Description: TODO(map集合转化成字符串)
	 * @param @param param 参数说明
	 * @return void 返回类型
	 * @throws
	 */
	public static String convertToJson(Map<String, Object> param) {
		return gson.toJson(param);
	}

	public static String convertToJson(Object param) {
		return gson.toJson(param);
	}

	/**
	 * 获取图片存放路径
	 * 
	 * @Title: getImgPath
	 * @Description: TODO
	 * @param @param imgName
	 * @param @return
	 * @param @throws Exception 参数说明
	 * @return String 返回类型
	 * @throws
	 */
	public static String getImgPath(String imgName, String path)
			throws Exception {
		String imgPath = "";
		try {
			String basePath = new File(new File(StringUtils.class.getResource(
					"/").getPath()).getParent()).getParent();
			imgPath = basePath + File.separator + path + File.separator;
			imgPath = imgPath.replace("%20", " ");
			if (!new File(imgPath).exists()) {
				new File(imgPath).mkdirs();
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return imgPath + imgName;
	}

	/**
	 * 获取图片存放路径
	 * 
	 * @Title: getImgPath
	 * @Description: TODO
	 * @param @param imgName
	 * @param @return
	 * @param @throws Exception 参数说明
	 * @return String 返回类型
	 * @throws
	 */
	public static String getPath(String path) throws Exception {
		String imgPath = "";
		try {
			String basePath = new File(new File(StringUtils.class.getResource(
					"/").getPath()).getParent()).getParent();
			imgPath = basePath + File.separator + path + File.separator;			
			imgPath = imgPath.replace("%20", " ");
			if (!new File(imgPath).exists()) {
				new File(imgPath).mkdirs();
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return imgPath;
	}

	/**
	 * 遍历路径下的所有文件
	 * 
	 * @Title: getFileStr
	 * @Description: TODO()
	 * @param @param path
	 * @param @return
	 * @param @throws Exception 参数说明
	 * @return String 返回类型
	 * @throws
	 */
	public static List<Map<String, Object>> getFileStr(String path,
			String pUrl, String tUrl) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			File file = new File(path);
			if (!file.isDirectory()) {
				return result;
			}
			File[] files = file.listFiles();
			for (File f : files) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("picUrl", pUrl + f.getName());
				map.put("thumb", tUrl + f.getName());
				result.add(map);
			}
		} catch (Exception e) {
			result = new ArrayList<Map<String, Object>>();
		}
		return result;
	}

	public static String random(int length) {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < length; i++)
			result = result * 10 + array[i];
		
		return result+"";
	}

	public static void main(String[] args) throws Exception {
		/*
		 * Object d = 1.0; System.out.println(doubleTrans(d));
		 * System.out.println(getUUID());
		 */
	//	String str = "1";
		//System.out.println(getPath("userIcon"));
	}
}
