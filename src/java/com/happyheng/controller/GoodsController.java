package com.happyheng.controller;

import com.happyheng.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * Created by happyheng on 2019/3/24.
 */
@RequestMapping("/goods")
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    private AtomicInteger optimisticInteger = new AtomicInteger(0);
    private AtomicInteger pessimisticInteger = new AtomicInteger(0);

    @RequestMapping("/buy/optimistic")
    public String buyGoodsUseOptimisticLock() {


        // 为了测试前100百名是否能购买成功，使用AtomicInteger自增来获取到userId
        int userId = optimisticInteger.incrementAndGet();

        System.out.println(" userId为 " + userId);

        int orderId  = goodsService.buyGoodsUseOptimisticLock(userId, 1);

        // 如果前100名购买失败，则输出
        if (userId <= 100 && orderId == 0) {
            System.out.println(userId  + " 前100名购买失败 ");
        }

        return "购买成功，购买的order id为 " + orderId;
    }

    @RequestMapping("/buy/pessimistic")
    public String bugGoodsUsePessimisticLock() {
        // 为了测试前100百名是否能购买成功，使用AtomicInteger自增来获取到userId
        int userId = pessimisticInteger.incrementAndGet();

        System.out.println(" userId为 " + userId);

        int orderId  = goodsService.bugGoodsUsePessimisticLock(userId, 1);

        // 如果前100名购买失败，则输出
        if (userId <= 100 && orderId == 0) {
            System.out.println(userId  + " 前100名购买失败 ");
        }

        return "购买成功，购买的order id为 " + orderId;
    }

}
