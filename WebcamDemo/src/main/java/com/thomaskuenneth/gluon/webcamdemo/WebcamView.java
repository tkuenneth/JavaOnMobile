package com.thomaskuenneth.gluon.webcamdemo;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.gluonhq.charm.down.common.PlatformFactory;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.layout.layer.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javax.imageio.ImageIO;

public class WebcamView extends View {

    private static final Logger LOGGER = Logger.getLogger(WebcamView.class.getName());

    public static final int CAM_W = 640;
    public static final int CAM_H = 480;

    private final ImageView iv;

    private boolean loop;

    private final WebcamListener l = new WebcamListener() {

        @Override
        public void webcamOpen(WebcamEvent we) {
            LOGGER.fine("webcamOpen()");
        }

        @Override
        public void webcamClosed(WebcamEvent we) {
            LOGGER.fine("webcamClosed()");
        }

        @Override
        public void webcamDisposed(WebcamEvent we) {
            LOGGER.fine("webcamDisposed()");
        }

        @Override
        public void webcamImageObtained(WebcamEvent we) {
            LOGGER.fine("webcamImageObtained()");
            BufferedImage bf = we.getImage();
            if (bf != null) {
                WritableImage writableImage = SwingFXUtils.toFXImage(bf, null);
                Platform.runLater(() -> iv.setImage(writableImage));
            }
        }
    };

    public void stop() {
        loop = false;
    }

    public WebcamView(String name) {
        super(name);
        iv = new ImageView();
        iv.setSmooth(true);
        StackPane sp = new StackPane(iv);
        sp.setPrefSize(CAM_W, CAM_H);
        setCenter(sp);
        getLayers().add(new FloatingActionButton(MaterialDesignIcon.CAMERA.text,
                e -> {
                    try {
                        File basedir = PlatformFactory.getPlatform().getPrivateStorage();
                        File file = File.createTempFile("webcam_", ".png", basedir);
                        ImageIO.write(SwingFXUtils.fromFXImage(iv.getImage(), null), "png", file);
                    } catch (IOException s) {
                        LOGGER.log(Level.SEVERE, "ImageIO.write()", s);
                    }
                }));
        openWebcam();
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }

    /**
     * See https://github.com/sarxos/webcam-capture
     */
    private void openWebcam() {
        new Thread(() -> {
            Webcam webcam = Webcam.getDefault();
            if (webcam != null) {
                webcam.setViewSize(new Dimension(CAM_W, CAM_H));
                webcam.addWebcamListener(l);
                webcam.open(true);
                loop = true;
                while (loop) {
                    Thread.yield();
                }
                webcam.close();
                webcam.removeWebcamListener(l);
            }
        }).start();
    }
}
