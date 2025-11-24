package ua.polina.doggonovo.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class MenuPanel extends VBox {
    private MainWindow mainWindow;

    public MenuPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setSpacing(10);
        setPadding(new Insets(15));
        setPrefWidth(200);
        setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-width: 0 1 0 0;");
        createMenu();
    }

    private void createMenu() {
        Label logo = new Label("DoggoNovo");
        logo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #4b3621;");
        Button btnForm = createButton("Підбір породи");
        btnForm.setOnAction(e -> mainWindow.showUserFormPanel());
        Button btnList = createButton("Всі породи");
        btnList.setOnAction(e -> mainWindow.showBreedsListPanel());
        Button btnAbout = createButton("Про програму");
        btnAbout.setOnAction(e -> mainWindow.showAboutPanel());

        getChildren().addAll(logo, new Separator(), btnForm, btnList, btnAbout);
    }

    private Button createButton(String text) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle("-fx-background-color: transparent; -fx-alignment: center-left; -fx-padding: 10; -fx-text-fill: #4b3621;");
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #ffecd1; -fx-alignment: center-left; -fx-padding: 10;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: transparent; -fx-alignment: center-left; -fx-padding: 10;"));
        return btn;
    }
}
