package com.thomaskuenneth.gluon.nativeabstractiondemo;

import com.gluonhq.charm.down.common.Platform;
import com.gluonhq.charm.down.common.PlatformFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AndroidNativeService implements NativeService {

    private static final Logger LOGGER = Logger.getLogger(AndroidNativeService.class.getName());

    private final Platform pf;

    public AndroidNativeService() {
        pf = PlatformFactory.getPlatform();
        LOGGER.log(Level.INFO, "isTablet(): {0}", pf.isTablet());
    }

    @Override
    public void notify(String s) {
    }

}
