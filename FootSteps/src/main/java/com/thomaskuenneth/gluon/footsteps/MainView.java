package com.thomaskuenneth.gluon.footsteps;

import com.gluonhq.charm.down.plugins.Position;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.maps.MapPoint;

import com.gluonhq.maps.MapView;
import javafx.scene.input.MouseButton;

public class MainView extends View {

    private final MapView mapView;
    private final FootStepsLayer layer;

    public MainView(String name) {
        super(name);
        mapView = new MapView();
        mapView.setZoom(18f);
        layer = new FootStepsLayer();
        mapView.addLayer(layer);
        setCenter(mapView);
        setOnMouseClicked(event -> {
            if ((event.getButton() == MouseButton.PRIMARY)
                    && (event.getClickCount() == 2)) {
                double x = event.getX();
                double y = event.getY();
                layer.addPoint(x, y);
            }
        });
    }

    public void setPosition(Position pos) {
        MapPoint start = new MapPoint(pos.getLatitude(), pos.getLongitude());
        mapView.setCenter(start);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setTitleText(FootSteps.getString("title"));
    }
}
