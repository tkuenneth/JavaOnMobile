package com.thomaskuenneth.gluon.nativeabstractiondemo;

import com.gluonhq.charm.down.common.JavaFXPlatform;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NativePlatformFactory {

    private static final Logger LOGGER = Logger.getLogger(NativePlatformFactory.class.getName());

    public static NativePlatform getPlatform() {
        try {
            return (NativePlatform) Class.forName(getPlatformClassName()).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            LOGGER.log(Level.SEVERE, "getPlatform()", ex);
            return null;
        }

    }

    private static String getPlatformClassName() {
        switch (JavaFXPlatform.getCurrent()) {
            case ANDROID:
                return "com.thomaskuenneth.gluon.nativeabstractiondemo.AndroidNativePlatform";
            default:
                return "com.thomaskuenneth.gluon.nativeabstractiondemo.DefaultNativePlatform";
        }
    }
}
