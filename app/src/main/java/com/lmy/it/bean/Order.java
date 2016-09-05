package com.lmy.it.bean;

import java.util.List;

/**
 * Created by limingyang on 2016/8/14.
 */
public class Order {
    private int id;
    private int desk; //几号桌
    private List<Food> foods;

    /**
     * 数据库对应的列
     */
    public static class TableColumn {
        public static final String TABLE_NAME = "ord";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_DESK = "desk";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDesk() {
        return desk;
    }

    public void setDesk(int desk) {
        this.desk = desk;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
