package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import chapter3.domain.Apple;
import chapter3.domain.Color;

public class Main {

	public static void main(String[] args) {
		// predicate();
		// consumer();
		function();
		// intPredicate();
		// capturing();
		// comparator();
	}

	private static void comparator() {
		List<Apple> apples = Arrays.asList(
			new Apple(Color.RED, 100),
			new Apple(Color.RED, 200),
			new Apple(Color.GREEN, 100),
			new Apple(Color.RED, 150),
			new Apple(Color.GREEN, 500)
		);

		Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
		apples.sort(Comparator.comparingInt(Apple::getWeight).reversed());
		apples.sort(Comparator.comparingInt(Apple::getWeight)
			.reversed()
			.thenComparing(Apple::getColor));

		Predicate<Apple> redApple = apple -> Color.RED.equals(apple.getColor());
		Predicate<Apple> notRedApple = redApple.negate();
		Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);
		Predicate<Apple> redAndHeavyAppleOrGreen =
			redApple.and(apple -> apple.getWeight() > 150)
				.or(apple -> Color.GREEN.equals(apple.getColor()));

		List<Apple> notRedApples = filter(apples, notRedApple);
		List<Apple> redAndHeavyApples = filter(apples, redAndHeavyApple);
		List<Apple> redAndHeavyOrGreenApples = filter(apples, redAndHeavyAppleOrGreen);

		// for (Apple apple : notRedApples) {
		// 	System.out.println(apple);
		// }

		for (Apple apple : redAndHeavyOrGreenApples) {
			System.out.println(apple);
		}
	}

	private static void capturing() {
		int portNumber = 1337;
		Runnable runnable = () -> System.out.println(portNumber);
		runnable.run();
	}

	private static void intPredicate() {
		IntPredicate evenNumbers = (int i) -> i % 2 == 0;
		evenNumbers.test(1000);

		Predicate<Integer> oddNumbers = (Integer i) -> i % 2 != 0;
		oddNumbers.test(1000);
	}

	private static void function() {
		List<Integer> list = map(
			Arrays.asList("lambdas", "in", "action"),
			(String s) -> s.length()
		);
		// for (Integer i : list) {
		// 	System.out.println(i);
		// }

		Function<Integer, Integer> f = x -> x + 1;
		Function<Integer, Integer> g = x -> x * 2;
		Function<Integer, Integer> h = f.andThen(g);
		int result = h.apply(1);
		System.out.println(result);

		h = f.compose(g);
		result = h.apply(1);
		System.out.println(result);
	}

	private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
		List<R> result = new ArrayList<>();
		for (T t : list) {
			result.add(function.apply(t));
		}
		return result;
	}

	private static void consumer() {
		forEach(
			Arrays.asList(1, 2, 3, 4, 5),
			(Integer i) -> System.out.println(i)
		);
	}

	private static <T> void forEach(List<T> list, Consumer<T> consumer) {
		for (T t : list) {
			consumer.accept(t);
		}
	}

	private static void predicate() {
		List<String> listOfStrings = Arrays.asList(
			"abc",
			""
		);
		Predicate<String> nonEmptyStringPredicate = (String str) -> !str.isEmpty();
		List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
		for (String str : nonEmpty) {
			System.out.println(str);
		}
	}

	private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
		List<T> results = new ArrayList<>();
		for (T t : list) {
			if (predicate.test(t)) {
				results.add(t);
			}
		}
		return results;
	}
}
