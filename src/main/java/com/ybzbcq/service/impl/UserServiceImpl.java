package com.ybzbcq.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ybzbcq.mapper.InfoUserMapper;
import com.ybzbcq.model.InfoUser;
import com.ybzbcq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

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

    /**
     * 数据一致性没有解决 暂时注释掉这个地方
     * @param map
     * @return
     */
    @Override
    public List<InfoUser> selectByMultCondition(Map<String, Object> map) {

        // 字符串的序列化器
//        RedisSerializer redisSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(redisSerializer);

//        List<InfoUser> infoUsers = (List<InfoUser>)redisTemplate.opsForValue().get("infoUsers");

        List<InfoUser> infoUsers = infoUserMapper.selectByMultCondition(map);

        /*if(CollectionUtils.isEmpty(infoUsers)){
            synchronized (this){
                infoUsers = (List<InfoUser>)redisTemplate.opsForValue().get("infoUsers");
                if(CollectionUtils.isEmpty(infoUsers)){

                    // json 对象序列化器
                    redisSerializer = new Jackson2JsonRedisSerializer(Object.class);
                    redisTemplate.setValueSerializer(redisSerializer);

                    infoUsers = infoUserMapper.selectByMultCondition(map);
                    redisTemplate.opsForValue().set("infoUsers", infoUsers);
                }
            }
        }*/

        return infoUsers;
    }

    @Override
    public int qryCount() {
        return infoUserMapper.qryCount();
    }
}