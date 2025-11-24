package ua.polina.doggonovo.model.breeds;

import ua.polina.doggonovo.data.BreedData;
import ua.polina.doggonovo.utils.Constants;

public abstract class DogBreed {
    protected String name;
    protected String size;
    protected int activityLevel;
    protected int requiredExperience;
    protected int careTimeNeeded;
    protected boolean goodWithKids;
    protected String suitableHome;
    protected String description;

    public DogBreed() {
        this.name = "Невідома порода";
        this.size = "Medium";
        this.activityLevel = 3;
        this.requiredExperience = 2;
        this.careTimeNeeded = 2;
        this.goodWithKids = true;
        this.suitableHome = "Квартира";
        this.description = "Опис відсутній";
    }

    public DogBreed(String name, String size, int activityLevel,
                    int requiredExperience, int careTimeNeeded,
                    boolean goodWithKids, String suitableHome, String description) {
        this.name = name;
        this.size = size;
        this.activityLevel = activityLevel;
        this.requiredExperience = requiredExperience;
        this.careTimeNeeded = careTimeNeeded;
        this.goodWithKids = goodWithKids;
        this.suitableHome = suitableHome;
        this.description = description;
    }

    public String getName() { return name; }
    public String getSize() { return size; }
    public int getActivityLevel() { return activityLevel; }
    public int getRequiredExperience() { return requiredExperience; }
    public int getCareTimeNeeded() { return careTimeNeeded; }
    public boolean isGoodWithKids() { return goodWithKids; }
    public String getSuitableHome() { return suitableHome; }
    public String getDescription() { return description; }

    public abstract String getCategory();
    public abstract String getSpecialTraits();

    public String getImagePath() {
        String category = getCategory();
        String imageName = getImageFileName();
        switch (getCategory()) {
            case "Herding": return Constants.HERDING_IMAGES + imageName;
            case "Hound": return Constants.HOUND_IMAGES + imageName;
            case "Sporting": return Constants.SPORTING_IMAGES + imageName;
            case "Toy": return Constants.TOY_IMAGES + imageName;
            case "Working": return Constants.WORKING_IMAGES + imageName;
            default: return Constants.DEFAULT_IMAGES + imageName;
        }
    }

    private String getImageFileName() {
        switch (name) {
            // Herding
            case "Німецька вівчарка": return "german_shepherd.jpg";
            case "Бордер-колі": return "border_collie.jpg";
            case "Корги": return "corgi.jpg";
            // Hound
            case "Бігль": return "beagle.jpeg";
            case "Такса": return "dachshund.jpg";
            case "Бассет-хаунд": return "basset.jpg";
            // Sporting
            case "Лабрадор": return "labrador.jpg";
            case "Золотистий ретрівер": return "golden_retriever.jpg";
            case "Кокер-спанієль": return "cocker_spaniel.jpg";
            // Toy
            case "Чіхуахуа": return "chihuahua.jpg";
            case "Померанський шпіц": return "pomeranian.jpg";
            case "Йоркширський тер'єр": return "yorkshire.jpg";
            // Working
            case "Сибірський хаскі": return "husky.jpg";
            case "Боксер": return "boxer.jpg";
            case "Бернський зенненхунд": return "bernese.jpg";
            default: return "default.png";
        }
    }

    public String getCategoryUkr() {
        return BreedData.getCategoryNameUkr(getCategory());
    }
    public String getSizeDescription() {
        switch (size) {
            case "Small": return "Маленька (до 10 кг)";
            case "Medium": return "Середня (10-25 кг)";
            case "Large": return "Велика (25+ кг)";
            default: return "Невідомо";
        }
    }

    public String getActivityDescription() {
        switch (activityLevel) {
            case 1: return "Дуже низька (диванний любимець)";
            case 2: return "Низька (короткі прогулянки)";
            case 3: return "Середня (щоденні прогулянки)";
            case 4: return "Висока (активні ігри, біг)";
            case 5: return "Дуже висока (потребує багато руху)";
            default: return "Невідомо";
        }
    }

    public String getExperienceDescription() {
        switch (requiredExperience) {
            case 1: return "Ідеально для новачків";
            case 2: return "Підходить для новачків";
            case 3: return "Потрібен середній досвід";
            case 4: return "Для досвідчених власників";
            case 5: return "Тільки для експертів";
            default: return "Невідомо";
        }
    }

    public String getShortDescription() {
        if (description.length() <= 100) {
            return description;
        }
        return description.substring(0, 97) + "...";
    }

    public boolean isApartmentFriendly() {
        return suitableHome.equals("Квартира") || size.equals("Small");
    }

    public boolean isBeginnerFriendly() {
        return requiredExperience <= 2;
    }

    public boolean isHighMaintenance() {
        return careTimeNeeded >= 3;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof DogBreed)) return false;
        DogBreed other = (DogBreed) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s [%s] - %s", name, getCategory(), getSizeDescription());
    }
}