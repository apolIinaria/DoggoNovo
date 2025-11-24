package ua.polina.doggonovo.model.breeds;

public class HoundBreed extends DogBreed {
    private String huntingLevel;
    private boolean apartmentSuitable;

    public HoundBreed() {
        super();
        this.huntingLevel = "Середній";
        this.apartmentSuitable = true;
    }

    public HoundBreed(String name, String size, int activityLevel,
                      int requiredExperience, int careTimeNeeded,
                      boolean goodWithKids, String suitableHome, String description,
                      String huntingLevel, boolean apartmentSuitable) {
        super(name, size, activityLevel, requiredExperience, careTimeNeeded,
                goodWithKids, suitableHome, description);
        this.huntingLevel = huntingLevel;
        this.apartmentSuitable = apartmentSuitable;
    }

    public String getHuntingLevel() {
        return huntingLevel;
    }

    public boolean isApartmentSuitable() {
        return apartmentSuitable;
    }

    @Override
    public String getCategory() {
        return "Hound";
    }

    @Override
    public String getSpecialTraits() {
        StringBuilder traits = new StringBuilder();
        traits.append("Мисливська порода\n");
        traits.append(String.format("Рівень полювання: %s\n", huntingLevel));
        traits.append("• Відмінний нюх та слух\n");

        if (huntingLevel.equals("Високий")) {
            traits.append("• Сильний полювальний інстинкт\n");
        }

        traits.append("• Любить слідкувати за запахами\n");
        return traits.toString();
    }

    public String getHuntingStyle() {
        if (size.equals("Small")) {
            return "Норна мисливська собака";
        } else if (activityLevel >= 4) {
            return "Гончак";
        } else {
            return "Пошукова собака";
        }
    }

    public String getVocalizationLevel() {
        if (huntingLevel.equals("Високий")) {
            return "Високий (може гавкати/вити)";
        } else if (huntingLevel.equals("Середній")) {
            return "Середній";
        } else {
            return "Низький";
        }
    }

    public String[] getCommonChallenges() {
        java.util.List<String> challenges = new java.util.ArrayList<>();

        if (huntingLevel.equals("Високий")) {
            challenges.add("Полювальний інстинкт до дрібних тварин");
            challenges.add("Вокалізація (гавкіт, виття)");
        }

        challenges.add("Може бути впертою під час навчання");
        return challenges.toArray(new String[0]);
    }

    public String[] getAdvantages() {
        java.util.List<String> advantages = new java.util.ArrayList<>();
        advantages.add("Дружелюбна та ласкава");

        if (goodWithKids) {
            advantages.add("Чудовий з дітьми");
        }
        if (apartmentSuitable) {
            advantages.add("Може жити в квартирі");
        }

        advantages.add("Цікава особистість");
        return advantages.toArray(new String[0]);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof HoundBreed)) return false;
        HoundBreed other = (HoundBreed) obj;
        return huntingLevel.equals(other.huntingLevel) &&
                apartmentSuitable == other.apartmentSuitable;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + huntingLevel.hashCode();
        result = 31 * result + (apartmentSuitable ? 1 : 0);
        return result;
    }
}