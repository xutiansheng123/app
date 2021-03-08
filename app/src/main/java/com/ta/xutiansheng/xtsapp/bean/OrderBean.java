package com.ta.xutiansheng.xtsapp.bean;

import java.util.List;

public class OrderBean {

    /**
     * createtime : 2020-08-16 04:02:42
     * address : 成都市双流区锦韵路与应龙路交叉路口往西北约50米(嘉年华御府)
     * orderid : 17b1c1ee36dc42afa4b66ab828c9d5131597521762329
     * distributionpeoplename : 小徐
     * foodids : 1:1,2:1
     * phonenum : 18615521792
     * distributionpeopleid : 1
     * foodnames : 精选套餐荤素搭配1,精选套餐荤素搭配2
     * allprice : 39.8
     * shopname : 小小的牛肉拉面
     * name : 徐天盛
     * shopid : 8dea0c78f16549bdb6358a1f38836352
     * state : 0
     * applytype : 0
     * remarks : 测试
     * username : xutiansheng
     */
    private List<ShopMenu> foodlist;


    private String createtime;
    private String address;
    private String orderid;
    private String distributionpeoplename;
    private String foodids;
    private String phonenum;
    private int distributionpeopleid;
    private String foodnames;
    private String allprice;
    private String shopname;
    private String name;
    private String shopid;
    //订单状态
    //具体状态在adapter里面
    private int state;
    private int applytype;
    private String remarks;
    private String username;
    private String headimg;
    private String bgimg;

    public String getBgimg() {
        return bgimg;
    }

    public void setBgimg(String bgimg) {
        this.bgimg = bgimg;
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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getDistributionpeoplename() {
        return distributionpeoplename;
    }

    public void setDistributionpeoplename(String distributionpeoplename) {
        this.distributionpeoplename = distributionpeoplename;
    }

    public String getFoodids() {
        return foodids;
    }

    public void setFoodids(String foodids) {
        this.foodids = foodids;
    }

    public List<ShopMenu> getFoodlist() {
        return foodlist;
    }

    public void setFoodlist(List<ShopMenu> foodlist) {
        this.foodlist = foodlist;
    }
    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public int getDistributionpeopleid() {
        return distributionpeopleid;
    }

    public void setDistributionpeopleid(int distributionpeopleid) {
        this.distributionpeopleid = distributionpeopleid;
    }

    public String getFoodnames() {
        return foodnames;
    }

    public void setFoodnames(String foodnames) {
        this.foodnames = foodnames;
    }

    public String getAllprice() {
        return allprice;
    }

    public void setAllprice(String allprice) {
        this.allprice = allprice;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getApplytype() {
        return applytype;
    }

    public void setApplytype(int applytype) {
        this.applytype = applytype;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
