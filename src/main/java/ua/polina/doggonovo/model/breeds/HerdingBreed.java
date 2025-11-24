package ua.polina.doggonovo.model.breeds;

public class HerdingBreed extends DogBreed {
    private int intelligenceLevel;

    public HerdingBreed(String ukrainianName, String large, int i, int i1, int i2, boolean b, String будинок, String s, int i3, String image) {
        super();
        this.intelligenceLevel = 85;
    }

    public HerdingBreed(String name, String size, int activityLevel,
                        int requiredExperience, int careTimeNeeded,
                        boolean goodWithKids, String suitableHome, String description,
                        int intelligenceLevel) {
        super(name, size, activityLevel, requiredExperience, careTimeNeeded,
                goodWithKids, suitableHome, description);
        this.intelligenceLevel = intelligenceLevel;
    }

    public int getIntelligenceLevel() {
        return intelligenceLevel;
    }

    @Override
    public String getCategory() {
        return "Herding";
    }

    @Override
    public String getSpecialTraits() {
        StringBuilder traits = new StringBuilder();
        traits.append("Пастуша порода\n");
        traits.append(String.format("Інтелект: %d/100 (%s)\n",
                intelligenceLevel, getIntelligenceDescription()));

        if (intelligenceLevel >= 90) {
            traits.append("• Надзвичайно розумна, легко навчається\n");
        } else if (intelligenceLevel >= 75) {
            traits.append("• Дуже розумна, швидко навчається\n");
        }

        traits.append("• Має інстинкт пастуха\n");
        traits.append("• Відданá та працелюбна\n");

        return traits.toString();
    }

    public String getIntelligenceDescription() {
        if (intelligenceLevel >= 95) {
            return "Геній";
        } else if (intelligenceLevel >= 85) {
            return "Дуже високий";
        } else if (intelligenceLevel >= 70) {
            return "Високий";
        } else {
            return "Середній";
        }
    }

    public boolean needsMentalStimulation() {
        return intelligenceLevel >= 75;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof HerdingBreed)) return false;
        HerdingBreed other = (HerdingBreed) obj;
        return intelligenceLevel == other.intelligenceLevel;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + intelligenceLevel;
    }
}