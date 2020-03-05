package calculator;

import calculator.logic.Equation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/style/structure.fxml");

        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style/global_stylesheet.css").toExternalForm());


        stage.setScene(scene);
        InputStream iconStream = getClass().getResourceAsStream("/images/pi.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.show();

        Equation asd = new Equation();
        asd.addNewElement("123");
        System.out.println(asd.getLastElement());
        asd.appendToBeginningOfElement("-");
        System.out.println(asd.getLastElement());

        /*GridPane.setFillWidth(, true);
        GridPane.setFillHeight(myButton, true);*/

        /*FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();*/
    }

    public static void main(String[] args) {
        launch();
    }

}