package ua.polina.doggonovo.logic;

import ua.polina.doggonovo.model.UserProfile;
import ua.polina.doggonovo.model.breeds.DogBreed;

public class ScoringEngine {
    private static final double WEIGHT_ACTIVITY = 0.25;
    private static final double WEIGHT_EXPERIENCE = 0.20;
    private static final double WEIGHT_LIVING_SPACE = 0.15;
    private static final double WEIGHT_FREE_TIME = 0.15;
    private static final double WEIGHT_KIDS = 0.15;
    private static final double WEIGHT_SIZE = 0.10;

    public static double calculateScore(UserProfile user, DogBreed breed) {
        double activityScore = MatchCriteria.matchActivity(user, breed);
        double experienceScore = MatchCriteria.matchExperience(user, breed);
        double livingSpaceScore = MatchCriteria.matchLivingSpace(user, breed);
        double freeTimeScore = MatchCriteria.matchFreeTime(user, breed);
        double kidsScore = MatchCriteria.matchKids(user, breed);
        double sizeScore = MatchCriteria.matchSize(user, breed);

        double weightedScore =
                activityScore * WEIGHT_ACTIVITY +
                        experienceScore * WEIGHT_EXPERIENCE +
                        livingSpaceScore * WEIGHT_LIVING_SPACE +
                        freeTimeScore * WEIGHT_FREE_TIME +
                        kidsScore * WEIGHT_KIDS +
                        sizeScore * WEIGHT_SIZE;
        return weightedScore * 100;
    }

    public static ScoreDetails calculateDetailedScore(UserProfile user, DogBreed breed) {
        double activityScore = MatchCriteria.matchActivity(user, breed);
        double experienceScore = MatchCriteria.matchExperience(user, breed);
        double livingSpaceScore = MatchCriteria.matchLivingSpace(user, breed);
        double freeTimeScore = MatchCriteria.matchFreeTime(user, breed);
        double kidsScore = MatchCriteria.matchKids(user, breed);
        double sizeScore = MatchCriteria.matchSize(user, breed);

        double totalScore = calculateScore(user, breed);

        return new ScoreDetails(
                totalScore,
                activityScore,
                experienceScore,
                livingSpaceScore,
                freeTimeScore,
                kidsScore,
                sizeScore
        );
    }

    public static class ScoreDetails {
        private final double totalScore;
        private final double activityScore;
        private final double experienceScore;
        private final double livingSpaceScore;
        private final double freeTimeScore;
        private final double kidsScore;
        private final double sizeScore;

        public ScoreDetails(double totalScore, double activityScore,
                            double experienceScore, double livingSpaceScore,
                            double freeTimeScore, double kidsScore, double sizeScore) {
            this.totalScore = totalScore;
            this.activityScore = activityScore;
            this.experienceScore = experienceScore;
            this.livingSpaceScore = livingSpaceScore;
            this.freeTimeScore = freeTimeScore;
            this.kidsScore = kidsScore;
            this.sizeScore = sizeScore;
        }

        public double getTotalScore() { return totalScore; }
        public double getActivityScore() { return activityScore; }
        public double getExperienceScore() { return experienceScore; }
        public double getLivingSpaceScore() { return livingSpaceScore; }
        public double getFreeTimeScore() { return freeTimeScore; }
        public double getKidsScore() { return kidsScore; }
        public double getSizeScore() { return sizeScore; }

        public double[] getAllScores() {
            return new double[]{
                    activityScore,
                    experienceScore,
                    livingSpaceScore,
                    freeTimeScore,
                    kidsScore,
                    sizeScore
            };
        }

        public String getWeakestCriteria() {
            double[] scores = getAllScores();
            String[] names = getCriteriaNames();
            int minIndex = 0;
            double minScore = scores[0];
            for (int i = 1; i < scores.length; i++) {
                if (scores[i] < minScore) {
                    minScore = scores[i];
                    minIndex = i;
                }
            }
            return names[minIndex];
        }

        public String getStrongestCriteria() {
            double[] scores = getAllScores();
            String[] names = getCriteriaNames();
            int maxIndex = 0;
            double maxScore = scores[0];
            for (int i = 1; i < scores.length; i++) {
                if (scores[i] > maxScore) {
                    maxScore = scores[i];
                    maxIndex = i;
                }
            }
            return names[maxIndex];
        }
    }

    public static String[] getCriteriaNames() {
        return new String[]{
                "Активність",
                "Досвід",
                "Тип житла",
                "Вільний час",
                "Діти",
                "Розмір"
        };
    }
}
