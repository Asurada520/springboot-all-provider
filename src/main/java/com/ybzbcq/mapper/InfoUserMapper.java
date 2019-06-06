package com.ybzbcq.mapper;

import com.ybzbcq.model.InfoUser;

import java.util.List;
import java.util.Map;

public interface InfoUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InfoUser record);

    int insertSelective(InfoUser record);

    InfoUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InfoUser record);

    int updateByPrimaryKey(InfoUser record);

    List<InfoUser> selectByMultCondition(Map<String, Object> map);

    int qryCount();
}