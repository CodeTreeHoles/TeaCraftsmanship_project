package cn.edu.chzc.service;

import cn.edu.chzc.pojo.User;

public interface UserService {

    User login(User user);
    String enroll(User user);
}
