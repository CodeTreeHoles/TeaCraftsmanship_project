package cn.edu.chzc.controller;

import cn.edu.chzc.pojo.Result;
import cn.edu.chzc.pojo.User;
import cn.edu.chzc.service.UserService;
import cn.edu.chzc.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user){


        Map<String,Object> claims = new HashMap<>();

        User u = userService.login(user);

        //获取到的用户为空的情况下，视为登录失败
        if(u != null){
            Long id = u.getId();
            String password = u.getPassword();

            //生成jwt令牌
            claims.put("id",id);
            claims.put("password",password);
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        return Result.success("登录失败");
    }

    @PostMapping("/enroll")
    public Result<String> enroll(@RequestBody User user){
        log.info("{}",user);
        return Result.success(userService.enroll(user));
    }
}
