package com.qianmo.VerificationCode.Observer;

/**
 * Created by wangjitao on 2016/10/13 0013.
 */
public interface Function {
    /**
     * 添加
     */
    public void addObserver(Observer observer);

    /**
     * 移除
     */
    public void removeObserver(Observer observer);

    /**
     * 通知观察者更新
     */
    public void notifyObserver();

}
