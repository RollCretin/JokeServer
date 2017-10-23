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
import com.cretin.po.vo.CustomerUserVo;
import com.cretin.po.vo.JokesQueryVo;
import com.cretin.service.JokesService;
import com.cretin.service.UserService;
import com.cretin.service.impl.JokesServiceImpl;
import com.cretin.utils.MD5Utils;
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
	public @ResponseBody BaseResponce<CustomerUserVo> jokesList(@RequestParam("username") String username,
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
		try {
			String code = StringUtils.getCode();
			session.setAttribute("verification_code", code);
			SendSmsResponse codeEntity = userService.sendCode(phone, code);
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
			@RequestParam("password") String password, @RequestParam("code") String code, HttpSession session,HttpServletRequest request) {
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
			User user =  new User();
			user.setUserId(UUIDUtils.getUuid());
			user.setAge(0);
			user.setAvatar("/img/user/default_avatar.jpg");
			user.setNickname("用户_"+StringUtils.getDefaultNickName());
			user.setPassword(MD5Utils.EncoderByMd5(password));
			user.setUsername(phone);
			userService.addUserAccount(user);
		} catch (Exception e) {
			return new BaseResponce<>(0, "服务器异常");
		}
		return null;
	}
}
