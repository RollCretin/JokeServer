/**
 * 
 */
package com.cretin.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
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
	 * @param phone
	 * @param code
	 * @return
	 */
	public SendSmsResponse sendCode(String phone,String code) throws Exception;
}
