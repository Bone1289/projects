package com.head.first.starbuzz.beverage;

public class HouseBlend extends Beverage {

	public HouseBlend() {
		description = "House Blend Coffe";
	}

	@Override
	public double cost() {
		return .89;
	}

}
