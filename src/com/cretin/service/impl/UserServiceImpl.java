package com.cretin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.cretin.app.BaseResponce;
import com.cretin.mapper.UserMapper;
import com.cretin.mapper.VerificationCodeCustomMapper;
import com.cretin.mapper.VerificationCodeMapper;
import com.cretin.po.User;
import com.cretin.po.UserExample;
import com.cretin.po.UserExample.Criteria;
import com.cretin.po.VerificationCode;
import com.cretin.po.vo.CustomerUserVo;
import com.cretin.service.UserService;
import com.cretin.utils.MD5Utils;
import com.sun.java_cup.internal.runtime.virtual_parse_stack;

import sms.SmsFactory;

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
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper customerUserMapper;
	@Autowired
	private VerificationCodeMapper verificationCodeMapper;
	@Autowired
	private VerificationCodeCustomMapper verificationCodeCustomMapper;

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

	@Override
	public SendSmsResponse sendCode(String phone, String code) throws Exception {
		// 生成一个验证码
		SendSmsResponse reslut = SmsFactory.sendSms(phone, code);
		logger.debug("短信发送状态：" + reslut);
		return reslut;
	}

	@Override
	public int addUserAccount(User user) {
		int row = customerUserMapper.insert(user);
		if (row > 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public List<User> findUserByPhone(String phone) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andTelephoneEqualTo(phone);
		List<User> list = customerUserMapper.selectByExample(example);
		return list == null ? new ArrayList<>() : list;
	}

	@Override
	public void addUserVerification(VerificationCode verificationCode) {
		verificationCodeMapper.insert(verificationCode);
	}

	@Override
	public int findSmsWithinOneMinute(String phone) {
		return verificationCodeCustomMapper.findSmsWithinOneMinute(phone);
	}
}
