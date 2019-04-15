package com.head.first.starbuzz.condiment;

import com.head.first.starbuzz.beverage.Beverage;

public class Whip extends CondimentDecorator {

	Beverage mBeverage;

	public Whip(Beverage beverage) {
		mBeverage = beverage;
	}

	@Override
	public String getDescription() {
		return mBeverage.getDescription() + ", Whip";
	}

	@Override
	public double cost() {
		return mBeverage.cost() + .10;
	}

}
