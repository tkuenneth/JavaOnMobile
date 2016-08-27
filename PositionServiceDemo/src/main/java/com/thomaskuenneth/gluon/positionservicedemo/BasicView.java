package com.thomaskuenneth.gluon.positionservicedemo;

import com.gluonhq.charm.down.common.Platform;
import com.gluonhq.charm.down.common.PlatformFactory;
import com.gluonhq.charm.down.common.Position;
import com.gluonhq.charm.down.common.PositionService;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class BasicView extends View {

    private static final Logger LOGGER = Logger.getLogger(BasicView.class.getName());

    final Label label;

    public BasicView(String name) {
        super(name);
        label = new Label();
        VBox controls = new VBox(15.0, label);
        controls.setAlignment(Pos.CENTER);
        setCenter(controls);
        // get current position
        Platform p = PlatformFactory.getPlatform();
        PositionService ps = p.getPositionService();
        outputPos(ps.getPosition());
        ps.positionProperty().addListener((obs, oldPos, newPos) -> {
            LOGGER.log(Level.INFO, "\nobs: {0}\noldPos: {1}\nnewPos: {2}",
                    new Object[]{obs, oldPos, newPos});
            outputPos(newPos);
        });
    }

    private void outputPos(Position p) {
        if (p != null) {
            label.setText(String.format("We are currently here: %f %f",
                    p.getLatitude(),
                    p.getLongitude()));
        }
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> System.out.println("Menu")));
        appBar.setTitleText("Basic View");
        appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
    }

}
