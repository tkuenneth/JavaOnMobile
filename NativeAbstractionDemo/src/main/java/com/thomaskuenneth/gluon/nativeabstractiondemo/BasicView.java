package com.thomaskuenneth.gluon.nativeabstractiondemo;

import com.gluonhq.charm.down.common.Platform;
import com.gluonhq.charm.down.common.PlatformFactory;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;

public class BasicView extends View {
    
    private static final Logger LOGGER = Logger.getLogger(BasicView.class.getName());
    
    public BasicView(String name) {
        super(name);
        TextArea ta = new TextArea();
        ta.setEditable(false);
        setCenter(ta);
        
        StringBuilder sb = new StringBuilder();

        // Private Storage
        Platform pf = PlatformFactory.getPlatform();
        try {
            pf.getPrivateStorage().listFiles((File pathname) -> {
                sb.append(pathname.getAbsolutePath())
                        .append("\n");
                return true;
            });
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "getFiles()", ex);
        }
        
        ta.setText(sb.toString());
        
        NativePlatform natPlat = NativePlatformFactory.getPlatform();
        natPlat.getNativeService().notify("Hallo Herbstcampus");
    }
    
    @Override
    protected void updateAppBar(AppBar appBar) {
    }
    
}
