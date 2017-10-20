/**
 * 
 */
package com.cretin.service;

import com.cretin.po.vo.CustomerUserVo;

/**
*<p>Title: UserService </p>
*<p>Description: </p>
*<p>Company: www.cretin.com </p> 
* @author cretin
* @date 2017年10月19日
*/
public interface UserService {
	/**
	 * 用户登录 根据用户名和密码获取用户信息的信息
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public CustomerUserVo login(String username,String password) throws Exception;
}
