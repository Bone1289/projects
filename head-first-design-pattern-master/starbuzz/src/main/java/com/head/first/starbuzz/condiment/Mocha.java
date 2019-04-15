package com.head.first.starbuzz.condiment;

import com.head.first.starbuzz.beverage.Beverage;

public class Mocha extends CondimentDecorator {
	Beverage mBeverage;

	public Mocha(Beverage beverage) {
		mBeverage = beverage;
	}

	@Override
	public String getDescription() {
		return mBeverage.getDescription() + ", Mocha";
	}

	@Override
	public double cost() {
		double cost = mBeverage.cost();
		switch (mBeverage.getCupSize()) {
		case LARGE:
			cost += .20;
			break;
		case MEDIUM:
			cost += .15;
			break;
		case SMALL:
			cost += .10;
			break;

		}
		return cost;
	}

}
