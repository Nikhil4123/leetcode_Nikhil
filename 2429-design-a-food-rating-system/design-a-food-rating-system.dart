import 'dart:collection';

class FoodRatings {
  final Map<String, String> foodToCuisine = {};
  final Map<String, int> foodToRating = {};
  final Map<String, SplayTreeSet<String>> cuisineFoods = {};

  FoodRatings(List<String> foods, List<String> cuisines, List<int> ratings) {
    for (int i = 0; i < foods.length; i++) {
      foodToCuisine[foods[i]] = cuisines[i];
      foodToRating[foods[i]] = ratings[i];
    }

    for (int i = 0; i < foods.length; i++) {
      String cuisine = cuisines[i];
      cuisineFoods.putIfAbsent(
        cuisine,
        () => SplayTreeSet<String>((a, b) {
          int r1 = foodToRating[a]!;
          int r2 = foodToRating[b]!;
          if (r1 != r2) return r2.compareTo(r1); 
          return a.compareTo(b); 
        }),
      );
      cuisineFoods[cuisine]!.add(foods[i]);
    }
  }

  void changeRating(String food, int newRating) {
    String cuisine = foodToCuisine[food]!;
    SplayTreeSet<String> set = cuisineFoods[cuisine]!;

    set.remove(food);
    foodToRating[food] = newRating;
    set.add(food);
  }

  String highestRated(String cuisine) {
    return cuisineFoods[cuisine]!.first;
  }
}