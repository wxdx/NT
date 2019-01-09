package com.bjpowernode.seckill.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.seckill.commons.CommonsConstants;
import com.bjpowernode.seckill.commons.CommonsReturnObject;
import com.bjpowernode.seckill.model.Goods;
import com.bjpowernode.seckill.model.User;
import com.bjpowernode.seckill.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName:GoodsController
 * Package:com.bjpowernode.seckill.controller
 * Description:
 *
 * @Date:2018/10/23 19:51
 * @Author:hiwangxiaodong@hotmail.com
 */
@Controller
public class GoodsController {
    @Reference
    private GoodsService goodsService;

    @GetMapping("/")
    public String goods(Model model){
        List<Goods> goodsList = goodsService.queryAllSecKillGoods();
        model.addAttribute("goodsList", goodsList);
        return "goods";
    }
    @GetMapping("/goods/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model, HttpSession session){
        User user = new User();
        user.setId(1997);
        session.setAttribute(CommonsConstants.SESSION_USER, user);
        Goods goods = goodsService.querySecKillGoodsById(id);
        model.addAttribute("goods", goods);
        model.addAttribute("nowTime", System.currentTimeMillis());
        return "detail";
    }

    @PostMapping("/goods/random/{id}")
    public @ResponseBody CommonsReturnObject random(@PathVariable("id") Integer id){
        CommonsReturnObject commonsReturnObject = new CommonsReturnObject();
        Goods goods = goodsService.querySecKillGoodsById(id);

        long startTime = goods.getStartTime().getTime();
        long endTime = goods.getEndTime().getTime();
        long nowTime = System.currentTimeMillis();

        if (startTime > nowTime){
            //秒杀未开始
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("秒杀未开始!");
        } else if (nowTime > endTime){
            //秒杀已结束
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("秒杀已结束!");
        } else {
            commonsReturnObject.setErrorCode(CommonsConstants.ZERO);
            commonsReturnObject.setData(goods.getRandomName());
        }
        return  commonsReturnObject;
    }

    @PostMapping("/goods/{id}/{random}")
    public @ResponseBody CommonsReturnObject execSeckill(@PathVariable("id") Integer id,
                                                         @PathVariable("random") String random, HttpSession session){

        User user = (User) session.getAttribute(CommonsConstants.SESSION_USER);
        Integer uid = user.getId();

        ExecutorService executorService = Executors.newFixedThreadPool(16);

        for (int i = 0; i < 10000; i++) {
            int userId = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    CommonsReturnObject commonsReturnObject = goodsService.execSecKill(id,random,userId);
                    System.out.println(commonsReturnObject.getErrorMessage() + userId);
                }
            });

        }
        //秒杀核心业务逻辑
        CommonsReturnObject commonsReturnObject = goodsService.execSecKill(id,random,uid);

        return commonsReturnObject;

    }

    @PostMapping("/goods/result/{id}")
    public @ResponseBody CommonsReturnObject secKillResult(@PathVariable("id") Integer id, HttpSession session){
        User user = (User) session.getAttribute(CommonsConstants.SESSION_USER);
        CommonsReturnObject commonsReturnObject = goodsService.querySecKillResult(id, user.getId());
        return  commonsReturnObject;
    }

}
