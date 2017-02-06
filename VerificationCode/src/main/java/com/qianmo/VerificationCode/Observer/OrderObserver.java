package com.qianmo.VerificationCode.Observer;

/**
 * Created by Administrator on 2016/10/13 0013.
 */
public class OrderObserver implements Observer {
    public OrderObserver(Function function) {
        function.addObserver(this);
    }

    @Override
    public void upLoginTag(boolean isLogin) {
        System.out.println("订单得到的登录状态：  -->" + isLogin);
    }
}
