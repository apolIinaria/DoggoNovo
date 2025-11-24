package ua.polina.doggonovo.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URI;

import static ua.polina.doggonovo.utils.Constants.*;

public class AboutPanel extends VBox {
    public AboutPanel() {
        setSpacing(15);
        setPadding(new Insets(30));
        setAlignment(Pos.TOP_CENTER);

        Label logo = new Label("");

        Image image = new Image(getClass().getResourceAsStream(LOGO_PATH));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        logo.setGraphic(imageView);
        logo.setStyle("-fx-font-size: 80px;");

        Label title = new Label("DoggoNovo");
        title.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #4b3621;");

        Label version = new Label("Версія 1.0.0");

        Label description = new Label("Розумний помічник для підбору породи собаки");
        description.setWrapText(true);
        description.setMaxWidth(500);
        description.setAlignment(Pos.CENTER);

        Label author = new Label(APP_AUTHOR);
        author.setStyle("-fx-font-weight: bold; -fx-text-fill: #4b3621;");

        Label info = new Label(
                "Програма аналізує ваш спосіб життя та рекомендує найкращі породи собак. " +
                        "База містить 15 порід у 5 категоріях."
        );
        info.setWrapText(true);
        info.setMaxWidth(500);
        info.setAlignment(Pos.CENTER);

        getChildren().addAll(logo, title, version, new Separator(), description, new Separator(), author, info);
    }
}