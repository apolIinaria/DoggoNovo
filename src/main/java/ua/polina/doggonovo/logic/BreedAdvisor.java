package ua.polina.doggonovo.logic;

import ua.polina.doggonovo.data.BreedRepository;
import ua.polina.doggonovo.model.BreedRecommendation;
import ua.polina.doggonovo.model.UserProfile;
import ua.polina.doggonovo.model.breeds.DogBreed;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BreedAdvisor {
    private BreedRepository repository;
    private UserProfile userProfile;

    public BreedAdvisor() {
        this.repository = new BreedRepository();
    }

    public BreedAdvisor(BreedRepository repository) {
        this.repository = repository;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public List<BreedRecommendation> getTopRecommendations(int topN) {
        return getBreedRecommendations(topN);
    }

    public List<BreedRecommendation> getBreedRecommendations(int topN) {
        if (userProfile == null) {
            throw new IllegalStateException("Профіль користувача не встановлено!");
        }

        List<DogBreed> allBreeds = repository.getAllBreeds();
        List<BreedRecommendation> breedRecommendations = new ArrayList<>();

        for (DogBreed breed : allBreeds) {
            double score = ScoringEngine.calculateScore(userProfile, breed);
            ScoringEngine.ScoreDetails details =
                    ScoringEngine.calculateDetailedScore(userProfile, breed);
            String reason = generateRecommendationReason(breed, details);
            breedRecommendations.add(new BreedRecommendation(breed, score, reason, details));
        }

        breedRecommendations.sort((r1, r2) -> Double.compare(r2.getScore(), r1.getScore()));

        return breedRecommendations.stream()
                .limit(topN)
                .collect(Collectors.toList());
    }

    public List<BreedRecommendation> getAllRecommendations() {
        return getBreedRecommendations(repository.getBreedCount());
    }

    private String generateRecommendationReason(DogBreed breed, ScoringEngine.ScoreDetails details) {
        StringBuilder reason = new StringBuilder();
        double totalScore = details.getTotalScore();

        if (totalScore >= 85) {
            reason.append("Відмінна відповідність! ");
        } else if (totalScore >= 70) {
            reason.append("Добра відповідність. ");
        } else if (totalScore >= 50) {
            reason.append("Прийнятна відповідність. ");
        } else {
            reason.append("Слабка відповідність. ");
        }

        String strongest = details.getStrongestCriteria();
        reason.append("Найкраще: ").append(strongest.toLowerCase()).append(". ");

        if (totalScore < 90) {
            String weakest = details.getWeakestCriteria();
            reason.append("Увага: ").append(weakest.toLowerCase()).append(".");
        }

        return reason.toString();
    }

    public RecommendationStats getStats() {
        if (userProfile == null) {
            return null;
        }
        List<BreedRecommendation> all = getAllRecommendations();

        double avgScore = all.stream()
                .mapToDouble(BreedRecommendation::getScore)
                .average()
                .orElse(0.0);
        long excellentCount = all.stream().filter(r -> r.getScore() >= 85).count();
        long goodCount = all.stream().filter(r -> r.getScore() >= 70 && r.getScore() < 85).count();
        long fairCount = all.stream().filter(r -> r.getScore() >= 50 && r.getScore() < 70).count();
        long poorCount = all.stream().filter(r -> r.getScore() < 50).count();

        return new RecommendationStats(
                avgScore, excellentCount, goodCount, fairCount, poorCount, all.size()
        );
    }

    public static class RecommendationStats {
        private final double averageScore;
        private final long excellentCount;
        private final long goodCount;
        private final long fairCount;
        private final long poorCount;
        private final long totalCount;

        public RecommendationStats(double avgScore, long excellent, long good,
                                   long fair, long poor, long total) {
            this.averageScore = avgScore;
            this.excellentCount = excellent;
            this.goodCount = good;
            this.fairCount = fair;
            this.poorCount = poor;
            this.totalCount = total;
        }

        public double getAverageScore() { return averageScore; }
        public long getExcellentCount() { return excellentCount; }
        public long getGoodCount() { return goodCount; }
        public long getFairCount() { return fairCount; }
        public long getPoorCount() { return poorCount; }
        public long getTotalCount() { return totalCount; }
    }
}