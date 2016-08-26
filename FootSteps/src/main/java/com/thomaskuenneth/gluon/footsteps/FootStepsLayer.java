package com.thomaskuenneth.gluon.footsteps;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class FootStepsLayer extends MapLayer {

    private static final Image FOOTSTEPS
            = new Image(FootStepsLayer.class.getResourceAsStream("/footsteps.png"),
                    32, 32, true, true);

    private final ObservableList<Pair<MapPoint, Node>> points
            = FXCollections.observableArrayList();

    public void addPoint(MapPoint mapPoint) {
        Node node = new ImageView(FOOTSTEPS);
        Pair<MapPoint, Node> pair = new Pair<>(mapPoint, node);
        points.add(pair);
        getChildren().add(node);
        markDirty();
    }

    public void addPoint(double x, double y) {
        System.out.println(x + ", " + y);
        Bounds bounds = baseMap.getParent().getLayoutBounds();
        baseMap.moveX(x - bounds.getWidth() / 2);
        baseMap.moveY(y - bounds.getHeight() / 2);
        addPoint(new MapPoint(baseMap.centerLat().get(),
                baseMap.centerLon().get()));
    }

    @Override
    protected void layoutLayer() {
        // Warning: suggested conversion to functional style crashed app on BlueStacks
        for (Pair<MapPoint, Node> element : points) {
            MapPoint mapPoint = element.getKey();
            Node node = element.getValue();
            Point2D point = baseMap.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());
            node.setVisible(true);
            node.setTranslateX(point.getX());
            node.setTranslateY(point.getY());
        }
    }
}
