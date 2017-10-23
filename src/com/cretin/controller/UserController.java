package com.cretin.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.cretin.app.BaseResponce;
import com.cretin.app.LogConstant;
import com.cretin.mapper.UserMapper;
import com.cretin.po.JokeContent;
import com.cretin.po.JokeImg;
import com.cretin.po.User;
import com.cretin.po.VerificationCode;
import com.cretin.po.vo.CustomerUserVo;
import com.cretin.po.vo.JokesQueryVo;
import com.cretin.service.JokesService;
import com.cretin.service.UserService;
import com.cretin.service.impl.JokesServiceImpl;
import com.cretin.utils.MD5Utils;
import com.cretin.utils.PhoneFormatCheckUtils;
import com.cretin.utils.StringUtils;
import com.cretin.utils.UUIDUtils;

/**
 * 
 * <p>
 * Title: UserController
 * </p>
 * <p>
 * Description:用户相关的Controller
 * </p>
 * <p>
 * Company: www.cretin.com
 * </p>
 * 
 * @author Cretin
 * @date 2017年10月19日11:32:29
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 用户登录
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("/login")
	public @ResponseBody BaseResponce<CustomerUserVo> login(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		// 先移除登录状态
		session.removeAttribute(LogConstant.LOGIN_USER);
		session.removeAttribute(LogConstant.LOGIN_USERID);
		BaseResponce<CustomerUserVo> responce = null;
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			responce = new BaseResponce<CustomerUserVo>();
			responce.setMessage("用户名或密码不能为空");
			return responce;
		}
		try {
			CustomerUserVo user = userService.login(username, password);
			if (user == null) {
				responce = new BaseResponce<CustomerUserVo>();
				responce.setMessage("用户名或密码错误");
				return responce;
			} else {
				session.setAttribute(LogConstant.LOGIN_USER, user);
				session.setAttribute(LogConstant.LOGIN_USERID, user.getUserId());
				responce = new BaseResponce<CustomerUserVo>();
				responce.setCode(1);
				responce.setMessage("登录成功");
				responce.setData(user);
				return responce;
			}
		} catch (Exception e) {
			responce = new BaseResponce<CustomerUserVo>();
			responce.setMessage("服务器异常");
			return responce;
		}
	}
	
	/**
	 * 用户登录
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("/logout")
	public @ResponseBody BaseResponce<?> logout(HttpSession session) {
		// 先移除登录状态
		session.removeAttribute(LogConstant.LOGIN_USER);
		session.removeAttribute(LogConstant.LOGIN_USERID);
		session.invalidate();
		return new BaseResponce<>(1,"退出登录成功");
	}

	/**
	 * 用户注册 发送验证码
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("/reg/sendcode")
	public @ResponseBody BaseResponce<?> sendcode(@RequestParam("phone") String phone, HttpSession session) {
		if (StringUtils.isEmpty(phone)) {
			return new BaseResponce<>(0, "手机号不能为空");
		}
		if (!PhoneFormatCheckUtils.isChinaPhoneLegal(phone)) {
			return new BaseResponce<>(0, "手机号格式不正确");
		}
		try {
			// 首先检测该手机号是否已经注册过了
			if (((List<User>) userService.findUserByPhone(phone)).size() > 0) {
				// 改手机号已经被注册
				return new BaseResponce<>(0, "该手机号已被注册，请直接登录");
			}
			// 获取最近一分钟内phone发送的验证码的条数
			int nums = userService.findSmsWithinOneMinute(phone);
			if (nums != 0) {
				return new BaseResponce<>(0, "验证码发送太快，请稍后再试");
			}
			String code = StringUtils.getCode();
			session.setAttribute("verification_code", code);
			//阿里那边要收费 一般不用
			SendSmsResponse codeEntity = userService.sendCode(phone, code);
//			SendSmsResponse codeEntity = new SendSmsResponse();//模拟发送短信
			codeEntity.setCode("OK");
			VerificationCode verificationCode = new VerificationCode();
			verificationCode.setSmsId(UUIDUtils.getUuid());
			verificationCode.setSmscontent("验证码" + code + "，您正进行掌中乐的注册操作，打死不告诉别人！");
			verificationCode.setSmstel(phone);
			verificationCode.setSmscode(code);
			if ("OK".equals(codeEntity.getCode())) {
				// 短信发送成功
				verificationCode.setSmsstatus(1);
			} else {
				verificationCode.setSmsstatus(0);
			}
			// 插入数据到数据库 不管是否成功
			userService.addUserVerification(verificationCode);

			if ("OK".equals(codeEntity.getCode())) {
				// 短信发送成功
				return new BaseResponce<>(1, "验证码发送成功");
			} else if ("isv.OUT_OF_SERVICE".equals(codeEntity.getCode())) {
				return new BaseResponce<>(0, "业务停机");
			} else if ("isv.ACCOUNT_ABNORMAL".equals(codeEntity.getCode())) {
				return new BaseResponce<>(0, "账户异常");
			} else if ("isv.MOBILE_NUMBER_ILLEGAL".equals(codeEntity.getCode())) {
				return new BaseResponce<>(0, "非法手机号");
			} else if ("isv.MOBILE_COUNT_OVER_LIMIT".equals(codeEntity.getCode())) {
				return new BaseResponce<>(0, "手机号码数量超过限制");
			} else if ("isv.BUSINESS_LIMIT_CONTROL".equals(codeEntity.getCode())) {
				return new BaseResponce<>(0, "发送频率太快");
			} else if ("isv.PARAM_LENGTH_LIMIT".equals(codeEntity.getCode())) {
				return new BaseResponce<>(0, "参数超出长度限制");
			} else {
				return new BaseResponce<>(0, "服务器内部错误");
			}
		} catch (Exception e) {
			return new BaseResponce<>(0, "服务器异常");
		}
	}

	/**
	 * 用户注册 发送验证码
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("/reg/register")
	public @ResponseBody BaseResponce<?> registerUser(@RequestParam("phone") String phone,
			@RequestParam("password") String password, @RequestParam("code") String code, HttpSession session,
			HttpServletRequest request) {
		if (StringUtils.isEmpty(phone)) {
			return new BaseResponce<>(0, "手机号不能为空");
		} else if (StringUtils.isEmpty(password)) {
			return new BaseResponce<>(0, "密码不能为空");
		} else if (StringUtils.isEmpty(code)) {
			return new BaseResponce<>(0, "验证码不能为空");
		}
		try {
			String valiCode = (String) session.getAttribute("verification_code");
			if (!StringUtils.isEmpty(valiCode)) {
				if (!valiCode.equals(code)) {
					return new BaseResponce<>(0, "验证码错误");
				}
			} else {
				return new BaseResponce<>(0, "验证码错误");
			}
			// 移除session中的数据
			session.removeAttribute("verification_code");
			// 验证码正确 开始注册
			User user = new User();
			user.setUserId(UUIDUtils.getUuid());
			user.setAge(0);
			user.setAvatar("/img/user/default_avatar.jpg");
			user.setNickname("用户_" + StringUtils.getDefaultNickName());
			user.setPassword(MD5Utils.EncoderByMd5(password));
			user.setUsername(phone);
			user.setSex(0);
			user.setTelephone(phone);
			int result = userService.addUserAccount(user);
			if (result != 0) {
				return new BaseResponce<>(1, "注册成功");
			} else {
				return new BaseResponce<>(0, "注册失败");
			}
		} catch (Exception e) {
			return new BaseResponce<>(0, "服务器异常");
		}
	}
	
	
}
