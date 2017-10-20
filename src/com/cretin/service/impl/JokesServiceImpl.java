package com.cretin.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cretin.app.BaseResponce;
import com.cretin.mapper.JokeContentCustomMapper;
import com.cretin.mapper.JokeContentMapper;
import com.cretin.mapper.JokeImgMapper;
import com.cretin.mapper.JokesContentLikeMapper;
import com.cretin.mapper.JokesImgLikeMapper;
import com.cretin.po.JokeContent;
import com.cretin.po.JokeContentExample;
import com.cretin.po.JokeContentExample.Criteria;
import com.cretin.po.vo.JokeContentVo;
import com.cretin.po.vo.JokeImageVo;
import com.cretin.po.vo.JokesQueryVo;
import com.cretin.po.JokeImg;
import com.cretin.po.JokeImgExample;
import com.cretin.po.JokesContentLike;
import com.cretin.po.JokesContentLikeExample;
import com.cretin.po.JokesImgLike;
import com.cretin.po.JokesImgLikeExample;
import com.cretin.service.JokesService;
import com.cretin.utils.StringUtils;
import com.cretin.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.ws.api.message.saaj.SAAJFactory;

/**
 * 
 * <p>
 * Title: ItemsServiceImpl
 * </p>
 * <p>
 * Description: 商品管理
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 传智.燕青
 * @date 2015-4-13下午3:49:54
 * @version 1.0
 */
public class JokesServiceImpl implements JokesService {
	@Autowired
	private JokeContentMapper jokeContentMapper;
	@Autowired
	private JokeImgMapper jokeImgMapper;
	@Autowired
	private JokesContentLikeMapper jokesContentLikeMapper;
	@Autowired
	private JokesImgLikeMapper jokesImgLikeMapper;
	@Autowired
	private JokeContentCustomMapper jokeContentCustomMapper;

	@Override
	public BaseResponce<JokesQueryVo<JokeContentVo>> findJokesList(Integer page) throws Exception {
		BaseResponce<JokesQueryVo<JokeContentVo>> baseResponce = new BaseResponce<JokesQueryVo<JokeContentVo>>();
		JokesQueryVo<JokeContentVo> jokesQueryVo = new JokesQueryVo<JokeContentVo>();
		// 获取第1页，10条内容，默认查询总数count
		// 设置每一页的记录数
		int limit = 10;
		jokesQueryVo.setLimit(limit);
		PageHelper.startPage(page, limit);
		List<JokeContentVo> jokesListVo = jokeContentCustomMapper.findJokesList();
		for (JokeContentVo jokeContent : jokesListVo) {
			// 时间规则 小于一分钟显示多少秒 小于一个小时的显示分钟 小于一天的显示小时 小于5天的显示天 大于5天的展示具体时间
			long time = jokeContent.getUpdateTime().getTime();
			String timeResult = "";
			// 计算时间
			long leftTime = System.currentTimeMillis() - time;
			int miniute = (int) (leftTime / 1000 / 60);
			if (leftTime / 1000 < 60) {
				timeResult = (leftTime / 1000) + "秒前";
			} else if (miniute < 60) {
				timeResult = miniute + "分钟前";
			} else if (miniute < 60 * 60 * 24) {
				timeResult = miniute / 60 + "小时前";
			} else if (miniute < 60 * 60 * 24 * 5) {
				timeResult = miniute / 60 / 24 + "天前";
			} else {
				timeResult = StringUtils.getFormatTime(time);
			}
			jokeContent.setShowTime(timeResult);
		}
		jokesQueryVo.setJokesList(jokesListVo);
		// 设置返回的总记录数
		PageInfo<JokeContentVo> pageInfo = new PageInfo<>(jokesListVo);
		// 设置当前页数
		jokesQueryVo.setPage(page);
		// 设置总记录数
		int totalCount = 0;
		totalCount = (int) pageInfo.getTotal();
		jokesQueryVo.setTotalCount(totalCount);
		// 设置总页面数
		int totalPage = 0;
		totalPage = totalCount % limit == 0 ? (totalCount / limit) : (totalCount / limit + 1);
		jokesQueryVo.setTotalPage(totalPage);
		baseResponce.setCode(1);
		baseResponce.setData(jokesQueryVo);
		return baseResponce;
	}

	@Override
	public BaseResponce<JokesQueryVo<JokeImageVo>> findJokesImgList(Integer page) throws Exception {
		BaseResponce<JokesQueryVo<JokeImageVo>> baseResponce = new BaseResponce<JokesQueryVo<JokeImageVo>>();
		JokesQueryVo<JokeImageVo> jokesQueryVo = new JokesQueryVo<JokeImageVo>();
		// 获取第1页，10条内容，默认查询总数count
		// 设置每一页的记录数
		int limit = 10;
		jokesQueryVo.setLimit(limit);
		PageHelper.startPage(page, limit);
		List<JokeImageVo> jokesListVo = jokeContentCustomMapper.findImgJokesList();
		for (JokeImageVo jokeContent : jokesListVo) {
			// 时间规则 小于一分钟显示多少秒 小于一个小时的显示分钟 小于一天的显示小时 小于5天的显示天 大于5天的展示具体时间
			long time = jokeContent.getUpdateTime().getTime();
			String timeResult = "";
			// 计算时间
			long leftTime = System.currentTimeMillis() - time;
			int miniute = (int) (leftTime / 1000 / 60);
			if (leftTime / 1000 < 60) {
				timeResult = (leftTime / 1000) + "秒前";
			} else if (miniute < 60) {
				timeResult = miniute + "分钟前";
			} else if (miniute < 60 * 60 * 24) {
				timeResult = miniute / 60 + "小时前";
			} else if (miniute < 60 * 60 * 24 * 5) {
				timeResult = miniute / 60 / 24 + "天前";
			} else {
				timeResult = StringUtils.getFormatTime(time);
			}
			jokeContent.setShowTime(timeResult);
		}
		jokesQueryVo.setJokesList(jokesListVo);
		// 设置返回的总记录数
		PageInfo<JokeImageVo> pageInfo = new PageInfo<>(jokesListVo);
		// 设置当前页数
		jokesQueryVo.setPage(page);
		// 设置总记录数
		int totalCount = 0;
		totalCount = (int) pageInfo.getTotal();
		jokesQueryVo.setTotalCount(totalCount);
		// 设置总页面数
		int totalPage = 0;
		totalPage = totalCount % limit == 0 ? (totalCount / limit) : (totalCount / limit + 1);
		jokesQueryVo.setTotalPage(totalPage);
		baseResponce.setCode(1);
		baseResponce.setData(jokesQueryVo);
		return baseResponce;
	}

