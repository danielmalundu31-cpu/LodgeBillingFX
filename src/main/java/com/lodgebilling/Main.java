package com.lodgebilling;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        stage.setTitle("Lodge Billing System");
        stage.setResizable(false);

        showLogin();

        stage.show();
    }

    public void showLogin() {
        LoginView loginView = new LoginView(this);

        Scene scene = new Scene(loginView.getView(), 1000, 650);

        scene.getStylesheets().add(
                getClass().getResource("/com/lodgebilling/style.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void showDashboard(String username) {

        DashboardView dashboard = new DashboardView(this, username);

        Scene scene = new Scene(dashboard.getView(), 1200, 750);

        scene.getStylesheets().add(
                getClass().getResource("/com/lodgebilling/style.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
