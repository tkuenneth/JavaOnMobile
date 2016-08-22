package com.thomaskuenneth.gluon.mapsdemo;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.maps.MapPoint;

import com.gluonhq.maps.MapView;

public class BasicView extends View {

    public BasicView(String name) {
        super(name);

        MapView view = new MapView();

        MapPoint mp = new MapPoint(49.45, 11.08);
        view.setZoom(18f);
        view.setCenter(mp);
        view.

        setCenter(view);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setTitleText("MapsDemo");
    }

}
