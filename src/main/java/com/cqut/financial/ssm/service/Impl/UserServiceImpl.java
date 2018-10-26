package com.cqut.financial.ssm.service.Impl;

import com.cqut.financial.ssm.dao.base.EntityDao;
import com.cqut.financial.ssm.dao.base.SearchDao;
import com.cqut.financial.ssm.entity.User;
import com.cqut.financial.ssm.entity.base.Entity;
import com.cqut.financial.ssm.service.IUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Li
 * @date 2018/10/23-11:58
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private EntityDao entityDao;
    @Resource
    private SearchDao searchDao;

    @Override
    public Map<String, Object> add(Map<String, Object> properties) {
        Map<String, Object> result = new HashMap<>();

        User user = new User();
        user.setProperties(properties);
        user.setPassword(pwdEncryption(user.getPassword(),user.getUser_id().toString()));

        entityDao.save(user);
        result.put("result","1");
        return result;
    }


    private String pwdEncryption(String pwd, String salt_user_id){
        String hashAlgorithmName = "MD5";
        Object credentials = pwd;
        Object salt = ByteSource.Util.bytes(salt_user_id);;
        int hashIterations = 512;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        return result.toString();
    }


    @Override
    public Map<String, Object> delete(String user_id) {
        Map<String, Object> result = new HashMap<>();
        entityDao.deleteByID(user_id, Entity.getTableName(User.class),Entity.getPrimaryKey(User.class));
        result.put("result","1");
        return result;
    }

    @Override
    public Map<String, Object> update(Map<String, Object> properties) {
        Map<String, Object> result = new HashMap<>();

        User user = new User();
        user.setProperties(properties);
        user.setPassword(pwdEncryption(user.getPassword(),user.getUser_name()));

        entityDao.updatePropByID(user,user.getUser_id().toString());
        result.put("result","1");
        return result;
    }

    @Override
    public Map<String, Object> getById(String user_id) {
        Map<String, Object> result = new HashMap<>();
        result = entityDao.findByID(new String[]{
                "user_id" ,
                "password",
                "user_name" ,
                "total_assets" ,
                "money"
                },
                user_id,
                Entity.getPrimaryKey(User.class),
                Entity.getTableName(User.class));

        if(result.size() == 0 || result == null){
            result.put("result","-1");
        }else{
            result.put("result","1");
        }

        return result;
    }
}
