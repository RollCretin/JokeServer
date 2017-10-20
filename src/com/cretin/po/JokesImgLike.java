package com.cretin.po;

import java.util.Date;

public class JokesImgLike {
    private String likeId;

    private String jokeimgId;

    private String userId;

    private Integer type;

    private Date createTime;

    public String getLikeId() {
        return likeId;
    }

    public void setLikeId(String likeId) {
        this.likeId = likeId == null ? null : likeId.trim();
    }

    public String getJokeimgId() {
        return jokeimgId;
    }

    public void setJokeimgId(String jokeimgId) {
        this.jokeimgId = jokeimgId == null ? null : jokeimgId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}