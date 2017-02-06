package com.wangjitao.myview.bean;

/**
 * Created by Administrator on 2016/10/12 0012.
 */
public class CityBean {
    private String tag; //用来表示分类，一般用城市拼音首字母
    private String city;

    public CityBean() {

    }

    public CityBean(String tag, String city) {
        this.city = city;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
