package com.cqut.financial.ssm.controller;

/**
 * @author Li
 * @date 2018/10/14-16:39
 */

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.cqut.financial.ssm.entity.User;
import com.cqut.financial.ssm.service.IUserService;
import com.cqut.financial.ssm.service.Impl.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private ShiroService shiroService;
    @Resource
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/testShiroAnnotation")
    public String testShiroAnnotation(HttpSession session){
        session.setAttribute("key", "value12345");
        shiroService.testMethod();

        //获取session的Id
        String sessionId = session.getId();
        //判断session是不是新创建的
        if (session.isNew()) {
            System.out.println("session创建成功，session的id是："+sessionId);
        }else {
            System.out.println("服务器已经存在session，session的id是："+sessionId);
        }
        return "";
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login(@RequestParam("user_id") String user_id,
                        @RequestParam("password") String password){

        Map<String, Object> result = new HashMap<>();

        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(user_id, password);
            // rememberme
//            token.setRememberMe(true);
            try {
                System.out.println("1. " + token.hashCode());
                // 执行登录.对角色权限缓存操作，对session缓存不操作
                currentUser.login(token);

                result = userService.getById(user_id);

                result.put("result","1");

            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            // 所有认证时异常的父类.
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
                System.out.println("登录失败: " + ae.getMessage());

                result.put("result","-1");
            }
        }

        return JSON.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping("/register")
    public String register(@RequestParam("user_id") String user_id,@RequestParam("user_name") String user_name,
                        @RequestParam("password") String password){

        Map<String, Object> result = new HashMap<>();

        User user = new User();
        user.setUser_id(Integer.parseInt(user_id));
        user.setPassword(password);
        user.setUser_name(user_name);
        result = userService.add(user.toMap());

        return JSON.toJSONString(result);
    }

    @RequestMapping("/logout")
    public String logout(@RequestParam("user_id") String user_id){

        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isAuthenticated()) {
            //对角色权限缓存操作，删除session数据库表里的数据，结束当前会话，当重定向到登录界面时候会创建新的session会话
            currentUser.logout();
        }

        return "redirect:user/login";
    }
}
