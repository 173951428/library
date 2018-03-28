package com.library.system.utils;

/**
 * 字符串工具类,继承org.apache.commons.lang3.StringUtils类
 * 
 * @Description: TODO
 * @Package cn.com.spdbccc.zcef.support.utils.ZcefStringUtils.java
 * @author zuohongda
 * @Company Newtouch
 * @since 2016年3月2日 下午3:37:04
 * @version V0.1
 */
public class ZcefStringUtils extends org.apache.commons.lang3.StringUtils {
	/**
	 * 判断对象toString是否为空或者为空字符串
	 * 
	 * @param obj
	 * @return true or false
	 */
	public static boolean isBlank(Object obj) {
		if (obj == null) {
			return true;
		}
		return isBlank(obj.toString());
	}

	/**
	 * 判断对象toString是否不为空
	 * 
	 * @param obj
	 * @return true or false
	 */
	public static boolean isNotBlank(Object obj) {
		return !isBlank(obj);
	}

	/**
	 * 将对象转换为字符串
	 * 
	 * @param o
	 *            对象
	 * @return 字符串
	 */
	public static String objToStr(Object o) {
		String str = (o == null) ? "" : o.toString();
		return str.trim();
	}

	/**
	 * 格式化路径 将分隔符设定为'/'
	 * 
	 * @param path
	 * @return
	 */
	public static String formatPath(String path) {
		String temp = "";
		if (path != null) {
			String reg0 = "\\\\+";
			String reg = "\\\\+|/+";
			temp = path.trim().replaceAll(reg0, "/");
			temp = temp.replaceAll(reg, "/");
			if (temp.length() > 1 && temp.endsWith("/")) {
				temp = temp.substring(0, temp.length() - 1);
			}
		}

		// temp = temp.replace('/', File.separatorChar);
		return temp;
	}
}