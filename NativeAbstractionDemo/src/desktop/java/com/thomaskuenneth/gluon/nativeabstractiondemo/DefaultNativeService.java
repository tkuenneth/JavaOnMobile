package com.thomaskuenneth.gluon.nativeabstractiondemo;

public class DefaultNativeService implements NativeService {

    @Override
    public void notify(String s) {
        System.out.println(s);
    }
    
}
