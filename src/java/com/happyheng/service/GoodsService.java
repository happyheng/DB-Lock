package com.happyheng.service;

import com.happyheng.bean.Goods;
import com.happyheng.dao.GoodsDao;
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


        for (int i = 0; i < OPTIMISTIC_TRY_COUNT; i++) {
            Goods goods = goodsDao.getGoods(goodsId);

            // @todo 查询商品，若count<=0，那么返回
            if (goods.getCount() <= 0) {
                return 0;
            }

            goods.setCount(goods.getCount() - 1);

            // 使用乐观锁来进行保存
            boolean saveSuccess = goodsDao.saveGoodsByOptimisticLock(goods);
            if (!saveSuccess) {
                continue;
            }
        }

        // @todo 若保存成功，则保存order

        return 0;

    }


}