	/**
	 * 点赞 点赞成功返回1 点赞失败返回0 已点赞返回2 type = 0 点赞文字段子 1 点赞图片的段子
	 */
	@Override
	public int like(String jokes_id, String userid, int type) throws Exception {
		if (type == 0) {
			// 文字段子
			// 先查询一下当前用户有没有点过赞
			JokesContentLikeExample example = new JokesContentLikeExample();
			JokesContentLikeExample.Criteria criteria = example.createCriteria();
			criteria.andJoketextIdEqualTo(jokes_id);
			criteria.andUserIdEqualTo(userid);
			List<JokesContentLike> list = jokesContentLikeMapper.selectByExample(example);
			// 如果没有点过赞就点赞 否则返回
			if (list != null && !list.isEmpty()) {
				// 点过赞了
				return 2;
			} else {
				// 没有点赞 点赞
				JokesContentLike jokesLike = new JokesContentLike();
				jokesLike.setType(0);
				jokesLike.setJoketextId(jokes_id);
				jokesLike.setUserId(userid);
				jokesLike.setLikeId(UUIDUtils.getUuid());
				jokesLike.setCreateTime(new Date());
				int row = jokesContentLikeMapper.insert(jokesLike);
				if (row != 0) {
					// 点赞成功
					return 1;
				} else {
					// 点赞失败
					return 0;
				}
			}
		} else {
			// 图片段子
			// 先查询一下当前用户有没有点过赞
			JokesImgLikeExample example = new JokesImgLikeExample();
			JokesImgLikeExample.Criteria criteria = example.createCriteria();
			criteria.andJokeimgIdEqualTo(jokes_id);
			criteria.andUserIdEqualTo(userid);
			List<JokesImgLike> list = jokesImgLikeMapper.selectByExample(example);
			// 如果没有点过赞就点赞 否则返回
			if (list != null && !list.isEmpty()) {
				// 点过赞了
				return 2;
			} else {
				// 没有点赞 点赞
				JokesImgLike jokesLike = new JokesImgLike();
				// 图片段子
				jokesLike.setType(1);
				jokesLike.setJokeimgId(jokes_id);
				jokesLike.setUserId(userid);
				jokesLike.setLikeId(UUIDUtils.getUuid());
				jokesLike.setCreateTime(new Date());
				int row = jokesImgLikeMapper.insert(jokesLike);
				if (row != 0) {
					// 点赞成功
					return 1;
				} else {
					// 点赞失败
					return 0;
				}
			}
		}
	}

	/**
	 * 取消点赞成功返回1 取消点赞失败返回0 未已点赞返回2 type = 0 点赞文字段子 1 点赞图片的段子
	 */
	@Override
	public int unlike(String jokes_id, String userid, int type) {
		if (type == 0) {
			// 文字段子
			// 先查询一下当前用户有没有点过赞
			JokesContentLikeExample example = new JokesContentLikeExample();
			JokesContentLikeExample.Criteria criteria = example.createCriteria();
			criteria.andJoketextIdEqualTo(jokes_id);
			criteria.andUserIdEqualTo(userid);
			List<JokesContentLike> list = jokesContentLikeMapper.selectByExample(example);
			// 如果没有点过赞就点赞 否则返回
			if (list != null && !list.isEmpty()) {
				// 点过赞了 取消点赞
				JokesContentLike jokesLike = list.get(0);
				int row = jokesContentLikeMapper.deleteByPrimaryKey(jokesLike.getLikeId());
				if (row != 0) {
					// 取消点赞成功
					return 1;
				} else {
					// 取消点赞失败
					return 0;
				}
			} else {
				// 没有点赞 返回
				return 2;
			}
		} else {
			// 图片段子
			// 先查询一下当前用户有没有点过赞
			JokesImgLikeExample example = new JokesImgLikeExample();
			JokesImgLikeExample.Criteria criteria = example.createCriteria();
			criteria.andJokeimgIdEqualTo(jokes_id);
			criteria.andUserIdEqualTo(userid);
			List<JokesImgLike> list = jokesImgLikeMapper.selectByExample(example);
			// 如果没有点过赞就点赞 否则返回
			if (list != null && !list.isEmpty()) {
				// 点过赞了 取消点赞
				JokesImgLike jokesLike = list.get(0);
				int row = jokesContentLikeMapper.deleteByPrimaryKey(jokesLike.getLikeId());
				if (row != 0) {
					// 取消点赞成功
					return 1;
				} else {
					// 取消点赞失败
					return 0;
				}
			} else {
				// 没有点赞 返回
				return 2;
			}
		}
	}
}
