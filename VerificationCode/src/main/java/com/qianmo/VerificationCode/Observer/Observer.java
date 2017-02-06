package com.qianmo.VerificationCode.Observer;

/**
 * Created by wangjitao on 2016/10/13 0013.
 * 所有观察者需要实现此接口
 */
public interface Observer {
//    public void upData(String msg);

    public void upLoginTag(boolean isLogin);
}
