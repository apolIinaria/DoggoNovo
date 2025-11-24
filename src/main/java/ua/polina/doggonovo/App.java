package ua.polina.doggonovo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ua.polina.doggonovo.ui.MainWindow;
import ua.polina.doggonovo.utils.Constants;
import ua.polina.doggonovo.utils.StyleManager;

public class App extends Application {
    private MainWindow mainWindow;

    @Override
    public void start(Stage primaryStage) {
        try {
            setupStage(primaryStage);
            mainWindow = new MainWindow();
            Scene scene = new Scene(mainWindow, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

            primaryStage.setScene(scene);
            primaryStage.show();
            showWelcomeMessage();

            System.out.println(" " + Constants.APP_NAME + " v" + Constants.APP_VERSION + " запущено успішно!");

        } catch (Exception e) {
            System.err.println("Помилка запуску програми:");
            e.printStackTrace();
            StyleManager.showErrorDialog(
                    "Помилка запуску",
                    "Не вдалося запустити програму.\n\nДеталі: " + e.getMessage()
            );
        }
    }

    private void setupStage(Stage stage) {
        stage.setTitle(Constants.TITLE_MAIN);
        stage.setMinWidth(Constants.MIN_WINDOW_WIDTH);
        stage.setMinHeight(Constants.MIN_WINDOW_HEIGHT);

        try {
            Image icon = new Image(getClass().getResourceAsStream(Constants.LOGO_PATH));
            stage.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println("Іконка програми не знайдена");
        }

        stage.setOnCloseRequest(event -> {
            if (confirmExit()) {
                System.out.println("Програма закривається...");
                cleanup();
            } else {
                event.consume();
            }
        });
    }

    private void showWelcomeMessage() {
        System.out.println("Ласкаво просимо до " + Constants.APP_NAME + "!");
        System.out.println(" " + Constants.APP_DESCRIPTION);
    }

    private boolean confirmExit() {
        return true;
    }

    private void cleanup() {
        System.out.println("Очищення ресурсів...");
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("Ініціалізація програми...");

        if (!Constants.validateWeights()) {
            System.err.println("УВАГА: Сума ваг алгоритму не дорівнює 1.0!");
        }
        initializeResources();
    }

    private void initializeResources() {
        System.out.println("📦 Завантаження ресурсів...");
        checkImageDirectories();
    }

    private void checkImageDirectories() {
        String[] directories = {
                Constants.HERDING_IMAGES,
                Constants.HOUND_IMAGES,
                Constants.SPORTING_IMAGES,
                Constants.TOY_IMAGES,
                Constants.WORKING_IMAGES
        };

        System.out.println("Перевірка директорій:");
        for (String dir : directories) {
            System.out.println("   - " + dir + " (не перевіряється на етапі розробки)");
        }
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Зупинка програми...");
        super.stop();
    }
}