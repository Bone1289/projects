package com.head.first.starbuzz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.head.first.starbuzz.beverage.Beverage;
import com.head.first.starbuzz.beverage.DarkRoast;
import com.head.first.starbuzz.beverage.Espresso;
import com.head.first.starbuzz.condiment.Mocha;
import com.head.first.starbuzz.condiment.Whip;

public class StarbuzzCoffe {
	private static final Logger logger = LogManager.getLogger("StarbuzzCoffe");

	public static void main(String[] args) {
		Beverage beverage = new Espresso();
		logger.debug(beverage);

		Beverage beverage2 = new DarkRoast();
		beverage2 = new Mocha(beverage2);
		beverage2 = new Mocha(beverage2);
		beverage2 = new Whip(beverage2);
		logger.debug(beverage2);
	}
}