
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Demo1 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        double width = visualBounds.getWidth();
        double height = visualBounds.getHeight();

        Label label = new Label("Bitte hier klicken");
        label.setTranslateY(50);

        Button button = new Button("Hallo Herbstcampus");
        button.setOnAction(e -> label.setText("Sie haben den Button angeklickt"));

        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setFill(Color.BEIGE);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, button, label);

        Scene scene = new Scene(stackPane, width, height);

        stage.setScene(scene);
        stage.show();
    }
}