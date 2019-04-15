package com.head.first.starbuzz.beverage;

public class DarkRoast extends Beverage {

	public DarkRoast() {
		description = "Dark Roast Coffe";
	}

	public DarkRoast(Size size) {
		description = "Dark Roast Coffe";
	}

	@Override
	public double cost() {
		return 1.34;
	}
}
