package com.ybzbcq.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ybzbcq.mapper.InfoUserMapper;
import com.ybzbcq.model.InfoUser;
import com.ybzbcq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @Description 用户服务类
 * @since 2019-06-06 13:56
 */

@Component
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private InfoUserMapper infoUserMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return infoUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(InfoUser infoUser) {
        return infoUserMapper.insertSelective(infoUser);
    }

    @Override
    public InfoUser selectByPrimaryKey(Integer id) {
        return infoUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(InfoUser infoUser) {
        return infoUserMapper.updateByPrimaryKeySelective(infoUser);
    }

    @Override
    public List<InfoUser> selectByMultCondition(Map<String, Object> map) {

        List<InfoUser> infoUsers = (List<InfoUser>)redisTemplate.opsForValue().get("infoUsers");

        if(CollectionUtils.isEmpty(infoUsers)){
            synchronized (this){
                infoUsers = (List<InfoUser>)redisTemplate.opsForValue().get("infoUsers");
                if(CollectionUtils.isEmpty(infoUsers)){
                    infoUsers = infoUserMapper.selectByMultCondition(map);
                    redisTemplate.opsForValue().set("infoUsers", infoUsers);
                }
            }
        }

        return infoUsers;
    }

    @Override
    public int qryCount() {
        return infoUserMapper.qryCount();
    }
}