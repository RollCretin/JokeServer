package com.cretin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cretin.app.BaseResponce;
import com.cretin.app.LogConstant;
import com.cretin.intercepter.Login;
import com.cretin.po.vo.JokeContentVo;
import com.cretin.po.vo.JokeImageVo;
import com.cretin.po.vo.JokesQueryVo;
import com.cretin.service.JokesService;

/**
 * 
 * <p>
 * Title: JokesController
 * </p>
 * <p>
 * Description:jokes的controller
 * </p>
 * <p>
 * Company:www.cretin.com
 * </p>
 * 
 * @author Cretin
 * @date 2017年10月19日11:32:29
 * @version 1.0
 */
@Controller
// 为了对url进行分类管理 ，可以在这里定义根路径，最终访问url是根路径+子路径
// 比如：商品列表：/items/queryItems.action
@RequestMapping("/jokes")
public class JokesController {

	@Autowired
	private JokesService jokesService;

	/**
	 * 分页查询所有文字段子
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("/jokesList")
	public @ResponseBody BaseResponce<JokesQueryVo<JokeContentVo>> jokesList(Integer page,HttpSession session) {
		BaseResponce<JokesQueryVo<JokeContentVo>> responce = null;
		String userid = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		System.out.println("userid-----"+userid);
		System.out.println("sessionid------"+session.getId());
		if (page == null) {
			responce = new BaseResponce<JokesQueryVo<JokeContentVo>>();
			responce.setMessage("page不能为空");
			return responce;
		}
		try {
			responce = jokesService.findJokesList(page,userid);
		} catch (Exception e) {
			responce = new BaseResponce<JokesQueryVo<JokeContentVo>>();
			responce.setMessage("服务器异常");
		}
		return responce;
	}
	

	/**
	 * 分页查询推荐的文字段子
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("/jokesRecList")
	public @ResponseBody BaseResponce<JokesQueryVo<JokeContentVo>> jokesRecList(Integer page,HttpSession session) {
		BaseResponce<JokesQueryVo<JokeContentVo>> responce = null;
		String userid = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		if (page == null) {
			responce = new BaseResponce<JokesQueryVo<JokeContentVo>>();
			responce.setMessage("page不能为空");
			return responce;
		}
		try {
			responce = jokesService.findJokesRecList(page,userid);
		} catch (Exception e) {
			responce = new BaseResponce<JokesQueryVo<JokeContentVo>>();
			responce.setMessage("服务器异常");
		}
		return responce;
	}

	/**
	 * 分页查询所有图片段子
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("/jokesImgList")
	public @ResponseBody BaseResponce<JokesQueryVo<JokeImageVo>> jokesImgList(Integer page,HttpSession session) {
		BaseResponce<JokesQueryVo<JokeImageVo>> responce = null;
		String userid = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		if (page == null) {
			responce = new BaseResponce<JokesQueryVo<JokeImageVo>>();
			responce.setMessage("page不能为空");
			return responce;
		}
		try {
			responce = jokesService.findJokesImgList(page,userid);
		} catch (Exception e) {
			responce = new BaseResponce<JokesQueryVo<JokeImageVo>>();
			responce.setMessage("服务器异常");
		}
		return responce;
	}

	/**
	 * 分页查询推荐图片段子
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("/jokesImgRecList")
	public @ResponseBody BaseResponce<JokesQueryVo<JokeImageVo>> jokesImgRecList(Integer page,HttpSession session) {
		BaseResponce<JokesQueryVo<JokeImageVo>> responce = null;
		String userid = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		if (page == null) {
			responce = new BaseResponce<JokesQueryVo<JokeImageVo>>();
			responce.setMessage("page不能为空");
			return responce;
		}
		try {
			responce = jokesService.findJokesImgRecList(page,userid);
		} catch (Exception e) {
			responce = new BaseResponce<JokesQueryVo<JokeImageVo>>();
			responce.setMessage("服务器异常");
		}
		return responce;
	}

	/**
	 * 文字段子点赞
	 * 
	 * @param jokes_id
	 * @param session
	 * @return
	 */
	@Login
	@RequestMapping("/text/like")
	public @ResponseBody BaseResponce<?> jokesLike(String jokes_id, HttpSession session) {
		BaseResponce<?> baseResponce = new BaseResponce<>();
		String userid = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		try {
			int status = jokesService.like(jokes_id, userid,0);
			// 点赞成功返回1 点赞失败返回0 已点赞返回2
			if (status == 1) {
				baseResponce.setCode(1);
				baseResponce.setMessage("点赞成功");
			} else if (status == 2) {
				baseResponce.setCode(0);
				baseResponce.setMessage("您已经点过赞");
			} else {
				baseResponce.setCode(0);
				baseResponce.setMessage("点赞失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			baseResponce.setCode(0);
			baseResponce.setMessage("服务器异常");
		}
		return baseResponce;
	}

	/**
	 * 文字段子取消点赞
	 * @param jokes_id
	 * @param session
	 * @return
	 */
	@Login
	@RequestMapping("/text/unlike")
	public @ResponseBody BaseResponce<?> jokesUnlike(String jokes_id, HttpSession session) {
		BaseResponce<?> baseResponce = new BaseResponce<>();
		String userid = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		try {
			int status = jokesService.unlike(jokes_id, userid,0);
			// 点赞成功返回1 点赞失败返回0 已点赞返回2
			if (status == 1) {
				baseResponce.setCode(1);
				baseResponce.setMessage("取消点赞成功");
			} else if (status == 2) {
				baseResponce.setCode(0);
				baseResponce.setMessage("请先点赞再取消");
			} else {
				baseResponce.setCode(0);
				baseResponce.setMessage("取消点赞失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			baseResponce.setCode(0);
			baseResponce.setMessage("服务器异常");
		}
		return baseResponce;
	}
	
	/**
	 * 点赞
	 * 
	 * @param jokes_id
	 * @param session
	 * @return
	 */
	@Login
	@RequestMapping("/img/like")
	public @ResponseBody BaseResponce<?> jokesImgLike(String jokes_id, HttpSession session) {
		BaseResponce<?> baseResponce = new BaseResponce<>();
		String userid = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		try {
			int status = jokesService.like(jokes_id, userid,1);
			// 点赞成功返回1 点赞失败返回0 已点赞返回2
			if (status == 1) {
				baseResponce.setCode(1);
				baseResponce.setMessage("点赞成功");
			} else if (status == 2) {
				baseResponce.setCode(0);
				baseResponce.setMessage("您已经点过赞");
			} else {
				baseResponce.setCode(0);
				baseResponce.setMessage("点赞失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			baseResponce.setCode(0);
			baseResponce.setMessage("服务器异常");
		}
		return baseResponce;
	}
	
	/**
	 * 文字段子取消点赞
	 * @param jokes_id
	 * @param session
	 * @return
	 */
	@Login
	@RequestMapping("/img/unlike")
	public @ResponseBody BaseResponce<?> jokesImgUnlike(String jokes_id, HttpSession session) {
		BaseResponce<?> baseResponce = new BaseResponce<>();
		String userid = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		try {
			int status = jokesService.unlike(jokes_id, userid,1);
			// 点赞成功返回1 点赞失败返回0 已点赞返回2
			if (status == 1) {
				baseResponce.setCode(1);
				baseResponce.setMessage("取消点赞成功");
			} else if (status == 2) {
				baseResponce.setCode(0);
				baseResponce.setMessage("请先点赞再取消");
			} else {
				baseResponce.setCode(0);
				baseResponce.setMessage("取消点赞失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			baseResponce.setCode(0);
			baseResponce.setMessage("服务器异常");
		}
		return baseResponce;
	}
}
