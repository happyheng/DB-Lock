package com.happyheng.dao;

import com.happyheng.utils.DbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 订单相关dao
 * Created by happyheng on 2019/3/24.
 */
@Service
public class OrderDao {

    @Autowired
    private DbUtils dbUtils;


    public int addOrder(int userId, int orderId) {

        Connection connection = dbUtils.getNewConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO orders (userId, goodsId) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userId);
            statement.setInt(2, orderId);

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


}
