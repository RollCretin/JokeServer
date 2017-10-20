package com.cretin.mapper;

import com.cretin.po.JokesContentLike;
import com.cretin.po.JokesContentLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JokesContentLikeMapper {
    int countByExample(JokesContentLikeExample example);

    int deleteByExample(JokesContentLikeExample example);

    int deleteByPrimaryKey(String likeId);

    int insert(JokesContentLike record);

    int insertSelective(JokesContentLike record);

    List<JokesContentLike> selectByExample(JokesContentLikeExample example);

    JokesContentLike selectByPrimaryKey(String likeId);

    int updateByExampleSelective(@Param("record") JokesContentLike record, @Param("example") JokesContentLikeExample example);

    int updateByExample(@Param("record") JokesContentLike record, @Param("example") JokesContentLikeExample example);

    int updateByPrimaryKeySelective(JokesContentLike record);

    int updateByPrimaryKey(JokesContentLike record);
}