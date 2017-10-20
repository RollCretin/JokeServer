package com.cretin.mapper;

import com.cretin.po.JokeContent;
import com.cretin.po.JokeContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JokeContentMapper {
    int countByExample(JokeContentExample example);

    int deleteByExample(JokeContentExample example);

    int deleteByPrimaryKey(String jokeId);

    int insert(JokeContent record);

    int insertSelective(JokeContent record);

    List<JokeContent> selectByExampleWithBLOBs(JokeContentExample example);

    List<JokeContent> selectByExample(JokeContentExample example);

    JokeContent selectByPrimaryKey(String jokeId);

    int updateByExampleSelective(@Param("record") JokeContent record, @Param("example") JokeContentExample example);

    int updateByExampleWithBLOBs(@Param("record") JokeContent record, @Param("example") JokeContentExample example);

    int updateByExample(@Param("record") JokeContent record, @Param("example") JokeContentExample example);

    int updateByPrimaryKeySelective(JokeContent record);

    int updateByPrimaryKeyWithBLOBs(JokeContent record);

    int updateByPrimaryKey(JokeContent record);
}