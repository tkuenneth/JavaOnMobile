package com.thomaskuenneth.gluon.nativeabstractiondemo;

public class AndroidNativePlatform extends NativePlatform {

    private AndroidNativeService androidNativeService;

    @Override
    public NativeService getNativeService() {
        if (androidNativeService == null) {
            androidNativeService = new AndroidNativeService();
        }
        return androidNativeService;
    }
}
