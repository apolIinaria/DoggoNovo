package ua.polina.doggonovo.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import ua.polina.doggonovo.model.UserProfile;

public class UserFormPanel extends VBox {
    private MainWindow mainWindow;
    private TextField nameField;
    private Slider activitySlider;
    private ComboBox<Integer> experienceCombo;
    private RadioButton apartmentRadio;
    private Spinner<Integer> freeTimeSpinner;
    private CheckBox hasKidsCheckBox;
    private ComboBox<String> sizeCombo;

    public UserFormPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setSpacing(15);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);
        setMaxWidth(600);
        createForm();
    }

    private void createForm() {
        Label title = new Label("Заповніть анкету");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #4b3621;");
        nameField = new TextField();
        nameField.setPromptText("Ваше ім'я");
        activitySlider = new Slider(1, 5, 3);
        activitySlider.setShowTickLabels(true);
        activitySlider.setShowTickMarks(true);
        activitySlider.setMajorTickUnit(1);
        activitySlider.setSnapToTicks(true);
        Label activityDesc = new Label(getActivityDescription(3));
        activityDesc.setStyle("-fx-font-size: 12px; -fx-text-fill: #4b3621; -fx-font-style: italic;");
        activityDesc.setWrapText(true);
        activitySlider.valueProperty().addListener((obs, old, val) -> {
            activityDesc.setText(getActivityDescription(val.intValue()));
        });
        VBox activityBox = new VBox(5, activitySlider, activityDesc);

        experienceCombo = new ComboBox<>();
        experienceCombo.getItems().addAll(1, 2, 3, 4, 5);
        experienceCombo.setValue(2);
        Label experienceDesc = new Label(getExperienceDescription(2));
        experienceDesc.setStyle("-fx-font-size: 12px; -fx-text-fill: #4b3621; -fx-font-style: italic;");
        experienceDesc.setWrapText(true);
        experienceCombo.setOnAction(e -> {
            experienceDesc.setText(getExperienceDescription(experienceCombo.getValue()));
        });
        VBox experienceBox = new VBox(5, experienceCombo, experienceDesc);

        ToggleGroup livingGroup = new ToggleGroup();
        apartmentRadio = new RadioButton("Квартира");
        apartmentRadio.setToggleGroup(livingGroup);
        apartmentRadio.setSelected(true);
        RadioButton houseRadio = new RadioButton("Будинок");
        houseRadio.setToggleGroup(livingGroup);
        HBox livingBox = new HBox(20, apartmentRadio, houseRadio);

        freeTimeSpinner = new Spinner<>(0, 24, 2);
        freeTimeSpinner.setEditable(true);
        Label timeDesc = new Label(getTimeDescription(2));
        timeDesc.setStyle("-fx-font-size: 12px; -fx-text-fill: #4b3621; -fx-font-style: italic;");
        timeDesc.setWrapText(true);
        freeTimeSpinner.valueProperty().addListener((obs, old, val) -> {
            timeDesc.setText(getTimeDescription(val));
        });
        VBox timeBox = new VBox(5, freeTimeSpinner, timeDesc);

        hasKidsCheckBox = new CheckBox("Є діти");
        HBox kidsBox = new HBox(hasKidsCheckBox);
        kidsBox.setAlignment(Pos.CENTER_LEFT);

        sizeCombo = new ComboBox<>();
        sizeCombo.getItems().addAll(
                "Small - Маленька (до 10 кг)",
                "Medium - Середня (10-25 кг)",
                "Large - Велика (понад 25 кг)"
        );
        sizeCombo.setValue("Medium - Середня (10-25 кг)");

        Button saveBtn = new Button("Зберегти і підібрати");
        saveBtn.setStyle("-fx-background-color: #e8c3af; -fx-text-fill: #4b3621; -fx-font-size: 14px; -fx-padding: 10 20;");
        saveBtn.setOnAction(e -> save());

        getChildren().addAll(
                title,
                new Separator(),
                createField("Ім'я (за бажанням):", nameField),
                createField("Рівень активності (1-5):", activityBox),
                createField("Досвід (1-5):", experienceBox),
                createField("Тип житла:", livingBox),
                createField("Вільний час (год/день):", timeBox),
                hasKidsCheckBox,
                createField("Розмір:", sizeCombo),
                new Separator(),
                saveBtn
        );
    }

    private String getActivityDescription(int level) {
        switch (level) {
            case 1: return "Дуже низька - домосід, мінімум руху, короткі прогулянки";
            case 2: return "Низька - рідкі короткі прогулянки";
            case 3: return "Середня - щоденні прогулянки";
            case 4: return "Висока - активні прогулянки, спорт, біг";
            case 5: return "Дуже висока - щоденні інтенсивні тренування";
            default: return "";
        }
    }

    private String getExperienceDescription(int level) {
        switch (level) {
            case 1: return "Без досвіду - перша собака";
            case 2: return "Мінімальний досвід - мав собаку або допомагав у догляді";
            case 3: return "Середній досвід - самостійно доглядав за собакою";
            case 4: return "Досвідчений власник - маю досвід з різними породами";
            case 5: return "Експерт - професійний досвід (дресирувальник/кінолог)";
            default: return "";
        }
    }

    private String getTimeDescription(int hours) {
        if (hours <= 1) return "Майже немає часу";
        if (hours <= 3) return "Невеликий запас часу";
        if (hours <= 6) return "Середня кількість часу";
        return "Багато часу";
    }

    private VBox createField(String labelText, javafx.scene.Node control) {
        VBox box = new VBox(5);
        Label label = new Label(labelText);
        label.setStyle("-fx-font-weight: bold;");
        box.getChildren().addAll(label, control);
        return box;
    }

    private void save() {
        String name = nameField.getText().isEmpty() ? "Користувач" : nameField.getText();
        int activity = (int) activitySlider.getValue();
        int experience = experienceCombo.getValue();
        String living = apartmentRadio.isSelected() ? "Квартира" : "Будинок";
        int time = freeTimeSpinner.getValue();
        boolean kids = hasKidsCheckBox.isSelected();

        String sizeText = sizeCombo.getValue();
        String size = sizeText.split(" - ")[0];

        UserProfile profile = new UserProfile(name, activity, experience, living, time, kids, size);
        mainWindow.saveUserProfile(profile);
    }
}