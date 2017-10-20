package com.cretin.mapper;

import java.util.List;

import com.cretin.po.vo.JokeContentVo;
import com.cretin.po.vo.JokeImageVo;

/**
 * <p>
 * Title: JokeContentCustomMapper
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.cretin.com
 * </p>
 * 
 * @author cretin
 * @date 2017年10月20日
 */
public interface JokeContentCustomMapper {
	/**
	 * 文字段子分页查询 按时间排序
	 * 
	 * @param itemsQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<JokeContentVo> findJokesList() throws Exception;
	
	/**
	 * 图片段子分页查询 按时间排序
	 * 
	 * @param itemsQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<JokeImageVo> findImgJokesList() throws Exception;


	/**
	 * 文字段子分页查询 按热度排序
	 * 
	 * @param itemsQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<JokeContentVo> findRecommendJokesList() throws Exception;
}
