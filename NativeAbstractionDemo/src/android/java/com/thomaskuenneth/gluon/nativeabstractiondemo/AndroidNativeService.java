package com.thomaskuenneth.gluon.nativeabstractiondemo;

import android.widget.Toast;
import com.gluonhq.charm.down.common.Platform;
import com.gluonhq.charm.down.common.PlatformFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxports.android.FXActivity;

public class AndroidNativeService implements NativeService {

    private static final Logger LOGGER = Logger.getLogger(AndroidNativeService.class.getName());

    private final Platform pf;

    public AndroidNativeService() {
        pf = PlatformFactory.getPlatform();
        LOGGER.log(Level.INFO, "isTablet(): {0}", pf.isTablet());
    }

    @Override
    public void notify(String s) {
        FXActivity.getInstance().runOnUiThread(() -> {
            Toast.makeText(FXActivity.getInstance(), s, Toast.LENGTH_LONG).show();
        });
    }

}
