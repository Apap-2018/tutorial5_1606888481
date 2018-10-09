package com.apap.tutorial5.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.repository.CarDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
	@Autowired
	private CarDb carDb;
 
	@Override
	public void addCar(CarModel car) {
		carDb.save(car);
	}
	@Override
	public void deleteCar(CarModel car) {
		carDb.delete(car);
	}	
	@Override
	public Optional<CarModel> getCarDetailById(Long id) {
		return carDb.findById(id);
	}
	@Override
	public void updateCar(Long id, CarModel carBaru) {		
		CarModel oldCar = carDb.getOne(id);
		oldCar.setBrand(carBaru.getBrand());
		oldCar.setType(carBaru.getType());
		oldCar.setAmount(carBaru.getAmount());
		oldCar.setPrice(carBaru.getPrice());
		carDb.save(oldCar);
	}

	
}
