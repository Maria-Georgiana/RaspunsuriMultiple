package com.example.raspunsurimultiple;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button entitiesButton;
    private Parent mainRoot;
    private Parent entityRoot;

    public void initialize(Parent mainRoot, Parent productsRoot) {
        this.mainRoot = mainRoot;
        this.entityRoot = productsRoot;
    }

    public void resetPage() throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        this.mainRoot = mainLoader.load();

        FXMLLoader entityLoader = new FXMLLoader(HelloApplication.class.getResource("SQLFile.fxml"));
        this.entityRoot = entityLoader.load();
        SQLController entitiesController = entityLoader.getController();
        entitiesController.initializeProducts();

        HelloController controller = mainLoader.getController();
        controller.initialize(this.mainRoot, this.entityRoot);
    }


    @FXML
    private void onEntitiesButtonClick() {
        try {
            resetPage();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
        showPage(entityRoot, "Products");
    }


    private void showPage(Parent pageRoot, String pageTitle) {
        Stage stage = new Stage();

        try {
            Scene scene = new Scene(pageRoot, 1200, 600);

            Stage[] stages = Stage.getWindows().toArray(new Stage[0]);
            for (Stage existingStage : stages) {
                existingStage.close();
            }

            stage.setTitle(pageTitle);
            stage.setScene(scene);
            stage.show();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }
}
