package ua.polina.doggonovo.model;

import ua.polina.doggonovo.logic.ScoringEngine;
import ua.polina.doggonovo.model.breeds.DogBreed;

public class BreedRecommendation implements Comparable<BreedRecommendation> {

    private DogBreed breed;
    private double score;
    private String reason;
    private ScoringEngine.ScoreDetails details;

    public BreedRecommendation(DogBreed breed, double score, String reason) {
        this.breed = breed;
        this.score = score;
        this.reason = reason;
        this.details = null;
    }

    public BreedRecommendation(DogBreed breed, double score, String reason,
                               ScoringEngine.ScoreDetails details) {
        this.breed = breed;
        this.score = score;
        this.reason = reason;
        this.details = details;
    }

    public DogBreed getBreed() {
        return breed;
    }

    public double getScore() {
        return score;
    }

    public String getReason() {
        return reason;
    }

    public ScoringEngine.ScoreDetails getDetails() {
        return details;
    }

    public String getScoreString() {
        return String.format("%.0f%%", score);
    }

    public String getMatchLevel() {
        if (score >= 90) {
            return "Ідеальна відповідність";
        } else if (score >= 85) {
            return "Відмінна відповідність";
        } else if (score >= 70) {
            return "Добра відповідність";
        } else if (score >= 50) {
            return "Прийнятна відповідність";
        } else if (score >= 30) {
            return "Слабка відповідність";
        } else {
            return "Погана відповідність";
        }
    }

    public String getScoreColor() {
        if (score >= 85) {
            return "#4CAF50";
        } else if (score >= 70) {
            return "#8BC34A";
        } else if (score >= 50) {
            return "#FFC107";
        } else if (score >= 30) {
            return "#FF9800";
        } else {
            return "#F44336";
        }
    }

    public String getStarsVisual() {
        int stars = getStarRating();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < stars; i++) {
            sb.append("★");
        }
        for (int i = stars; i < 5; i++) {
            sb.append("☆");
        }

        return sb.toString();
    }

    private int getStarRating() {
        if (score >= 90) return 5;
        if (score >= 75) return 4;
        if (score >= 60) return 3;
        if (score >= 40) return 2;
        return 1;
    }

    @Override
    public int compareTo(BreedRecommendation other) {
        return Double.compare(other.score, this.score);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)",
                breed.getName(),
                getScoreString(),
                getMatchLevel());
    }
}