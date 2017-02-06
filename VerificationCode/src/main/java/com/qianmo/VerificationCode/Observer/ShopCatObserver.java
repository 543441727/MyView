package com.qianmo.VerificationCode.Observer;

/**
 * Created by Administrator on 2016/10/13 0013.
 */
public class ShopCatObserver implements Observer{
    public ShopCatObserver(Function function){
        function.addObserver(this);
    }

    @Override
    public void upLoginTag(boolean isLogin) {
        System.out.println("购物车得到的登录状态：  -->" + isLogin);
    }
}
