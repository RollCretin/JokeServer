/**
 * 
 */
package com.cretin.utils;

import java.text.SimpleDateFormat;
import java.util.Random;

import org.apache.taglibs.standard.lang.jstl.BooleanLiteral;
import org.junit.Test;

import com.sun.javafx.image.IntToIntPixelConverter;

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
	private static int COMMENT_LENGTH = 6;
	private static String strRes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

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
	 * 
	 * @param time
	 * @return
	 */
	public static String getFormatTime(long time) {
		return simpleDateFormat.format(time);
	}

	/**
	 * 获取一个长度为默认值6位的验证码
	 * 
	 * @return
	 */
	public static String getCode() {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < COMMENT_LENGTH; i++) {
			buffer.append(random.nextInt(10));
		}
		return buffer.toString();
	}

	/**
	 * 获取一个长度为默认值length位的验证码
	 * 
	 * @param length
	 *            验证码长度
	 * @return
	 */
	public static String getCode(int length) {
		if (length <= 0)
			length = COMMENT_LENGTH;
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			buffer.append(random.nextInt(10));
		}
		return buffer.toString();
	}

	/**
	 * 获取长度为默认值的昵称
	 * 
	 * @return
	 */
	public static String getDefaultNickName() {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < COMMENT_LENGTH; i++) {
			buffer.append(strRes.charAt(random.nextInt(26)));
		}
		return buffer.toString();
	}

	/**
	 * 获取长度为默认值length的昵称
	 * 
	 * @return
	 */
	public static String getDefaultNickName(int length) {
		if (length <= 0)
			length = COMMENT_LENGTH;
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			buffer.append(strRes.charAt(random.nextInt(26)));
		}
		return buffer.toString();
	}
}
