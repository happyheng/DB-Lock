package com.happyheng.dao;

import com.happyheng.bean.Goods;
import com.happyheng.utils.DbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * Created by happyheng on 2019/3/23.
 */
@Service
public class GoodsDao {


    @Autowired
    private DbUtils dbUtils;

    /**
     * 获取到商品
     */
    public Goods getGoods(int goodsId) {

        Connection connection = dbUtils.getNewConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select  * from goods WHERE id = ?");
            statement.setInt(1, goodsId);
            ResultSet resultSet = statement.executeQuery() ;

            if (resultSet.next()) {
                Goods goods = new Goods();
                goods.setId(resultSet.getInt("id"));
                goods.setCount(resultSet.getInt("count"));
                goods.setVersion(resultSet.getLong("version"));

                return goods;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 使用乐观锁保存商品
     */
    public boolean saveGoodsByOptimisticLock(Goods goods) {
        if (goods == null) {
            return false;
        }

        Connection connection = dbUtils.getNewConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update goods set count = ? , version = version + 1 " +
            "WHERE id = ? AND version = ? ");
            statement.setInt(1, goods.getCount());
            statement.setInt(2, goods.getId());
            statement.setLong(3, goods.getVersion());

            int executeCount = statement.executeUpdate();
            return executeCount > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }



}
