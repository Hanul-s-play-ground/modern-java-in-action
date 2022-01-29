package chapter2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import chapter2.domain.Apple;
import chapter2.domain.Color;

public class Main {

	private final static List<Apple> apples = new ArrayList<>();

	public static void main(String[] args) {
		addApple();
		// filterUsingParameter();
		// predicate();
		// anonymousClass();
		lambda();
		comparator();
		runnable();
	}

	private static void runnable() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("hello world");
			}
		});

		// Thread t = new Thread(() -> System.out.println("hello world"));
	}

	private static void comparator() {
		apples.sort(new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				return o1.getWeight() - o2.getWeight();
			}
		});

		apples.sort((o1, o2) -> o1.getWeight() - o2.getWeight());
	}

	private static void lambda() {
		List<Apple> redApples = filterApples(Main.apples, (Apple apple) -> Color.RED.equals(apple.getColor()));
		for (Apple redApple : redApples) {
			System.out.println(redApple);
		}
	}

	private static void anonymousClass() {
		List<Apple> redApples = filterApples(Main.apples, new ApplePredicate() {
			public boolean test(Apple apple) {
				return Color.RED.equals(apple.getColor());
			}
		});
		for (Apple redApple : redApples) {
			System.out.println(redApple);
		}
	}

	private static void predicate() {
		List<Apple> redAndHeavyApples = filterApples(Main.apples, new AppleRedAndHeavyPredicate());
		for (Apple redAndHeavyApple : redAndHeavyApples) {
			System.out.println(redAndHeavyApple);
		}
	}

	private static List<Apple> filterApples(List<Apple> apples, ApplePredicate predicate) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : apples) {
			if (predicate.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	private static void filterUsingParameter() {
		List<Apple> greenApples = filterGreenApples(apples);
		for (Apple greenApple : greenApples) {
			System.out.println(greenApple);
		}
	}

	private static List<Apple> filterGreenApples(List<Apple> apples) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : apples) {
			if (Color.GREEN.equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	private static void addApple() {
		apples.add(new Apple(Color.GREEN, 10));
		apples.add(new Apple(Color.GREEN, 2));
		apples.add(new Apple(Color.GREEN, 5));
		apples.add(new Apple(Color.RED, 200));
		apples.add(new Apple(Color.RED, 300));
		apples.add(new Apple(Color.RED, 150));
	}
}
