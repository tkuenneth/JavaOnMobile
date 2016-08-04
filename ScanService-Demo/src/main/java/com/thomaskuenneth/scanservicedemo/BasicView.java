package com.thomaskuenneth.scanservicedemo;

import com.gluonhq.charm.down.common.Platform;
import com.gluonhq.charm.down.common.PlatformFactory;
import com.gluonhq.charm.down.common.ScanService;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class BasicView extends View {

    public BasicView(String name) {
        super(name);

        Platform p = PlatformFactory.getPlatform();
        System.out.println(p.getName());
        ScanService sc = p.getScanService();

        Text output = new Text();

        Button button = new Button();
        button.setGraphic(new Icon(MaterialDesignIcon.CAMERA));
        button.setOnAction(e -> {
            StringProperty scannedString = sc.scan();
            scannedString.addListener((obs, ov, nv) -> {
                output.setText(nv);
            });
        });

        VBox controls = new VBox(15.0, button, output);
        controls.setAlignment(Pos.CENTER);
        setCenter(controls);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setTitleText("ScanService-Demo");
    }

}
