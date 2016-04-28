/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.controller;

import com.xkt.siot.mina.server.CoordinatorServer;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台控制器
 *
 * @author L.X <gugia@qq.com>
 */
@Controller
public class ConsoleController extends BaseController {

    Logger logger = LoggerFactory.getLogger(ConsoleController.class);
    
    @Resource
    ThreadPoolTaskExecutor taskExecutor;
    @Resource
    CoordinatorServer coordinatorServer;

    @RequestMapping(value = "/console", method = RequestMethod.GET)
    public ModelAndView console() {
        ModelAndView model = new ModelAndView();
        model.setViewName("console");
        return model;
    }
    
    @ResponseBody
    @RequestMapping(value = "/console/cs/start", method = RequestMethod.POST)
    public String startCoordinatorServer() {
        taskExecutor.execute(coordinatorServer);
        return "done";
    }
    
    @ResponseBody
    @RequestMapping(value = "/console/cs/stop", method = RequestMethod.POST)
    public String stopCoordinatorServer() {
        taskExecutor.destroy();
        return "done";
    }
}
