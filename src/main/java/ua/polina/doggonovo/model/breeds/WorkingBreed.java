package ua.polina.doggonovo.model.breeds;

public class WorkingBreed extends DogBreed {

    private String workType;
    private boolean highEndurance;

    public WorkingBreed() {
        super();
        this.workType = "Охоронний";
        this.highEndurance = true;
    }

    public WorkingBreed(String name, String size, int activityLevel,
                        int requiredExperience, int careTimeNeeded,
                        boolean goodWithKids, String suitableHome, String description,
                        String workType, boolean highEndurance) {
        super(name, size, activityLevel, requiredExperience, careTimeNeeded,
                goodWithKids, suitableHome, description);
        this.workType = workType;
        this.highEndurance = highEndurance;
    }

    public String getWorkType() {
        return workType;
    }

    public boolean isHighEndurance() {
        return highEndurance;
    }

    @Override
    public String getCategory() {
        return "Working";
    }

    @Override
    public String getSpecialTraits() {
        StringBuilder traits = new StringBuilder();
        traits.append("Робоча порода\n");
        traits.append(String.format("Тип роботи: %s\n", workType));

        switch (workType) {
            case "Їздовий":
                traits.append("• Створена для перевезення вантажів\n");
                traits.append("• Надзвичайна витривалість\n");
                break;
            case "Охоронний":
                traits.append("• Природний інстинкт захисту\n");
                traits.append("• Відважна та віддана\n");
                break;
            case "Тягловий":
                traits.append("• Надзвичайна сила\n");
                traits.append("• Спокійний характер\n");
                break;
        }

        traits.append("• Потужна будова\n");
        return traits.toString();
    }

    public String getStrengthLevel() {
        if (workType.equals("Тягловий")) {
            return "Надвисокий";
        } else if (size.equals("Large") && highEndurance) {
            return "Дуже високий";
        } else {
            return "Високий";
        }
    }

    public String[] getRecommendedActivities() {
        java.util.List<String> activities = new java.util.ArrayList<>();

        switch (workType) {
            case "Їздовий":
                activities.add("Їзда на велосипеді з собакою");
                activities.add("Каніскрос");
                activities.add("Довгі походи");
                break;
            case "Охоронний":
                activities.add("Навчання послуху");
                activities.add("Аджиліті");
                activities.add("Патрулювання території");
                break;
            case "Тягловий":
                activities.add("Вейтпуллінг");
                activities.add("Картинг");
                activities.add("Довгі прогулянки");
                break;
        }

        activities.add("Навчання командам");
        activities.add("Соціалізація");
        return activities.toArray(new String[0]);
    }

    public String[] getChallenges() {
        java.util.List<String> challenges = new java.util.ArrayList<>();

        if (requiredExperience >= 3) {
            challenges.add("Потрібен досвідчений власник");
        }
        if (size.equals("Large")) {
            challenges.add("Великий розмір - потребує простору");
            challenges.add("Високі витрати на утримання");
        }
        if (workType.equals("Охоронний")) {
            challenges.add("Потребує ранньої соціалізації");
        }
        if (highEndurance && activityLevel >= 4) {
            challenges.add("Потребує багато часу та енергії");
        }

        return challenges.toArray(new String[0]);
    }

    public String[] getAdvantages() {
        java.util.List<String> advantages = new java.util.ArrayList<>();
        advantages.add("Сильна та витривала");
        advantages.add("Віддана та захисна");

        if (goodWithKids) {
            advantages.add("Добра з дітьми");
        }
        if (workType.equals("Охоронний")) {
            advantages.add("Відмінний охоронець");
        }

        advantages.add("Вражаюча робоча етика");
        return advantages.toArray(new String[0]);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof WorkingBreed)) return false;
        WorkingBreed other = (WorkingBreed) obj;
        return workType.equals(other.workType) &&
                highEndurance == other.highEndurance;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + workType.hashCode();
        result = 31 * result + (highEndurance ? 1 : 0);
        return result;
    }
}