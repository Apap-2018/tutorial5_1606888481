package com.apap.tutorial5.model;
import java.util.Comparator;

import com.apap.tutorial5.model.CarModel;

public class CarPriceComparator implements Comparator<CarModel> {
	@Override
	public int compare(CarModel mobilSatu, CarModel mobilDua) {
		return(mobilSatu.getPrice() - mobilDua.getPrice());
	}
	

}
