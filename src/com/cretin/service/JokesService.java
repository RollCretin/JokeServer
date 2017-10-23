package com.cretin.service;

import java.util.List;

import com.cretin.app.BaseResponce;
import com.cretin.po.JokeContent;
import com.cretin.po.JokeImg;
import com.cretin.po.vo.JokeContentVo;
import com.cretin.po.vo.JokeImageVo;
import com.cretin.po.vo.JokesQueryVo;

/**
 * 
 * <p>
 * Title: ItemsService
 * </p>
 * <p>
 * Description:商品管理service
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 传智.燕青
 * @date 2015-4-13下午3:48:09
 * @version 1.0
 */
public interface JokesService {

	/**
	 * 文字段子分页查询
	 * @param userid 用户查询点赞数
	 * 
	 * @param itemsQueryVo
	 * @return
	 * @throws Exception
	 */
	public BaseResponce<JokesQueryVo<JokeContentVo>> findJokesList(Integer page, String userid) throws Exception;
	

	/**
	 * 推荐文字段子分页查询
	 * @param userid 用户查询点赞数
	 * @param itemsQueryVo
	 * @return
	 * @throws Exception
	 */
	public BaseResponce<JokesQueryVo<JokeContentVo>> findJokesRecList(Integer page, String userid) throws Exception;

	/**
	 * 图片段子分页查询
	 * @param userid 用户查询点赞数
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public BaseResponce<JokesQueryVo<JokeImageVo>> findJokesImgList(Integer page, String userid) throws Exception;
	
	/**
	 * 推荐图片段子分页查询
	 * @param userid 用户查询点赞数
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public BaseResponce<JokesQueryVo<JokeImageVo>> findJokesImgRecList(Integer page, String userid) throws Exception;

	/**
	 * 给指定的jokes点赞 点赞成功返回1 点赞失败返回0 已点赞返回2
	 * 
	 * @param jokes_id
	 * @param userid
	 * @param type
	 *            type = 0 点赞文字段子 1 点赞图片的段子
	 * @return
	 */
	public int like(String jokes_id, String userid, int type) throws Exception;

	/**
	 * 取消点赞 取消点赞成功返回1 取消点赞失败返回0 未已点赞返回2
	 * 
	 * @param jokes_id
	 * @param userid
	 * @param type
	 *            type = 0 点赞文字段子 1 点赞图片的段子
	 * @return
	 */
	public int unlike(String jokes_id, String userid, int type);

	// //根据id查询商品信息
	// /**
	// *
	// * <p>Title: findItemsById</p>
	// * <p>Description: </p>
	// * @param id 查询商品的id
	// * @return
	// * @throws Exception
	// */
	// public ItemsCustom findItemsById(Integer id) throws Exception;
	//
	// //修改商品信息
	// /**
	// *
	// * <p>Title: updateItems</p>
	// * <p>Description: </p>
	// * @param id 修改商品的id
	// * @param itemsCustom 修改的商品信息
	// * @throws Exception
	// */
	// public void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;

}
