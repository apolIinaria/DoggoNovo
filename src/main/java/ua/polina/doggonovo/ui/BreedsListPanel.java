package ua.polina.doggonovo.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import ua.polina.doggonovo.data.BreedRepository;
import ua.polina.doggonovo.model.breeds.DogBreed;
import java.util.List;

public class BreedsListPanel extends VBox {
    private BreedRepository repository;
    private FlowPane cardsContainer;
    private ComboBox<String> categoryFilter;
    private TextField searchField;

    public BreedsListPanel(BreedRepository repository) {
        this.repository = repository;
        setSpacing(15);
        setPadding(new Insets(20));
        createContent();
        displayBreeds(repository.getAllBreeds());
    }

    private void createContent() {
        Label title = new Label("Всі породи собак");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #4b3621;");

        HBox filters = new HBox(10);
        filters.setAlignment(Pos.CENTER_LEFT);

        searchField = new TextField();
        searchField.setPromptText("Пошук...");
        searchField.setPrefWidth(200);
        searchField.textProperty().addListener((obs, old, val) -> applyFilters());

        categoryFilter = new ComboBox<>();
        categoryFilter.getItems().addAll(
                "Всі",
                "Пастуша",
                "Мисливська",
                "Спортивна",
                "Декоративна",
                "Робоча"
        );
        categoryFilter.setValue("Всі");
        categoryFilter.setOnAction(e -> applyFilters());

        filters.getChildren().addAll(new Label("Пошук:"), searchField, new Label("Категорія:"), categoryFilter);

        cardsContainer = new FlowPane();
        cardsContainer.setHgap(15);
        cardsContainer.setVgap(15);
        cardsContainer.setPadding(new Insets(10));

        ScrollPane scroll = new ScrollPane(cardsContainer);
        scroll.setFitToWidth(true);
        VBox.setVgrow(scroll, Priority.ALWAYS);

        getChildren().addAll(title, filters, new Separator(), scroll);
    }

    private void applyFilters() {
        String search = searchField.getText().toLowerCase();
        String categoryUkr = categoryFilter.getValue();
        String category = convertUkrToEngCategory(categoryUkr);

        List<DogBreed> filtered = repository.getAllBreeds().stream()
                .filter(b -> search.isEmpty() || b.getName().toLowerCase().contains(search))
                .filter(b -> category.equals("Всі") || b.getCategory().equals(category))
                .toList();

        displayBreeds(filtered);
    }

    private String convertUkrToEngCategory(String ukrCategory) {
        switch (ukrCategory) {
            case "Пастуша": return "Herding";
            case "Мисливська": return "Hound";
            case "Спортивна": return "Sporting";
            case "Декоративна": return "Toy";
            case "Робоча": return "Working";
            default: return "Всі";
        }
    }

    private void displayBreeds(List<DogBreed> breeds) {
        cardsContainer.getChildren().clear();
        for (DogBreed breed : breeds) {
            cardsContainer.getChildren().add(new BreedCardPanel(breed));
        }
    }
}