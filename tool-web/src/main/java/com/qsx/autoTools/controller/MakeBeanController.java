package com.qsx.autoTools.controller;

import com.qsx.autoTools.service.impl.MakeBeanServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by shuxinqin
 * DATE : 18-2-5
 * TIME : 下午3:36
 * PROJECT : convenienceTools
 * PACKAGE : com.qsx.autoTools.controller
 *
 * @author <a href="mailto:shuxin.qin@qunar.com">shuxin.qin</a>
 */
@Controller
public class MakeBeanController {

    @Resource
    private MakeBeanServiceImpl makeBeanService;

    @RequestMapping(value = "/")
    public ModelAndView home(){

        String viewName = "home";
        ModelAndView modelAndView = new ModelAndView(viewName);
        return modelAndView;
    }

    @RequestMapping(value = "/upload", method = { RequestMethod.POST })
    public ModelAndView addUser(@RequestParam(value = "createTable") String createTable)
            throws UnsupportedEncodingException {

        createTable = new String(createTable.getBytes("8859_1"), "utf8");
        String viewName = "makeBean";
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("bean", makeBeanService.makeBeanByCreateTableString(createTable));
        modelAndView.addObject("source", createTable);
        return modelAndView;
    }
}
