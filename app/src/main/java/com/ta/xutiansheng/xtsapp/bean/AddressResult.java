package com.ta.xutiansheng.xtsapp.bean;

import java.io.Serializable;

public class AddressResult  implements Serializable {

    /**
     * addressinfo : 2栋1609
     * address : 青年城嘉年华smart公寓
     * latitude : 30.536943
     * phonenum : 18615521792
     * type : 1
     * userid : a42c7382-f360-4160-8c84-58c9fad358ef
     * addressid : 2
     * longitude : 104.078805
     * username : xutiansheng
     */

    private String addressinfo;
    private String address;
    private String latitude;
    private String phonenum;
    private int type;
    private String userid;
    private int addressid;
    private String longitude;
    private String username;
    private int sex;
    private String addressname;

    public String getAddressname() {
        return addressname;
    }

    public void setAddressname(String addressname) {
        this.addressname = addressname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddressinfo() {
        return addressinfo;
    }

    public void setAddressinfo(String addressinfo) {
        this.addressinfo = addressinfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getAddressid() {
        return addressid;
    }

    public void setAddressid(int addressid) {
        this.addressid = addressid;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
