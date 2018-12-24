package com.atguigu.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class Utils {

	/**
	 * 降低耦合 把请求的参数注入到javaBean对象中
	 */
	public static <T> T copyParamToBean(T bean, Map src) {
		try {
			System.out.println("注入值之前：" + bean);
			// 将map中的值，注入到javaBean对象的属性中
			BeanUtils.populate(bean, src);
			System.out.println("注入值之后：" + bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * 将字符串转换成为int
	 * 
	 * @param intStr
	 * @param defaultValue
	 * @return
	 */
	public static int parseInt(String intStr, int defaultValue) {
		try {
			return Integer.parseInt(intStr);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return defaultValue;
	}

	/**
	 * 
	 * @param parameter
	 * @param defaultValue
	 * @return
	 */
	public static double parseDouble(String doubleStr, double defaultValue) {
		try {
			return Double.parseDouble(doubleStr);
		} catch (Exception e) {
		}
		return defaultValue;
	}

}
