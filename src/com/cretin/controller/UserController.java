package com.cretin.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
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

import com.cretin.app.BaseResponce;
import com.cretin.app.LogConstant;
import com.cretin.po.JokeContent;
import com.cretin.po.JokeImg;
import com.cretin.po.vo.CustomerUserVo;
import com.cretin.po.vo.JokesQueryVo;
import com.cretin.service.JokesService;
import com.cretin.service.UserService;
import com.cretin.service.impl.JokesServiceImpl;
import com.cretin.utils.StringUtils;

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
}
