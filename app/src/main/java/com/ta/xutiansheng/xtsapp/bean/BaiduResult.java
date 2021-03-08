package com.ta.xutiansheng.xtsapp.bean;

import java.io.Serializable;

public class BaiduResult  implements Serializable {

    /**
     * name : 嘉年华青年城smart公寓
     * location : {"lat":30.541561,"lng":104.086211}
     * address : 成都市双流区锦韵路与应龙路交叉路口往西北约50米(嘉年华御府)
     * province : 四川省
     * city : 成都市
     * area : 双流区
     * street_id : f915682ad0b0a6e3c0364481
     * detail : 1
     * uid : f915682ad0b0a6e3c0364481
     */

    private String name;
    private LocationBean location;
    private String address;
    private String province;
    private String city;
    private String area;
    private String street_id;
    private int detail;
    private String uid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet_id() {
        return street_id;
    }

    public void setStreet_id(String street_id) {
        this.street_id = street_id;
    }

    public int getDetail() {
        return detail;
    }

    public void setDetail(int detail) {
        this.detail = detail;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public static class LocationBean  implements Serializable{
        /**
         * lat : 30.541561
         * lng : 104.086211
         */

        private double lat;
        private double lng;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}
