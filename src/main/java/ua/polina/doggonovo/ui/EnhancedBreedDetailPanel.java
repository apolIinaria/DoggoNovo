package ua.polina.doggonovo.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import ua.polina.doggonovo.model.BreedRecommendation;
import ua.polina.doggonovo.model.breeds.*;

public class EnhancedBreedDetailPanel extends VBox {
    private DogBreed breed;
    private BreedRecommendation recommendation;
    public EnhancedBreedDetailPanel(BreedRecommendation recommendation) {
        this.breed = recommendation.getBreed();
        this.recommendation = recommendation;
        setSpacing(20);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: white;");
        createContent();
    }

    private void createContent() {
        VBox header = createHeader();
        VBox basicInfo = createBasicInfo();
        VBox specialTraits = createSpecialTraitsSection();
        HBox prosAndCons = createProsAndConsSection();
        VBox activities = createActivitiesSection();

        ScrollPane scroll = new ScrollPane();
        VBox content = new VBox(20,
                header,
                new Separator(),
                basicInfo,
                new Separator(),
                specialTraits,
                prosAndCons,
                new Separator(),
                activities
        );
        content.setPadding(new Insets(10));
        scroll.setContent(content);
        scroll.setFitToWidth(true);
        getChildren().add(scroll);
    }

    private VBox createHeader() {
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView();
        imageView.setFitWidth(400);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 2);");

        try {
            String imagePath = breed.getImagePath();
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            imageView.setImage(image);
        } catch (Exception e) {
            imageView.setStyle("-fx-background-color: #e0e0e0;");
            System.out.println("Зображення не знайдено для породи: " + breed.getName());
        }

        Label name = new Label(breed.getName());
        name.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #4b3621;");
        Label category = new Label(breed.getCategoryUkr() + " • " + breed.getSizeDescription());
        category.setStyle("-fx-font-size: 16px; -fx-text-fill: #4b3621;");

