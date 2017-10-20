package com.cretin.po.vo;

import java.util.Date;

import com.cretin.po.JokeContent;
import com.cretin.po.User;

/**
 * <p>
 * Title: JokeContentVo
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
public class JokeImageVo {
	// 所属用户
	private CustomerUserVo orignUser;
	private String jokeId;
	private int likeCount;

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	private Date updateTime;

	public CustomerUserVo getOrignUser() {
		return orignUser;
	}

	public void setOrignUser(CustomerUserVo orignUser) {
		this.orignUser = orignUser;
	}

	private String content;
	private String imageUrl;

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	// 显示的发布时间
	private String showTime;

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getJokeId() {
		return jokeId;
	}

	public void setJokeId(String jokeId) {
		this.jokeId = jokeId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
