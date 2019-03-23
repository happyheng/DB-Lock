package com.happyheng.bean;

/**
 * 商品对应bean
 * Created by happyheng on 2019/3/23.
 */
public class Goods {


    private int id;

    private int count;

    private long version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
