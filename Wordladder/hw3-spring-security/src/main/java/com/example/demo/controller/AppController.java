package com.example.demo.controller;

import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import com.example.demo.WordLadder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppController {
    protected static Logger logger=LoggerFactory.getLogger(AppController.class);
    private WordLadder wl = new WordLadder();

    @RequestMapping(value="/user/wordladder", method = RequestMethod.GET)
    public String helloworld(@RequestParam String wd1, @RequestParam String wd2) {
        logger.debug("输入w1,Name={}",wd1);
        logger.debug("输入w2,Name={}",wd2);
        Set<String> dic = new HashSet<String>();
        wl.getKeyWords(dic);
        return wl.getLadder(wd1, wd2, dic);
    }
}
