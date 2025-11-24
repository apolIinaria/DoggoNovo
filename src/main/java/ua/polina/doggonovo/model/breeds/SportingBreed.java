package ua.polina.doggonovo.model.breeds;

public class SportingBreed extends DogBreed {
    private boolean waterLover;
    private String sportType;

    public SportingBreed() {
        super();
        this.waterLover = true;
        this.sportType = "Універсальний";
    }

    public SportingBreed(String name, String size, int activityLevel,
                         int requiredExperience, int careTimeNeeded,
                         boolean goodWithKids, String suitableHome, String description,
                         boolean waterLover, String sportType) {
        super(name, size, activityLevel, requiredExperience, careTimeNeeded,
                goodWithKids, suitableHome, description);
        this.waterLover = waterLover;
        this.sportType = sportType;
    }

    public boolean isWaterLover() {
        return waterLover;
    }

    public String getSportType() {
        return sportType;
    }

    @Override
    public String getCategory() {
        return "Sporting";
    }

    @Override
    public String getSpecialTraits() {
        StringBuilder traits = new StringBuilder();
        traits.append("Спортивна порода\n");
        traits.append(String.format("Тип спорту: %s\n", sportType));
        traits.append("• Дуже енергійна та активна\n");
        traits.append("• Обожнює гратися та аппортувати\n");

        if (waterLover) {
            traits.append("• Любить плавати та гратися у воді\n");
        }

        traits.append("• Дружелюбна та соціальна\n");
        return traits.toString();
    }

    public boolean suitableForCompetitions() {
        return activityLevel >= 4 && requiredExperience >= 2;
    }

    public String getEnergyLevel() {
        if (activityLevel >= 5) {
            return "Надвисокий";
        } else if (activityLevel >= 4) {
            return "Високий";
        } else if (activityLevel >= 3) {
            return "Середній";
        } else {
            return "Низький";
        }
    }

    public String[] getRecommendedActivities() {
        java.util.List<String> activities = new java.util.ArrayList<>();
        activities.add("Аппортування м'яча");
        activities.add("Фрізбі");
        activities.add("Біг з господарем");

        if (waterLover) {
            activities.add("Плавання");
            activities.add("Ігри у воді");
        }

        if (suitableForCompetitions()) {
            activities.add("Аджиліті");
            activities.add("Обідієнс");
        }

        activities.add("Довгі прогулянки");
        return activities.toArray(new String[0]);
    }

    public String[] getAdvantages() {
        java.util.List<String> advantages = new java.util.ArrayList<>();
        advantages.add("Дружелюбна та ласкава");
        advantages.add("Легко навчається");
        advantages.add("Відданий компаньйон");

        if (goodWithKids) {
            advantages.add("Чудовий з дітьми");
        }
        if (waterLover) {
            advantages.add("Любить водні активності");
        }

        advantages.add("Соціальна");
        return advantages.toArray(new String[0]);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof SportingBreed)) return false;
        SportingBreed other = (SportingBreed) obj;
        return waterLover == other.waterLover &&
                sportType.equals(other.sportType);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (waterLover ? 1 : 0);
        result = 31 * result + sportType.hashCode();
        return result;
    }
}