package ua.polina.doggonovo.data;

public enum BreedData {
    // Herding (Пастуші породи)
    GERMAN_SHEPHERD("Німецька вівчарка", "Herding"),
    BORDER_COLLIE("Бордер-колі", "Herding"),
    CORGI("Корги", "Herding"),

    // Hound (Мисливські породи)
    BEAGLE("Бігль", "Hound"),
    DACHSHUND("Такса", "Hound"),
    BASSET("Бассет-хаунд", "Hound"),

    // Sporting (Спортивні породи)
    LABRADOR("Лабрадор", "Sporting"),
    GOLDEN_RETRIEVER("Золотистий ретрівер", "Sporting"),
    COCKER_SPANIEL("Кокер-спанієль", "Sporting"),

    // Toy (Декоративні породи)
    CHIHUAHUA("Чіхуахуа", "Toy"),
    POMERANIAN("Померанський шпіц", "Toy"),
    YORKSHIRE("Йоркширський тер'єр", "Toy"),

    // Working (Робочі породи)
    HUSKY("Сибірський хаскі", "Working"),
    BOXER("Боксер", "Working"),
    BERNESE("Бернський зенненхунд", "Working");

    private final String ukrainianName;
    private final String category;

    BreedData(String ukrainianName, String category) {
        this.ukrainianName = ukrainianName;
        this.category = category;
    }

    public String getUkrainianName() {
        return ukrainianName;
    }

    public String getCategory() {
        return category;
    }

    public static String getCategoryNameUkr(String category) {
        switch (category) {
            case "Herding": return "Пастуша";
            case "Hound": return "Мисливська";
            case "Sporting": return "Спортивна";
            case "Toy": return "Декоративна";
            case "Working": return "Робоча";
            default: return category;
        }
    }

    public static BreedData[] getByCategory(String category) {
        return java.util.Arrays.stream(values())
                .filter(breed -> breed.category.equals(category))
                .toArray(BreedData[]::new);
    }

    @Override
    public String toString() {
        return ukrainianName;
    }
}
