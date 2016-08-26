package com.thomaskuenneth.gluon.footsteps;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.maps.MapPoint;

import com.gluonhq.maps.MapView;
import javafx.scene.input.MouseButton;

public class MainView extends View {

    private static final MapPoint NUREMBERG = new MapPoint(49.45, 11.08);

    private final FootStepsLayer layer;

    public MainView(String name) {
        super(name);
        MapView mapView = new MapView();
        mapView.setZoom(18f);
        mapView.setCenter(NUREMBERG);
        layer = new FootStepsLayer();
        mapView.addLayer(layer);
        setCenter(mapView);

        layer.addPoint(NUREMBERG);

        setOnMouseClicked(event -> {
            if ((event.getButton() == MouseButton.PRIMARY)
                    && (event.getClickCount() == 2)) {
                double x = event.getX();
                double y = event.getY();
                layer.addPoint(x, y);
            }
        });

    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setTitleText("Foot Steps");
    }

}
