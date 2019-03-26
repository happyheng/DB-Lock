package com.happyheng.service;

import com.happyheng.bean.Goods;
import com.happyheng.dao.GoodsDao;
import com.happyheng.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品相关服务
 * Created by happyheng on 2019/3/22.
 */
@Service
public class GoodsService{


    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private OrderDao orderDao;

    /**
     * 乐观锁最多重试次数
     */
    private static final int OPTIMISTIC_TRY_COUNT = 3;

    /**
     * 购买商品，使用乐观锁
     * @param userId   userId
     * @param goodsId  商品id
     * @return         orderId
     */
    public int buyGoodsUseOptimisticLock(int userId, int goodsId) {

        boolean buySuccess = false;

        for (int i = 0; i < OPTIMISTIC_TRY_COUNT; i++) {
            Goods goods = goodsDao.getGoods(goodsId);

            //  查询商品，若count<=0，那么返回
            if (goods.getCount() <= 0) {
                return 0;
            }

            goods.setCount(goods.getCount() - 1);

            // 使用乐观锁来进行保存
            buySuccess = goodsDao.saveGoodsByOptimisticLock(goods);
            if (buySuccess) {
                break;
            }
        }

        if (buySuccess) {
            //  若保存成功，则保存order的id
          return orderDao.addOrder(userId, goodsId);
        } else {
            // 如果一直未成功，返回
            return 0;
        }

    }

}
