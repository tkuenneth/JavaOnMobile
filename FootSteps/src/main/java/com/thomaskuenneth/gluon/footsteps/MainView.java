package com.thomaskuenneth.gluon.footsteps;

import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.Position;
import com.gluonhq.charm.down.plugins.PositionService;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.maps.MapPoint;

import com.gluonhq.maps.MapView;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.input.MouseButton;

public class MainView extends View {

    private static final Position NUREMBERG = new Position(49.45, 11.08);

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

    /**
     * Sets the center of the map to the current position.
     */
    public void setMapCenterToCurrentPosition() {
        Platform.runLater(() -> {
            Position pos = getCurrentPosition();
            MapPoint mapPoint = new MapPoint(pos.getLatitude(), pos.getLongitude());
            mapView.setCenter(mapPoint);
        });
    }

    /**
     * Start receiving position changes, if a position service si available-
     */
    public void startReceivingPositionChanges() {
        Services.get(PositionService.class).ifPresent((service) -> {
            ReadOnlyObjectProperty<Position> positionProperty = service.positionProperty();
            positionProperty.addListener(e -> {
                Position pos = positionProperty.get();
                if (pos != null) {
                    mapView.setCenter(pos.getLatitude(), pos.getLongitude());
                }
            });
        });
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setTitleText(FootSteps.getString("title"));
    }

    /**
     * Gets the current position. If no position service is available, a
     * hardcoded position is returned.
     *
     * @return current position
     */
    private Position getCurrentPosition() {
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
