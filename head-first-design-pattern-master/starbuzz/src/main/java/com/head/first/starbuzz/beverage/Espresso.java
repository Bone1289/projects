package com.head.first.starbuzz.beverage;

public class Espresso extends Beverage {

	public Espresso() {
		description = "Espresso";
	}

	public Espresso(Size size) {
		description = "Espresso";
	}

	@Override
	public double cost() {
		return 1.99;
	}

}
