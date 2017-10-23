/**
 * 
 */
package com.cretin.service;

import java.util.List;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.cretin.po.User;
import com.cretin.po.VerificationCode;
import com.cretin.po.vo.CustomerUserVo;

/**
 * <p>
 * Title: UserService
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.cretin.com
 * </p>
 * 
 * @author cretin
 * @date 2017年10月19日
 */
public interface UserService {
	/**
	 * 用户登录 根据用户名和密码获取用户信息的信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public CustomerUserVo login(String username, String password) throws Exception;

	/**
	 * 给phone发送验证码code
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	public SendSmsResponse sendCode(String phone, String code) throws Exception;

	/**
	 * 添加用户信息 注册 1 成功 0 失败
	 * 
	 * @param user
	 * @return
	 */
	public int addUserAccount(User user);

	/**
	 * 根据手机号查询用户
	 * 
	 * @param phone
	 * @return
	 */
	public List<User> findUserByPhone(String phone);

	/**
	 * 添加验证短信的信息到数据库做统计
	 * @param verificationCode 
	 */
	public void addUserVerification(VerificationCode verificationCode);

	/**
	 * @param phone
	 * @return
	 */
	public int findSmsWithinOneMinute(String phone);
}
