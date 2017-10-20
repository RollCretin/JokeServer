package com.cretin.mapper;

import com.cretin.po.JokeImg;
import com.cretin.po.JokeImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JokeImgMapper {
    int countByExample(JokeImgExample example);

    int deleteByExample(JokeImgExample example);

    int deleteByPrimaryKey(String jokeId);

    int insert(JokeImg record);

    int insertSelective(JokeImg record);

    List<JokeImg> selectByExampleWithBLOBs(JokeImgExample example);

    List<JokeImg> selectByExample(JokeImgExample example);

    JokeImg selectByPrimaryKey(String jokeId);

    int updateByExampleSelective(@Param("record") JokeImg record, @Param("example") JokeImgExample example);

    int updateByExampleWithBLOBs(@Param("record") JokeImg record, @Param("example") JokeImgExample example);

    int updateByExample(@Param("record") JokeImg record, @Param("example") JokeImgExample example);

    int updateByPrimaryKeySelective(JokeImg record);

    int updateByPrimaryKeyWithBLOBs(JokeImg record);

    int updateByPrimaryKey(JokeImg record);
}