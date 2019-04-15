package com.head.first.starbuzz.beverage;

import java.text.MessageFormat;

public abstract class Beverage {
	public enum Size {
		SMALL, MEDIUM, LARGE
	}

	private Size mCupSize = Size.SMALL;
	protected String description = "Unknown Beverage";

	public String getDescription() {
		return description;
	}

	public abstract double cost();

	@Override
	public String toString() {
		return MessageFormat.format("The order is: {0} and cost ${1}", getDescription(), cost());
	}

	public Size getCupSize() {
		return mCupSize;
	}

	public void setCupSize(Size cupSize) {
		mCupSize = cupSize;
	}
}