        header.getChildren().addAll(name, imageView, category);
        return header;
    }

    private VBox createBasicInfo() {
        VBox section = new VBox(15);
        Label title = new Label("Основна інформація");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #4b3621;");
        Label description = new Label(breed.getDescription());
        description.setWrapText(true);
        description.setStyle("-fx-font-size: 14px; -fx-text-fill: #4b3621;");

        GridPane specs = new GridPane();
        specs.setHgap(15);
        specs.setVgap(8);

        int row = 0;
        addSpecRow(specs, row++, "Розмір:", breed.getSizeDescription());
        addSpecRow(specs, row++, "Активність:", breed.getActivityDescription());
        addSpecRow(specs, row++, "Досвід:", breed.getExperienceDescription());
        addSpecRow(specs, row++, "Час догляду:", breed.getCareTimeNeeded() + " год/день");
        addSpecRow(specs, row++, "З дітьми:", breed.isGoodWithKids() ? "Так" : "Ні");
        addSpecRow(specs, row++, "Житло:", breed.getSuitableHome());

        HBox badges = new HBox(10);
        if (breed.isApartmentFriendly()) {
            badges.getChildren().add(createBadge("Для квартири", "#ffecd1"));
        }
        if (breed.isBeginnerFriendly()) {
            badges.getChildren().add(createBadge("Для новачків", "#ffecd1"));
        }
        if (breed.isHighMaintenance()) {
            badges.getChildren().add(createBadge("Потребує часу", "#ffecd1"));
        }

        section.getChildren().addAll(title, description, specs, badges);
        return section;
    }

    private VBox createSpecialTraitsSection() {
        VBox section = new VBox(10);

        Label title = new Label("Спеціальні характеристики");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        VBox specifics = new VBox(10);

        if (breed instanceof HerdingBreed) {
            HerdingBreed hb = (HerdingBreed) breed;
            specifics.getChildren().add(createInfoBox("Пастуша порода", "#E8EAF6",
                    "Інтелект: " + hb.getIntelligenceLevel() + "/100 (" + hb.getIntelligenceDescription() + ")",
                    "Розумова стимуляція: " + (hb.needsMentalStimulation() ? "Потрібна" : "Не обов'язкова")
            ));
        } else if (breed instanceof HoundBreed) {
            HoundBreed hb = (HoundBreed) breed;
            specifics.getChildren().add(createInfoBox("Мисливська порода", "#E8EAF6",
                    "Рівень полювання: " + hb.getHuntingLevel(),
                    "Стиль: " + hb.getHuntingStyle(),
                    "Вокалізація: " + hb.getVocalizationLevel()
            ));
        } else if (breed instanceof SportingBreed) {
            SportingBreed sb = (SportingBreed) breed;
            specifics.getChildren().add(createInfoBox("Спортивна порода", "#E8EAF6",
                    "Любить воду: " + (sb.isWaterLover() ? "Так" : "Ні"),
                    "Рівень енергії: " + sb.getEnergyLevel(),
                    "Підходить для змагань: " + (sb.suitableForCompetitions() ? "Так" : "Ні")
            ));
        } else if (breed instanceof ToyBreed) {
            ToyBreed tb = (ToyBreed) breed;
            specifics.getChildren().add(createInfoBox("Декоративна порода", "#E8EAF6",
                    "Портативність: " + (tb.isPortable() ? "Легко носити" : "Низька"),
                    "Вага: " + tb.getWeightRange(),
                    "Для подорожей: " + (tb.travelFriendly() ? "Так" : "Ні")
            ));
        } else if (breed instanceof WorkingBreed) {
            WorkingBreed wb = (WorkingBreed) breed;
            specifics.getChildren().add(createInfoBox("Робоча порода", "#E8EAF6",
                    "Тип роботи: " + wb.getWorkType(),
                    "Витривалість: " + (wb.isHighEndurance() ? "Висока" : "Середня"),
                    "Сила: " + wb.getStrengthLevel()
            ));
        }

        section.getChildren().addAll(title, specifics);
        return section;
    }

    private VBox createInfoBox(String title, String bgColor, String... items) {
        VBox box = new VBox(5);
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 8;");

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #4b3621;");
        box.getChildren().add(titleLabel);

        for (String item : items) {
            Label label = new Label("• " + item);
            label.setWrapText(true);
            box.getChildren().add(label);
        }

        return box;
    }

    private HBox createProsAndConsSection() {
        HBox container = new HBox(20);
        container.setAlignment(Pos.TOP_CENTER);

        VBox prosBox = new VBox(10);
        prosBox.setPrefWidth(350);
        prosBox.setPadding(new Insets(15));
        prosBox.setStyle("-fx-background-color: #E8F5E9; -fx-background-radius: 10;");

        Label prosTitle = new Label("Переваги");
        prosTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #4b3621;");

        VBox prosList = new VBox(5);
        for (String adv : getAdvantagesForBreed()) {
            Label label = new Label("• " + adv);
            label.setWrapText(true);
            prosList.getChildren().add(label);
        }

        prosBox.getChildren().addAll(prosTitle, prosList);

        VBox consBox = new VBox(10);
        consBox.setPrefWidth(350);
        consBox.setPadding(new Insets(15));
        consBox.setStyle("-fx-background-color: #ffe0e0; -fx-background-radius: 10;");

        Label consTitle = new Label("Виклики");
        consTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #4b3621;");

        VBox consList = new VBox(5);
        for (String challenge : getChallengesForBreed()) {
            Label label = new Label("• " + challenge);
            label.setWrapText(true);
            consList.getChildren().add(label);
        }

        consBox.getChildren().addAll(consTitle, consList);
        container.getChildren().addAll(prosBox, consBox);
        return container;
    }

    private VBox createActivitiesSection() {
        VBox section = new VBox(10);
        Label title = new Label("Рекомендовані активності");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #4b3621;");
        FlowPane flowPane = new FlowPane(10, 10);
        for (String activity : getActivitiesForBreed()) {
            Label badge = createBadge(activity, "#bde2ff");
            flowPane.getChildren().add(badge);
        }

        section.getChildren().addAll(title, flowPane);
        return section;
    }

    private String[] getAdvantagesForBreed() {
        if (breed instanceof HoundBreed) {
            return ((HoundBreed) breed).getAdvantages();
        } else if (breed instanceof SportingBreed) {
            return ((SportingBreed) breed).getAdvantages();
        } else if (breed instanceof WorkingBreed) {
            return ((WorkingBreed) breed).getAdvantages();
        }
        return new String[]{"Дружелюбна", "Віддана", "Чудовий компаньйон"};
    }

    private String[] getChallengesForBreed() {
        if (breed instanceof HoundBreed) {
            return ((HoundBreed) breed).getCommonChallenges();
        } else if (breed instanceof ToyBreed) {
            return ((ToyBreed) breed).getChallenges();
        } else if (breed instanceof WorkingBreed) {
            return ((WorkingBreed) breed).getChallenges();
        }
        return new String[]{"Потребує регулярного догляду", "Важливе навчання"};
    }

    private String[] getActivitiesForBreed() {
        if (breed instanceof SportingBreed) {
            return ((SportingBreed) breed).getRecommendedActivities();
        } else if (breed instanceof WorkingBreed) {
            return ((WorkingBreed) breed).getRecommendedActivities();
        }
        return new String[]{"Прогулянки", "Ігри", "Навчання", "Соціалізація"};
    }

    private void addSpecRow(GridPane grid, int row, String label, String value) {
        Label keyLabel = new Label(label);
        keyLabel.setStyle("-fx-font-weight: bold;");
        Label valueLabel = new Label(value);
        grid.add(keyLabel, 0, row);
        grid.add(valueLabel, 1, row);
    }

    private Label createBadge(String text, String color) {
        Label badge = new Label(text);
        badge.setStyle(String.format(
                "-fx-background-color: %s; -fx-text-fill: #4b3621; " +
                        "-fx-padding: 5 10; -fx-background-radius: 15; -fx-font-size: 12px;", color));
        return badge;
    }
}