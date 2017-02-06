package com.qianmo.VerificationCode.Observer;

/**
 * Created by wangjitao on 2016/10/13 0013.
 */
public class TestObserver {

    public static void main(String[] args) {
//        SubjectFor3D subjectFor3D = new SubjectFor3D();
//
//        ObserverGao observerGao = new ObserverGao(subjectFor3D);
//        ObserverWang observerWang = new ObserverWang(subjectFor3D);
//
//        subjectFor3D.setMsg("3D号码是：141");
//
//        subjectFor3D.setMsg("3D号码是：520");

        LoginFunction loginFunction = new LoginFunction();
        ShopCatObserver shopCatObserver = new ShopCatObserver(loginFunction);
        OrderObserver orderObserver = new OrderObserver(loginFunction);

        loginFunction.setLoginTag(true);

    }
}
