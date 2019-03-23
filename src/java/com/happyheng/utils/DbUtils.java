package com.happyheng.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * Created by happyheng on 2019/3/22.
 */
@Service
public class DbUtils {


    @Value("${db.url}")
    private String dbUrl;
    @Value("${db.user}")
    private String dbUser;
    @Value("${db.password}")
    private String dbPassWord;


    /**
     * 获取数据库连接
     */
    public Connection getNewConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, dbUser, dbPassWord);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
