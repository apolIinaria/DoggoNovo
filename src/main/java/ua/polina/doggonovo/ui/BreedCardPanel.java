package ua.polina.doggonovo.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.polina.doggonovo.data.BreedData;
import ua.polina.doggonovo.model.BreedRecommendation;
import ua.polina.doggonovo.model.breeds.DogBreed;

public class BreedCardPanel extends VBox {
    private DogBreed breed;
    private BreedRecommendation recommendation;
    public BreedCardPanel(BreedRecommendation recommendation) {
        this(recommendation.getBreed(), recommendation);
    }
    public BreedCardPanel(DogBreed breed) {
        this(breed, null);
    }

    private BreedCardPanel(DogBreed breed, BreedRecommendation recommendation) {
        this.breed = breed;
        this.recommendation = recommendation;
        setSpacing(15);
        setPadding(new Insets(15));
        setPrefWidth(300);
        setStyle("-fx-background-color: white; -fx-border-color: #fef1d2; " +
                "-fx-border-radius: 8; -fx-background-radius: 8;");
        createContent();
    }

    private void createContent() {
        ImageView imageView = createBreedImage();
        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER_LEFT);

        Label nameLabel = new Label(breed.getName());
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        nameLabel.setWrapText(true);

        Label categoryLabel = new Label(breed.getCategoryUkr());
        categoryLabel.setStyle("-fx-background-color: #fef1d2; -fx-text-fill: #4b3621; " +
                "-fx-padding: 3 8; -fx-background-radius: 10; -fx-font-size: 11px;");

        header.getChildren().addAll(nameLabel, categoryLabel);
        Label descLabel = new Label(breed.getShortDescription());
        descLabel.setWrapText(true);
        descLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #4b3621;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);
        grid.add(new Label("Розмір:"), 0, 0);
        grid.add(new Label(breed.getSize()), 1, 0);
        grid.add(new Label("Активність:"), 0, 1);
        grid.add(new Label(breed.getActivityLevel() + "/5"), 1, 1);
        grid.add(new Label("З дітьми:"), 0, 2);
        grid.add(new Label(breed.isGoodWithKids() ? "Так" : "Ні"), 1, 2);

        VBox scoreBox = null;
        if (recommendation != null) {
            scoreBox = new VBox(5);
            scoreBox.setAlignment(Pos.CENTER);
            scoreBox.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 10; -fx-background-radius: 5;");
            Label scoreLabel = new Label(recommendation.getScoreString());
            scoreLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; " +
                    "-fx-text-fill: " + recommendation.getScoreColor() + ";");

            Label matchLabel = new Label(recommendation.getMatchLevel());
            matchLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #4b3621;");

            Label starsLabel = new Label(recommendation.getStarsVisual());
            starsLabel.setStyle("-fx-font-size: 16px;");
            scoreBox.getChildren().addAll(scoreLabel, matchLabel, starsLabel);
        }

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        Button detailsBtn = new Button("Детальніше →");
        detailsBtn.setMaxWidth(Double.MAX_VALUE);
        detailsBtn.setStyle("-fx-background-color: #e8c3af; -fx-text-fill: #4b3621; " +
                "-fx-padding: 8; -fx-font-weight: bold;");
        detailsBtn.setOnAction(e -> showEnhancedDetails());

        getChildren().add(imageView);
        getChildren().addAll(header, descLabel, grid);
        if (scoreBox != null) getChildren().add(scoreBox);
        getChildren().addAll(spacer, detailsBtn);
    }

    private ImageView createBreedImage() {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(270);
        imageView.setFitHeight(180);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-background-radius: 8");

        try {
            String imagePath = breed.getImagePath();
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            imageView.setImage(image);
        } catch (Exception ex) {
            System.out.println("Зображення не знайдено для породи: " + breed.getName());
        }
        return imageView;
    }

    private void showEnhancedDetails() {
        Stage detailStage = new Stage();
        detailStage.initModality(Modality.APPLICATION_MODAL);
        detailStage.setTitle(breed.getName() + " - Детальна інформація");

        EnhancedBreedDetailPanel detailPanel;
        if (recommendation != null) {
            detailPanel = new EnhancedBreedDetailPanel(recommendation);
        } else {
            BreedRecommendation dummyRec = new BreedRecommendation(
                    breed, 0, "Інформаційний показ", null
            );
            detailPanel = new EnhancedBreedDetailPanel(dummyRec);
        }

        ScrollPane scrollPane = new ScrollPane(detailPanel);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 800, 600);
        detailStage.setScene(scene);
        detailStage.show();
    }
}