package com.thomaskuenneth.scanservicedemo;

import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.BarcodeScanService;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class BasicView extends View {

    public BasicView(String name) {
        super(name);
        Text output = new Text();
        Button button = new Button();
        button.setGraphic(new Icon(MaterialDesignIcon.CAMERA));
        Services.get(BarcodeScanService.class).ifPresent((sc) -> {
            button.setOnAction(e -> {
                sc.scan().ifPresent((str) -> {
                    output.setText(str);
                });
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
