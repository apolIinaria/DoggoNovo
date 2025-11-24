package ua.polina.doggonovo.utils;

public class Constants {
    public static final String APP_NAME = "DoggoNovo";
    public static final String APP_VERSION = "1.0.0";
    public static final String APP_DESCRIPTION = "Розумний помічник для підбору породи собаки";
    public static final String APP_AUTHOR = "Поліна Новомлинець";

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    public static final int MIN_WINDOW_WIDTH = 1000;
    public static final int MIN_WINDOW_HEIGHT = 700;

    public static final double WEIGHT_ACTIVITY = 0.25;
    public static final double WEIGHT_EXPERIENCE = 0.20;
    public static final double WEIGHT_LIVING_SPACE = 0.15;
    public static final double WEIGHT_FREE_TIME = 0.15;
    public static final double WEIGHT_KIDS = 0.15;
    public static final double WEIGHT_SIZE = 0.10;

    public static final String LOGO_PATH = "/images/logo.png";
    public static final String IMAGES_PATH = "/images/breeds/";
    public static final String HERDING_IMAGES = IMAGES_PATH + "herding/";
    public static final String HOUND_IMAGES = IMAGES_PATH + "hound/";
    public static final String SPORTING_IMAGES = IMAGES_PATH + "sporting/";
    public static final String TOY_IMAGES = IMAGES_PATH + "toy/";
    public static final String WORKING_IMAGES = IMAGES_PATH + "working/";
    public static final String DEFAULT_IMAGES = IMAGES_PATH + "default.png";

    public static final String TITLE_MAIN = "DoggoNovo - Підбір породи собаки";

    public static boolean validateWeights() {
        double sum = WEIGHT_ACTIVITY + WEIGHT_EXPERIENCE +
                WEIGHT_LIVING_SPACE + WEIGHT_FREE_TIME +
                WEIGHT_KIDS + WEIGHT_SIZE;
        return Math.abs(sum - 1.0) < 0.0001;
    }

    private Constants() {
        throw new UnsupportedOperationException("Це utility клас!");
    }
}