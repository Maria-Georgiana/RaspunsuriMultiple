package com.example.raspunsurimultiple;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent mainRoot = mainLoader.load();

        FXMLLoader productsLoader = new FXMLLoader(HelloApplication.class.getResource("SQLFile.fxml"));
        Parent productsRoot = productsLoader.load();
        SQLController patientsController = productsLoader.getController();
        patientsController.initializeProducts();



        HelloController controller = mainLoader.getController();
        controller.initialize(mainRoot, productsRoot);

        Scene scene = new Scene(mainRoot, 450, 300);

        stage.setTitle("Pagina principală");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}