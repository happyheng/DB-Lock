package com.happyheng.controller;

import com.happyheng.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by happyheng on 2019/3/24.
 */
@RequestMapping("/goods")
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/buy")
    public String buyGoods() {


        int randomUserId = (int) (Math.random() * 100) + 1;

        System.out.println(" userId为 " + randomUserId);

        int orderId  = goodsService.buyGoodsUseOptimisticLock(randomUserId, 1);

        System.out.println(" 购买的orderId为 " + orderId);

        return "购买成功，购买的order id为 " + orderId;
    }

}
