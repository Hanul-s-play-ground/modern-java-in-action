package chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chapter4.domain.Dish;
import chapter4.domain.Type;

public class Main {

	public static void main(String[] args) {
		List<String> lowCaloricDishes = getLowCaloricDishes();
		streamSearch();
	}

	private static void streamSearch() {
		List<String> title = Arrays.asList("java8", "in", "action");
		Stream<String> stream = title.stream();
		stream.forEach(System.out::println);
		// stream.forEach(System.out::println);
	}

	private static List<String> getLowCaloricDishes() {
		List<Dish> menu = new ArrayList<>();
		menu.add(new Dish("치킨", false, 1000, Type.MEAT));
		menu.add(new Dish("피자", false, 2000, Type.OTHER));
		menu.add(new Dish("샐러드", true, 300, Type.OTHER));
		menu.add(new Dish("계란", true, 100, Type.MEAT));

		// 400 칼로리 미만의 음식 이름만 반환
		// 1. 컨테이너 역할을 하는 중간 변수를 사용
		// List<Dish> lowCaloricDishes = new ArrayList<>();
		// for (Dish dish : menu) {
		// 	if (dish.getCalorie() < 400) {
		// 		lowCaloricDishes.add(dish);
		// 	}
		// }
		//
		// lowCaloricDishes.sort((Comparator.comparingInt(Dish::getCalorie)));
		//
		// List<String> names = new ArrayList<>();
		// for (Dish dish : lowCaloricDishes) {
		// 	names.add(dish.getName());
		// }

		// 2. Stream 사용
		return menu.stream()
			.filter(m -> m.getCalorie() < 400)
			.map(Dish::getName)
			.collect(Collectors.toList());
	}
}
