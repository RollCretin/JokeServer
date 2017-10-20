package com.cretin.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cretin.app.BaseResponce;
import com.cretin.mapper.UserMapper;
import com.cretin.po.User;
import com.cretin.po.UserExample;
import com.cretin.po.UserExample.Criteria;
import com.cretin.po.vo.CustomerUserVo;
import com.cretin.service.UserService;
import com.cretin.utils.MD5Utils;

/**
 * <p>
 * Title: UserServiceImpl
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
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper customerUserMapper;

	@Override
	public CustomerUserVo login(String username, String password) throws Exception {
		CustomerUserVo customerUserVo = null;
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(MD5Utils.EncoderByMd5(password));
		List<User> listUser = customerUserMapper.selectByExample(example);
		if (listUser != null && !listUser.isEmpty()) {
			User customerUser = listUser.get(0);
			customerUserVo = new CustomerUserVo();
			BeanUtils.copyProperties(customerUser, customerUserVo);
		}
		return customerUserVo;
	}

}
