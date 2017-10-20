/**
 * 
 */
package com.cretin.utils;

import java.text.SimpleDateFormat;

import org.apache.taglibs.standard.lang.jstl.BooleanLiteral;

/**
 * <p>
 * Title: StringUtils
 * </p>
 * <p>
 * Description: 对字符串的处理类
 * </p>
 * <p>
 * Company: www.cretin.com
 * </p>
 * 
 * @author cretin
 * @date 2017年10月19日
 */
public class StringUtils {
	private static SimpleDateFormat simpleDateFormat = null;

	static {
		simpleDateFormat = new SimpleDateFormat("YY/MM/DD HH:mm");
	}

	/**
	 * 空字符判断
	 * 
	 * @param text
	 * @return
	 */
	public static Boolean isEmpty(String text) {
		if (text == null)
			return true;
		if ("".equals(text))
			return true;
		return false;
	}

	/**
	 * 格式化时间
	 * @param time
	 * @return
	 */
	public static String getFormatTime(long time) {
		return simpleDateFormat.format(time);
	}
}
