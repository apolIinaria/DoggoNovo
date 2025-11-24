package ua.polina.doggonovo.logic;

import ua.polina.doggonovo.model.UserProfile;
import ua.polina.doggonovo.model.breeds.DogBreed;

public class MatchCriteria {
    public static double matchActivity(UserProfile user, DogBreed breed) {
        int difference = Math.abs(user.getActivityLevel() - breed.getActivityLevel());
        switch (difference) {
            case 0: return 1.0;
            case 1: return 0.8;
            case 2: return 0.5;
            case 3: return 0.2;
            default: return 0.1;
        }
    }

    public static double matchExperience(UserProfile user, DogBreed breed) {
        int userExp = user.getExperienceLevel();
        int requiredExp = breed.getRequiredExperience();
        if (userExp >= requiredExp) {
            return 1.0;
        } else {
            int gap = requiredExp - userExp;
            switch (gap) {
                case 1: return 0.7;
                case 2: return 0.4;
                case 3: return 0.2;
                default: return 0.1;
            }
        }
    }

    public static double matchLivingSpace(UserProfile user, DogBreed breed) {
        String userHome = user.getLivingSpace();
        String breedHome = breed.getSuitableHome();

        if (breedHome.equals("Квартира")) {
            return 1.0;
        }

        if (breedHome.equals("Будинок")) {
            if (userHome.equals("Будинок")) {
                return 1.0;
            } else {
                return 0.3;
            }
        }
        return 0.5;
    }

    public static double matchFreeTime(UserProfile user, DogBreed breed) {
        int userTime = user.getFreeTimeHours();
        int requiredTime = breed.getCareTimeNeeded();
        if (userTime >= requiredTime + 1) {
            return 1.0;
        } else if (userTime >= requiredTime) {
            return 0.9;
        } else if (userTime == requiredTime - 1) {
            return 0.6;
        } else {
            return 0.3;
        }
    }

    public static double matchKids(UserProfile user, DogBreed breed) {
        boolean hasKids = user.hasChildren();
        boolean goodWithKids = breed.isGoodWithKids();
        if (!hasKids) {
            return 1.0;
        }
        if (hasKids && goodWithKids) {
            return 1.0;
        } else {
            return 0.2;
        }
    }

    public static double matchSize(UserProfile user, DogBreed breed) {
        String preferredSize = user.getPreferredSize();
        String breedSize = breed.getSize();
        if (preferredSize.equals(breedSize)) {
            return 1.0;
        }
        if (preferredSize.equals("Medium")) {
            return 0.7;
        }
        if ((preferredSize.equals("Small") && breedSize.equals("Medium")) ||
                (preferredSize.equals("Large") && breedSize.equals("Medium"))) {
            return 0.6;
        }
        return 0.3;
    }
}