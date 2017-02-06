package com.qianmo.VerificationCode.Observer;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by wangjitao on 2016/10/13 0013.
 */
public class LoginFunction implements Function {

    private List<Observer> observers = new ArrayList<>();

    private boolean isLogin;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.upLoginTag(isLogin);
        }
    }

    public void setLoginTag(boolean isLogin) {
        this.isLogin = isLogin;
        notifyObserver();
    }

}
