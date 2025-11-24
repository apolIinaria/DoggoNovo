package ua.polina.doggonovo.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import ua.polina.doggonovo.logic.BreedAdvisor;
import ua.polina.doggonovo.model.BreedRecommendation;
import ua.polina.doggonovo.model.UserProfile;
import java.util.List;

public class RecommendationPanel extends VBox {
    private BreedAdvisor breedAdvisor;
    private UserProfile userProfile;

    public RecommendationPanel(BreedAdvisor breedAdvisor, UserProfile userProfile) {
        this.breedAdvisor = breedAdvisor;
        this.userProfile = userProfile;
        setSpacing(20);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);
        createContent();
    }

    private void createContent() {
        Label title = new Label("Топ-3 породи для вас");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #4b3621;");
        VBox profileBox = new VBox(5);
        profileBox.setPadding(new Insets(15));
        profileBox.setStyle("-fx-background-color: #fff7eb; -fx-background-radius: 8;");
        profileBox.setMaxWidth(600);
        profileBox.getChildren().add(new Label("Хто: " + userProfile.getUserName()));
        profileBox.getChildren().add(new Label("Активність: " + userProfile.getActivityLevel() + "/5"));
        profileBox.getChildren().add(new Label("Досвід: " + userProfile.getExperienceLevel() + "/5"));
        profileBox.getChildren().add(new Label("Житло: " + userProfile.getLivingSpace()));

        List<BreedRecommendation> recommendations = breedAdvisor.getTopRecommendations(3);

        HBox cardsBox = new HBox(20);
        cardsBox.setAlignment(Pos.CENTER);

        for (int i = 0; i < recommendations.size(); i++) {
            Label medal = new Label(getMedal(i + 1));
            medal.setStyle("-fx-font-size: 28px;");
            VBox cardWrapper = new VBox(10);
            cardWrapper.setAlignment(Pos.CENTER);
            BreedCardPanel card = new BreedCardPanel(recommendations.get(i));
            cardWrapper.getChildren().addAll(medal, card);
            cardsBox.getChildren().add(cardWrapper);
        }

        getChildren().addAll(title, profileBox, cardsBox);
    }

    private String getMedal(int position) {
        switch (position) {
            case 1: return "1";
            case 2: return "2";
            case 3: return "3";
            default: return "";
        }
    }
}