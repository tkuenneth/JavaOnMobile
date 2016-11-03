package com.thomaskuenneth.gluon.footsteps;

import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.Position;
import com.gluonhq.charm.down.plugins.PositionService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FootSteps extends MobileApplication {
    
    private static final Logger LOGGER = Logger.getLogger(FootSteps.class.getName());
    private static final ResourceBundle BUNDLE = getResourceBundle();
    private static final Position NUREMBERG = new Position(49.45, 11.08);
    
    private MainView mainView;
    
    @Override
    public void init() {
        mainView = new MainView(HOME_VIEW);
        addViewFactory(HOME_VIEW, () -> mainView);
    }
    
    @Override
    public void postInit(Scene scene) {
        ((Stage) scene.getWindow()).getIcons().add(new Image(FootSteps.class.getResourceAsStream("/footsteps.png")));
        mainView.setPosition(getPosition());
    }
    
    public static String getString(String key) {
        if (BUNDLE.containsKey(key)) {
            return BUNDLE.getString(key);
        }
        return "";
    }
    
    private static ResourceBundle getResourceBundle() {
        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle("strings");
        } catch (MissingResourceException e) {
            LOGGER.log(Level.SEVERE, "bundle not found", e);
        }
        return bundle;
    }
    
    private Position getPosition() {
        return Services.get(PositionService.class)
                .map(positionService -> {
                    Position pos = positionService.getPosition();
                    if (pos == null) {
                        pos = NUREMBERG;
                    }
                    return pos;
                })
                .orElseGet(() -> {
                    return NUREMBERG;
                });
    }
}
