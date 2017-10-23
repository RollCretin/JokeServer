package com.cretin.po.vo;

import java.io.Serializable;
import java.util.Date;

/**
*<p>Title: CustomerUserVo </p>
*<p>Description: </p>
*<p>Company: www.cretin.com </p> 
* @author cretin
* @date 2017年10月20日
*/
public class CustomerUserVo implements Serializable{
	private static final long serialVersionUID = 1810382113491528817L;

	private String userId;

    private String username;

    private String avatar;

    private String nickname;

    private Integer age;

    private Integer sex;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
}
