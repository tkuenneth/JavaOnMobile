package com.thomaskuenneth.gluon.nativeabstractiondemo;

public class DefaultNativePlatform extends NativePlatform {

    private DefaultNativeService defaultdNativeService;

    @Override
    public NativeService getNativeService() {
        if (defaultdNativeService == null) {
            defaultdNativeService = new DefaultNativeService();
        }
        return defaultdNativeService;
    }
}
