package com.cretin.controller;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cretin.app.BaseResponce;
import com.cretin.app.LogConstant;
import com.cretin.intercepter.Login;
import com.cretin.po.JokeImg;
import com.cretin.po.vo.JokeContentVo;
import com.cretin.po.vo.JokeImageVo;
import com.cretin.po.vo.JokesQueryVo;
import com.cretin.service.JokesService;
import com.cretin.utils.StringUtils;
import com.cretin.utils.UUIDUtils;

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
	public @ResponseBody BaseResponce<JokesQueryVo<JokeContentVo>> jokesList(Integer page, HttpSession session) {
		BaseResponce<JokesQueryVo<JokeContentVo>> responce = null;
		String userid = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		if (page == null) {
			responce = new BaseResponce<JokesQueryVo<JokeContentVo>>();
			responce.setMessage("page不能为空");
			return responce;
		}
		try {
			responce = jokesService.findJokesList(page, userid);
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
	public @ResponseBody BaseResponce<JokesQueryVo<JokeContentVo>> jokesRecList(Integer page, HttpSession session) {
		BaseResponce<JokesQueryVo<JokeContentVo>> responce = null;
		String userid = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		if (page == null) {
			responce = new BaseResponce<JokesQueryVo<JokeContentVo>>();
			responce.setMessage("page不能为空");
			return responce;
		}
		try {
			responce = jokesService.findJokesRecList(page, userid);
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
	public @ResponseBody BaseResponce<JokesQueryVo<JokeImageVo>> jokesImgList(Integer page, HttpSession session) {
		BaseResponce<JokesQueryVo<JokeImageVo>> responce = null;
		String userid = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		if (page == null) {
			responce = new BaseResponce<JokesQueryVo<JokeImageVo>>();
			responce.setMessage("page不能为空");
			return responce;
		}
		try {
			responce = jokesService.findJokesImgList(page, userid);
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
	public @ResponseBody BaseResponce<JokesQueryVo<JokeImageVo>> jokesImgRecList(Integer page, HttpSession session) {
		BaseResponce<JokesQueryVo<JokeImageVo>> responce = null;
		String userid = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		if (page == null) {
			responce = new BaseResponce<JokesQueryVo<JokeImageVo>>();
			responce.setMessage("page不能为空");
			return responce;
		}
		try {
			responce = jokesService.findJokesImgRecList(page, userid);
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
			int status = jokesService.like(jokes_id, userid, 0);
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
	 * 
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
			int status = jokesService.unlike(jokes_id, userid, 0);
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
			int status = jokesService.like(jokes_id, userid, 1);
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
	 * 
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
			int status = jokesService.unlike(jokes_id, userid, 1);
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

	@Login
	@RequestMapping("/img/addJoke")
	public @ResponseBody BaseResponce<?> addJoke(@RequestParam("content") String content, MultipartFile image,
			HttpSession session) throws Exception {
		if(StringUtils.isEmpty(content))
			return new BaseResponce<>(0,"标题不能为空");
		if(image==null)
			return new BaseResponce<>(0,"请上传图片");
		// 组装数据
		JokeImg jokeImg = new JokeImg();
		jokeImg.setJokeId(UUIDUtils.getUuid());
		String userId = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		jokeImg.setUserId(userId);
		jokeImg.setContent(content);
		// 原始名称
		String originalFilename = image.getOriginalFilename();
		// 上传图片
		if (image != null && originalFilename != null && originalFilename.length() > 0) {
			// 存储图片的物理路径
			String pic_path = LogConstant.PIC_DEIRECTORY;
			// 新的图片名称
			Calendar cal = Calendar.getInstance();
	        int year = cal.get(Calendar.YEAR);//获取年份
	        int month=cal.get(Calendar.MONTH);//获取月份 
	        int day=cal.get(Calendar.DATE);//获取日 
			String newFileName = "joke/"+year+"/"+month+"/"+day+"/"+UUIDUtils.getUuid() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 新图片
			File newFile = new File(pic_path + newFileName);
			if(!newFile.exists())
				newFile.mkdirs();
			// 将内存中的数据写入磁盘
			image.transferTo(newFile);
			jokeImg.setImageUrl("img/"+newFileName);
		}
		int state = jokesService.addImgJoke(jokeImg);
		if (state != 0) {
			return new BaseResponce<>(1, "提交成功");
		} else {
			return new BaseResponce<>(0, "提交失败");
		}
	}
}
