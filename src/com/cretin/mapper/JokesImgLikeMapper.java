package com.cretin.mapper;

import com.cretin.po.JokesImgLike;
import com.cretin.po.JokesImgLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JokesImgLikeMapper {
    int countByExample(JokesImgLikeExample example);

    int deleteByExample(JokesImgLikeExample example);

    int deleteByPrimaryKey(String likeId);

    int insert(JokesImgLike record);

    int insertSelective(JokesImgLike record);

    List<JokesImgLike> selectByExample(JokesImgLikeExample example);

    JokesImgLike selectByPrimaryKey(String likeId);

    int updateByExampleSelective(@Param("record") JokesImgLike record, @Param("example") JokesImgLikeExample example);

    int updateByExample(@Param("record") JokesImgLike record, @Param("example") JokesImgLikeExample example);

    int updateByPrimaryKeySelective(JokesImgLike record);

    int updateByPrimaryKey(JokesImgLike record);
}