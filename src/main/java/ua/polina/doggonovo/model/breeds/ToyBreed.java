package ua.polina.doggonovo.model.breeds;

public class ToyBreed extends DogBreed {

    private boolean portable;
    private String companionType;

    public ToyBreed() {
        super();
        this.portable = true;
        this.companionType = "Компаньйон";
        this.size = "Small";
    }

    public ToyBreed(String name, String size, int activityLevel,
                    int requiredExperience, int careTimeNeeded,
                    boolean goodWithKids, String suitableHome, String description,
                    boolean portable, String companionType) {
        super(name, size, activityLevel, requiredExperience, careTimeNeeded,
                goodWithKids, suitableHome, description);
        this.portable = portable;
        this.companionType = companionType;
        this.size = "Small";
    }

    public boolean isPortable() {
        return portable;
    }

    public String getCompanionType() {
        return companionType;
    }

    @Override
    public String getCategory() {
        return "Toy";
    }

    @Override
    public String getSpecialTraits() {
        StringBuilder traits = new StringBuilder();
        traits.append("Декоративна порода\n");
        traits.append(String.format("Тип: %s\n", companionType));
        traits.append("• Компактний розмір (ідеально для квартири)\n");
        traits.append("• Відданий компаньйон\n");

        if (portable) {
            traits.append("• Можна брати скрізь з собою\n");
        }

        if (!goodWithKids) {
            traits.append("• Обережно з маленькими дітьми\n");
        }

        return traits.toString();
    }

    public boolean travelFriendly() {
        return portable;
    }

    public String getWeightRange() {
        if (companionType.equals("Декоративний")) {
            return "1-3 кг";
        } else {
            return "3-8 кг";
        }
    }

    public String[] getChallenges() {
        java.util.List<String> challenges = new java.util.ArrayList<>();

        if (!goodWithKids) {
            challenges.add("Крихка будова - легко травмуватися");
        }

        challenges.add("Може бути голосною");
        challenges.add("Часто важко привчити до туалету");
        challenges.add("Може мерзнути в холод");

        if (careTimeNeeded >= 2) {
            challenges.add("Потребує регулярного догляду за шерстю");
        }

        if (!goodWithKids) {
            challenges.add("Не рекомендується для сімей з маленькими дітьми");
        }

        return challenges.toArray(new String[0]);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof ToyBreed)) return false;
        ToyBreed other = (ToyBreed) obj;
        return portable == other.portable &&
                companionType.equals(other.companionType);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (portable ? 1 : 0);
        result = 31 * result + companionType.hashCode();
        return result;
    }
}