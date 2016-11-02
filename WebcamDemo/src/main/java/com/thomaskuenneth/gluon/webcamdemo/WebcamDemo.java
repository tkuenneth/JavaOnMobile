package com.thomaskuenneth.gluon.webcamdemo;

import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.glisten.application.MobileApplication;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class WebcamDemo extends MobileApplication {

    private WebcamView view = null;

    @Override
    public void init() {
        addViewFactory(HOME_VIEW, () -> {
            return view = new WebcamView(HOME_VIEW);
        });
    }

    @Override
    public void postInit(Scene scene) {
        ((Stage) scene.getWindow()).getIcons().add(new Image(WebcamDemo.class.getResourceAsStream("/icon.png")));
        if (Platform.isDesktop()) {
            scene.getWindow().setWidth(WebcamView.CAM_W);
            scene.getWindow().setHeight(WebcamView.CAM_H);
        }
        setTitle("WebcamDemo");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (view != null) {
            view.stop();
        }
    }
}
