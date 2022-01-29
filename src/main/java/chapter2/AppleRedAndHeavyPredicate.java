package chapter2;

import chapter2.domain.Apple;
import chapter2.domain.Color;

public class AppleRedAndHeavyPredicate implements ApplePredicate{
	@Override
	public boolean test(Apple apple) {
		return Color.RED.equals(apple.getColor()) &&
			apple.getWeight() > 50;
	}
}
