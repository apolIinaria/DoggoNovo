package ua.polina.doggonovo.data;

import ua.polina.doggonovo.model.*;
import ua.polina.doggonovo.model.breeds.*;
import java.util.*;

public class BreedRepository {
    private List<DogBreed> allBreeds;
    public BreedRepository() {
        allBreeds = new ArrayList<>();
        initializeBreeds();
    }

    private void initializeBreeds() {
        // Herding
        allBreeds.add(new HerdingBreed(
                BreedData.GERMAN_SHEPHERD.getUkrainianName(),
                "Large",
                5,
                4,
                3,
                true,
                "Будинок",
                "Універсальна робоча порода. Розумна, відданá, легко навчається. Чудовий захисник родини.",
                95
        ));

        allBreeds.add(new HerdingBreed(
                BreedData.BORDER_COLLIE.getUkrainianName(),
                "Medium",
                5,
                5,
                3,
                true,
                "Будинок",
                "Найрозумніша порода. Потребує багато фізичної та розумової активності. Ідеальна для спорту.",
                100
        ));

        allBreeds.add(new HerdingBreed(
                BreedData.CORGI.getUkrainianName(),
                "Small",
                4,
                2,
                2,
                true,
                "Квартира",
                "Компактна, активна, дружелюбна. Королева Єлизавета обожнювала цю породу!",
                85
        ));

        // Hound
        allBreeds.add(new HoundBreed(
                BreedData.BEAGLE.getUkrainianName(),
                "Medium",
                4,
                2,
                2,
                true,
                "Квартира",
                "Веселий, дружелюбний, енергійний. Обожнює слідкувати за запахами. Чудовий сімейний пес.",
                "Середній",
                true
        ));

        allBreeds.add(new HoundBreed(
                BreedData.DACHSHUND.getUkrainianName(),
                "Small",
                3,
                2,
                1,
                true,
                "Квартира",
                "Сміливий маленький мисливець. Впертий, але дуже ласкавий. Ідеальний для квартири.",
                "Високий",
                true
        ));

        allBreeds.add(new HoundBreed(
                BreedData.BASSET.getUkrainianName(),
                "Medium",
                2,
                1,
                2,
                true,
                "Квартира",
                "Спокійний, меланхолійний, терплячий. Чудовий з дітьми. Обожнює комфорт.",
                "Середній",
                true
        ));

        // Sporting
        allBreeds.add(new SportingBreed(
                BreedData.LABRADOR.getUkrainianName(),
                "Large",
                5,
                2,
                3,
                true,
                "Будинок",
                "Найпопулярніша порода у світі. Дружелюбний, активний, обожнює воду і гру.",
                true,
                "Аппортування"
        ));

        allBreeds.add(new SportingBreed(
                BreedData.GOLDEN_RETRIEVER.getUkrainianName(),
                "Large",
                4,
                2,
                3,
                true,
                "Будинок",
                "М'який, терплячий, ідеальний сімейний пес. Часто використовується як терапевтична собака.",
                true,
                "Аппортування"
        ));

        allBreeds.add(new SportingBreed(
                BreedData.COCKER_SPANIEL.getUkrainianName(),
                "Medium",
                4,
                3,
                2,
                true,
                "Квартира",
                "Веселий, ніжний, енергійний. Красива довга шерсть потребує регулярного догляду.",
                false,
                "Польова робота"
        ));

        // Toy
        allBreeds.add(new ToyBreed(
                BreedData.CHIHUAHUA.getUkrainianName(),
                "Small",
                2,
                1,
                1,
                false,
                "Квартира",
                "Найменша порода у світі. Відважна, ласкава до господаря, може бути ревнивою.",
                true,
                "Компаньйон"
        ));

        allBreeds.add(new ToyBreed(
                BreedData.POMERANIAN.getUkrainianName(),
                "Small",
                3,
                2,
                2,
                false,
                "Квартира",
                "Пухнастий маленький ведмедик. Активний, розумний, любить бути в центрі уваги.",
                true,
                "Компаньйон"
        ));

        allBreeds.add(new ToyBreed(
                BreedData.YORKSHIRE.getUkrainianName(),
                "Small",
                3,
                3,
                2,
                false,
                "Квартира",
                "Елегантний, відважний, енергійний. Шерсть схожа на людське волосся.",
                true,
                "Компаньйон"
        ));

        // Working
        allBreeds.add(new WorkingBreed(
                BreedData.HUSKY.getUkrainianName(),
                "Large",
                5,
                4,
                3,
                true,
                "Будинок",
                "Енергійний, незалежний, любить бігати. Створений для холодного клімату. Може втікати!",
                "Їздовий",
                true
        ));

        allBreeds.add(new WorkingBreed(
                BreedData.BOXER.getUkrainianName(),
                "Large",
                5,
                3,
                2,
                true,
                "Будинок",
                "Енергійний, грайливий, відданий. Чудовий охоронець і сімейний пес.",
                "Охоронний",
                true
        ));

        allBreeds.add(new WorkingBreed(
                BreedData.BERNESE.getUkrainianName(),
                "Large",
                3,
                3,
                3,
                true,
                "Будинок",
                "Спокійний гігант. М'який, терплячий, обожнює дітей. Потребує прохолодного клімату.",
                "Тягловий",
                false
        ));
    }

    public List<DogBreed> getAllBreeds() {
        return new ArrayList<>(allBreeds);
    }

    public DogBreed getBreedByName(String name) {
        return allBreeds.stream()
                .filter(breed -> breed.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<DogBreed> getBreedsByCategory(String category) {
        List<DogBreed> result = new ArrayList<>();
        for (DogBreed breed : allBreeds) {
            if (breed.getClass().getSimpleName().replace("Breed","").equals(category)) {
                result.add(breed);
            }
        }
        return result;
    }

    public List<DogBreed> getBreedsBySize(String size) {
        return allBreeds.stream()
                .filter(breed -> breed.getSize().equals(size))
                .collect(java.util.stream.Collectors.toList());
    }

    public int getBreedCount() {
        return allBreeds.size();
    }
}