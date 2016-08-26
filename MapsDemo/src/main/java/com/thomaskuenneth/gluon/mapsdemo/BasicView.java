package com.thomaskuenneth.gluon.mapsdemo;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.maps.MapPoint;

import com.gluonhq.maps.MapView;

public class BasicView extends View {

    private static final MapPoint NUREMBERG = new MapPoint(49.45, 11.08);

    public BasicView(String name) {
        super(name);
        MapView mapView = new MapView();
        mapView.setZoom(18f);
        mapView.setCenter(NUREMBERG);
        setCenter(mapView);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setTitleText("MapsDemo");
    }

}
