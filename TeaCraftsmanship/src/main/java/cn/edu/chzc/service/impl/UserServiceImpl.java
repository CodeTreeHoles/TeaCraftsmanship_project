package cn.edu.chzc.service.impl;

import cn.edu.chzc.mapper.UserMapper;
import cn.edu.chzc.pojo.User;
import cn.edu.chzc.service.UserService;
import cn.edu.chzc.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        User u = userMapper.login(user);
        if(u != null) {
            String MD5Code = MD5Utils.MD5Lower(user.getPassword(), u.getSalt());
            if(MD5Code.equals(u.getPassword())){
                log.info("密码正确");
                return u;
            }
        }
        return null;
    }

    @Override
    public String enroll(User user) {
        user.setSalt(MD5Utils.getSalt());
        String password = MD5Utils.MD5Lower(user.getPassword(), user.getSalt());
        user.setPassword(password);
        userMapper.enroll(user);
        return "成功";
    }
}
