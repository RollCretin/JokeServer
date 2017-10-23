package com.cretin.mapper;

/**
 * <p>
 * Title: VerificationCodeCustomMapper
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.cretin.com
 * </p>
 * 
 * @author cretin
 * @date 2017年10月23日
 */
public interface VerificationCodeCustomMapper {
	/**
	 * 查询一分钟内phone发送的验证码的条数 限流
	 * @param phone
	 * @return
	 */
	int findSmsWithinOneMinute(String phone);
}
