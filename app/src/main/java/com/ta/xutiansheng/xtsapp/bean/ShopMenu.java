package com.ta.xutiansheng.xtsapp.bean;

import java.io.Serializable;

public class ShopMenu  implements Serializable {

    /**
     * foodname : 精选套餐荤素搭配
     * menuname : 套餐一
     * price : 19.9
     * foodimgs :
     * foodid : 10
     * shopid : dd0b02f2c74e4dd0829d009d7f4e60fd
     * info : 精美的19.9的套餐请大家品尝荤素均匀
     */

    private String foodname;
    private String menuname;
    private String price;
    private String foodimgs;
    private int foodid;
    private String shopid;
    private String info;
    //销量　
    private String scale;
    private int count=0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ShopMenu{" +
                "foodname='" + foodname + '\'' +
                ", menuname='" + menuname + '\'' +
                ", price='" + price + '\'' +
                ", foodimgs='" + foodimgs + '\'' +
                ", foodid=" + foodid +
                ", shopid='" + shopid + '\'' +
                ", info='" + info + '\'' +
                ", scale='" + scale + '\'' +
                '}';
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFoodimgs() {
        return foodimgs;
    }

    public void setFoodimgs(String foodimgs) {
        this.foodimgs = foodimgs;
    }

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
