package ua.polina.doggonovo.ui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import ua.polina.doggonovo.data.BreedRepository;
import ua.polina.doggonovo.logic.BreedAdvisor;
import ua.polina.doggonovo.model.UserProfile;

public class MainWindow extends BorderPane {
    private MenuPanel menuPanel;
    private VBox headerPanel;
    private UserFormPanel userFormPanel;
    private BreedsListPanel breedsListPanel;
    private RecommendationPanel recommendationPanel;
    private AboutPanel aboutPanel;
    private Node currentPanel;

    private BreedAdvisor breedAdvisor;
    private BreedRepository breedRepository;
    private UserProfile userProfile;

    public MainWindow() {
        initializeLogic();
        initializeComponents();
        layoutComponents();
        applyStyles();
        showUserFormPanel();

        System.out.println("Програма запущена успішно!");
    }

    private void initializeLogic() {
        breedRepository = new BreedRepository();
        breedAdvisor = new BreedAdvisor(breedRepository);
        userProfile = new UserProfile();
        System.out.println("Завантажено " + breedRepository.getBreedCount() + " порід");
    }

    private void initializeComponents() {
        headerPanel = createHeaderPanel();
        menuPanel = new MenuPanel(this);
    }

    private void layoutComponents() {
        setTop(headerPanel);
        setLeft(menuPanel);
    }

    private void applyStyles() {
        setStyle("-fx-background-color: #ffffff;");
    }

    private VBox createHeaderPanel() {
        VBox header = new VBox(5);
        header.setPadding(new Insets(20));

        BackgroundFill bgFill = new BackgroundFill(
                new javafx.scene.paint.LinearGradient(
                        0, 0, 1, 0, true,
                        javafx.scene.paint.CycleMethod.NO_CYCLE,
                        new javafx.scene.paint.Stop(1, Color.web("#fff7eb")),
                        new javafx.scene.paint.Stop(0, Color.web("#fef1d2"))
                ),
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        header.setBackground(new Background(bgFill));
        Label titleLabel = new Label("DoggoNovo");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #4b3621;");
        Label subtitleLabel = new Label("Розумний помічник для підбору породи собаки");
        subtitleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #4b3621;");

        header.getChildren().addAll(titleLabel, subtitleLabel);
        return header;
    }

    public void showUserFormPanel() {
        if (userFormPanel == null) {
            userFormPanel = new UserFormPanel(this);
        }
        switchToPanel(userFormPanel);
        System.out.println("Показано форму користувача");
    }

    public void showBreedsListPanel() {
        if (breedsListPanel == null) {
            breedsListPanel = new BreedsListPanel(breedRepository);
        }
        switchToPanel(breedsListPanel);
        System.out.println("Показано список порід");
    }

    public void showRecommendationPanel() {
        if (userProfile == null || !userProfile.isValid()) {
            showError("Помилка", "Будь ласка, спочатку заповніть анкету!");
            showUserFormPanel();
            return;
        }

        recommendationPanel = new RecommendationPanel(breedAdvisor, userProfile);
        switchToPanel(recommendationPanel);
        System.out.println("Показано рекомендації");
    }

    public void showAboutPanel() {
        if (aboutPanel == null) {
            aboutPanel = new AboutPanel();
        }
        switchToPanel(aboutPanel);
        System.out.println("Показано інформацію про програму");
    }

    private void switchToPanel(Node newPanel) {
        if (currentPanel != newPanel) {
            currentPanel = newPanel;
            setCenter(newPanel);
        }
    }

    public void saveUserProfile(UserProfile profile) {
        this.userProfile = profile;
        breedAdvisor.setUserProfile(profile);
        System.out.println("Профіль збережено:");
        System.out.println(profile);
        showInfo("Успіх", "Профіль успішно збережено!");
        showRecommendationPanel();
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public BreedAdvisor getBreedAdvisor() {
        return breedAdvisor;
    }

    public BreedRepository getBreedRepository() {
        return breedRepository;
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean showConfirm(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
    }
}