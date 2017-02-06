package com.qianmo.meituan.bean;

/**
 * Created by wangjitao on 2016/10/14 0014.
 * 用于存放每个小条目的实体类的，包含名称和图片
 */
public class Subject {
    /**
     * 名称
     */
    private String name;
    /**
     * 图片地址
     */
    private int icon_res;

    public Subject(String name, int icon_res) {
        this.name = name;
        this.icon_res = icon_res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon_res() {
        return icon_res;
    }

    public void setIcon_res(int icon_res) {
        this.icon_res = icon_res;
    }
}
