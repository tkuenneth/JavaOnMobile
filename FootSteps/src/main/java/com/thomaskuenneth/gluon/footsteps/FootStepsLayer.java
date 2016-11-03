package com.thomaskuenneth.gluon.footsteps;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class FootStepsLayer extends MapLayer {

    private static final int SIZE = 32;
    private static final Image FOOTSTEPS
            = new Image(FootStepsLayer.class.getResourceAsStream("/footsteps.png"),
                    SIZE, SIZE, true, true);

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
        MapPoint p = baseMap.getMapPosition(x, y);
        addPoint(p);
    }

    @Override
    protected void layoutLayer() {
        // Warning: suggested conversion to functional style crashed app on BlueStacks
        for (Pair<MapPoint, Node> element : points) {
            MapPoint mapPoint = element.getKey();
            Node node = element.getValue();
            Point2D point = baseMap.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());
            node.setVisible(true);
            node.setTranslateX(point.getX() - SIZE / 2);
            node.setTranslateY(point.getY() - SIZE / 2);
        }
    }
}
