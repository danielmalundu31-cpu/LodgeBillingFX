package com.lodgebilling;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginView {

    private final Main main;

    public LoginView(Main main) {
        this.main = main;
    }

    public Parent getView() {

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.getStyleClass().add("login-background");

        VBox card = new VBox(18);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(40));
        card.setMaxWidth(430);
        card.setMaxHeight(500);
        card.getStyleClass().add("login-card");

        Label icon = new Label("🏨");
        icon.setFont(Font.font(45));

        Label title = new Label("LODGE BILLING");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.getStyleClass().add("login-title");

        Label subtitle = new Label("Management System");
        subtitle.getStyleClass().add("login-subtitle");

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setPrefHeight(45);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setPrefHeight(45);

        Button loginButton = new Button("LOGIN");
        loginButton.setPrefWidth(300);
        loginButton.setPrefHeight(45);
        loginButton.getStyleClass().add("primary-button");

        Label message = new Label();
        message.getStyleClass().add("error-label");

        Label demo = new Label("Demo: admin / admin");
        demo.getStyleClass().add("demo-label");

        loginButton.setOnAction(e -> {

            String user = username.getText().trim();
            String pass = password.getText();

            if (user.equals("admin") && pass.equals("admin")) {

                main.showDashboard(user);

            } else {

                message.setText("Invalid username or password");

            }
        });

        password.setOnAction(e -> loginButton.fire());

        card.getChildren().addAll(
                icon,
                title,
                subtitle,
                username,
                password,
                loginButton,
                message,
                demo
        );

        root.getChildren().add(card);

        return root;
    }
}
