package com.lmy.it.bean;

/**
 * Created by limingyang on 2016/8/14.
 */
public class Food {
    private int id;
    private String name;
    private double price;

    /**
     * 数据库对应的列
     */
    public static class TableColumn {
        public static final String TABLE_NAME = "food";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
