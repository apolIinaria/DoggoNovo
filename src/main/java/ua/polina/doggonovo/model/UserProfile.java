package ua.polina.doggonovo.model;

/**
 * Профіль користувача для підбору породи
 * Спрощено - видалено невикористовувані методи
 */
public class UserProfile {
    private int activityLevel;
    private int experienceLevel;
    private String livingSpace;
    private int freeTimeHours;
    private boolean hasChildren;
    private String preferredSize;
    private String userName;

    public UserProfile() {
        this.activityLevel = 3;
        this.experienceLevel = 2;
        this.livingSpace = "Квартира";
        this.freeTimeHours = 2;
        this.hasChildren = false;
        this.preferredSize = "Medium";
        this.userName = "Користувач";
    }

    public UserProfile(int activityLevel, int experienceLevel, String livingSpace,
                       int freeTimeHours, boolean hasChildren, String preferredSize) {
        this(null, activityLevel, experienceLevel, livingSpace,
                freeTimeHours, hasChildren, preferredSize);
    }

    public UserProfile(String userName, int activityLevel, int experienceLevel,
                       String livingSpace, int freeTimeHours, boolean hasChildren,
                       String preferredSize) {
        this.userName = (userName == null || userName.isEmpty()) ? "Користувач" : userName;
        setActivityLevel(activityLevel);
        setExperienceLevel(experienceLevel);
        setLivingSpace(livingSpace);
        setFreeTimeHours(freeTimeHours);
        this.hasChildren = hasChildren;
        setPreferredSize(preferredSize);
    }

    public int getActivityLevel() { return activityLevel; }
    public int getExperienceLevel() { return experienceLevel; }
    public String getLivingSpace() { return livingSpace; }
    public int getFreeTimeHours() { return freeTimeHours; }
    public boolean hasChildren() { return hasChildren; }
    public String getPreferredSize() { return preferredSize; }
    public String getUserName() { return userName; }

    public void setActivityLevel(int activityLevel) {
        if (activityLevel < 1 || activityLevel > 5) {
            throw new IllegalArgumentException("Рівень активності: 1-5");
        }
        this.activityLevel = activityLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        if (experienceLevel < 1 || experienceLevel > 5) {
            throw new IllegalArgumentException("Рівень досвіду: 1-5");
        }
        this.experienceLevel = experienceLevel;
    }

    public void setLivingSpace(String livingSpace) {
        if (!livingSpace.equals("Квартира") && !livingSpace.equals("Будинок")) {
            throw new IllegalArgumentException("Тип житла: 'Квартира' або 'Будинок'");
        }
        this.livingSpace = livingSpace;
    }

    public void setFreeTimeHours(int freeTimeHours) {
        if (freeTimeHours < 0 || freeTimeHours > 24) {
            throw new IllegalArgumentException("Години: 0-24");
        }
        this.freeTimeHours = freeTimeHours;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public void setPreferredSize(String preferredSize) {
        if (!preferredSize.equals("Small") &&
                !preferredSize.equals("Medium") &&
                !preferredSize.equals("Large")) {
            throw new IllegalArgumentException("Розмір: 'Small', 'Medium' або 'Large'");
        }
        this.preferredSize = preferredSize;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isValid() {
        return activityLevel >= 1 && activityLevel <= 5 &&
                experienceLevel >= 1 && experienceLevel <= 5 &&
                freeTimeHours >= 0 && freeTimeHours <= 24 &&
                (livingSpace.equals("Квартира") || livingSpace.equals("Будинок")) &&
                (preferredSize.equals("Small") || preferredSize.equals("Medium") ||
                        preferredSize.equals("Large"));
    }

    public UserProfile copy() {
        return new UserProfile(userName, activityLevel, experienceLevel,
                livingSpace, freeTimeHours, hasChildren, preferredSize);
    }

    @Override
    public String toString() {
        return String.format("UserProfile[%s]: Активність=%d, Досвід=%d, " +
                        "Житло=%s, Час=%dгод, Діти=%s, Розмір=%s",
                userName, activityLevel, experienceLevel,
                livingSpace, freeTimeHours,
                hasChildren ? "Так" : "Ні", preferredSize);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserProfile other = (UserProfile) obj;
        return activityLevel == other.activityLevel &&
                experienceLevel == other.experienceLevel &&
                freeTimeHours == other.freeTimeHours &&
                hasChildren == other.hasChildren &&
                livingSpace.equals(other.livingSpace) &&
                preferredSize.equals(other.preferredSize);
    }

    @Override
    public int hashCode() {
        int result = activityLevel;
        result = 31 * result + experienceLevel;
        result = 31 * result + livingSpace.hashCode();
        result = 31 * result + freeTimeHours;
        result = 31 * result + (hasChildren ? 1 : 0);
        result = 31 * result + preferredSize.hashCode();
        return result;
    }
}