/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.controller;

import com.xkt.siot.mina.client.FakeClient;
import com.xkt.siot.mina.server.CoordinatorServer;
import com.xkt.siot.mina.server.SimpleTextServer;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Resource
    SimpleTextServer simpleTextServer;

    @RequestMapping(value = "/console", method = RequestMethod.GET)
    public ModelAndView console() {
        ModelAndView model = new ModelAndView();
        model.setViewName("console");
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/console/cs", method = RequestMethod.POST)
    public String startCoordinatorServer(@RequestParam("cmd") String cmd) {
        if (cmd.equalsIgnoreCase("start")) {
            try {
                taskExecutor.execute(coordinatorServer);
                return "success";
            } catch (Exception ex) {
                logger.error("节点服务器线程启动失败", ex);
                return "节点服务器线程启动失败";
            }
        } else if (cmd.equalsIgnoreCase("stop")) {
            try {
                coordinatorServer.stop();
                return "success";
            } catch (Exception ex) {
                logger.error("停止节点服务器时出现异常", ex);
                return "停止节点服务器时出现异常";
            }
        } else {
            return "意外的命令参数";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/console/ss", method = RequestMethod.POST)
    public String startSimpleTextServer(@RequestParam("cmd") String cmd) {
        if (cmd.equalsIgnoreCase("start")) {
            try {
                taskExecutor.execute(simpleTextServer);
                return "success";
            } catch (Exception ex) {
                logger.error("简单文本服务器线程启动失败", ex);
                return "简单文本服务器线程启动失败";
            }
        } else if (cmd.equalsIgnoreCase("stop")) {
            try {
                simpleTextServer.stop();
                return "success";
            } catch (Exception ex) {
                logger.error("停止简单文本服务器时出现异常", ex);
                return "停止简单文本服务器时出现异常";
            }
        } else {
            return "意外的命令参数";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/console/client", method = RequestMethod.POST)
    public String client() {
        try {
            taskExecutor.execute(new FakeClient());
            return "success";
        } catch (Exception ex) {
            logger.error("测试客户端线程启动失败", ex);
            return "测试客户端线程启动失败";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/console/all/stop", method = RequestMethod.POST)
    public String stopAllServer() {
        return "";
    }
}
