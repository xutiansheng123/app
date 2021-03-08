package com.ta.xutiansheng.xtsapp.bean;

import java.util.List;

public class ShopBean {

    public List<ShopMenu> getFoodlist() {
        return foodlist;
    }

    public void setFoodlist(List<ShopMenu> foodlist) {
        this.foodlist = foodlist;
    }

    /**
     * createtime : 2020-07-24 21:56:15
     * address : 成都
     * shopname : 潇潇的火锅店
     * shopid : dd0b02f2c74e4dd0829d009d7f4e60fd
     * phonenum : 18615521792
     * type : 1
     */

    private List<ShopMenu> foodlist;
    private String createtime;
    private String address;
    private String shopname;
    private String shopid;
    private String phonenum;
    private int type;
    private String headimg;
     private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "ShopBean{" +
                "createtime='" + createtime + '\'' +
                ", address='" + address + '\'' +
                ", shopname='" + shopname + '\'' +
                ", shopid='" + shopid + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", type=" + type +
                ", headimg='" + headimg + '\'' +
                '}';
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
