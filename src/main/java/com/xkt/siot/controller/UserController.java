/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.controller;

import com.xkt.siot.domain.User;
import com.xkt.siot.service.UserService;
import com.xkt.siot.shiro.UserPasswordService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户控制器
 *
 * @author L.X <gugia@qq.com>
 */
@Controller
public class UserController extends BaseController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    UserService userService;
    @Resource
    UserPasswordService passwordService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest req) {
        Subject subject = SecurityUtils.getSubject();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //userService.findByName(username).getPassword()会导致NullPointerException
        //logger.info("匹配结果 {}", passwordService.passwordsMatch(password, userService.findByName(username).getPassword()));
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        if (req.getParameter("rememberMe") != null) {
            token.setRememberMe(true);
        }
        ModelAndView model = new ModelAndView();
        try {
            subject.login(token);
            model.setViewName("redirect:/console");
        } catch (UnknownAccountException | IncorrectCredentialsException | DisabledAccountException ex) {
            token.clear();
            model.setViewName("login");
            String error;
            if (NumberFormatException.class.getName().equals(ex.getClass().getName())) {
                error = "账号格式不正确";
            } else if (UnknownAccountException.class.getName().equals(ex.getClass().getName())) {
                error = "该用户不存在";
            } else if (IncorrectCredentialsException.class.getName().equals(ex.getClass().getName())) {
                error = "输入的密码与账号不匹配";
            } else if (DisabledAccountException.class.getName().equals(ex.getClass().getName())) {
                error = "该用户尚未激活";
            } else {
                error = "暂时无法登录";
            }
            model.addObject("error", error);
            model.addObject("posted_username", req.getParameter("username"));
        }
        return model;
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public String getp() {
        return "unauthorized";
    }
}
