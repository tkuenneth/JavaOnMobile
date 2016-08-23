package com.thomaskuenneth.gluon.mapsdemo;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.maps.MapPoint;

import com.gluonhq.maps.MapView;
import javafx.scene.input.MouseButton;

/**
 * Important: see local dependency in build.gradle to maps-1.0.1-SNAPSHOT.jar;
 * needed to be able to update
 *
 * @author thomas
 */
public class BasicView extends View {

    private static final MapPoint NUREMBERG = new MapPoint(49.45, 11.08);

    private final FootStepsLayer layer;

    public BasicView(String name) {
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
        appBar.setTitleText("MapsDemo");
    }

}
