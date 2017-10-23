package com.cretin.mapper;

import com.cretin.po.VerificationCode;
import com.cretin.po.VerificationCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VerificationCodeMapper {
    int countByExample(VerificationCodeExample example);

    int deleteByExample(VerificationCodeExample example);

    int deleteByPrimaryKey(String smsId);

    int insert(VerificationCode record);

    int insertSelective(VerificationCode record);

    List<VerificationCode> selectByExample(VerificationCodeExample example);

    VerificationCode selectByPrimaryKey(String smsId);

    int updateByExampleSelective(@Param("record") VerificationCode record, @Param("example") VerificationCodeExample example);

    int updateByExample(@Param("record") VerificationCode record, @Param("example") VerificationCodeExample example);

    int updateByPrimaryKeySelective(VerificationCode record);

    int updateByPrimaryKey(VerificationCode record);
}