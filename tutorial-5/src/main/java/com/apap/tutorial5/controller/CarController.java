package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.*;
import com.apap.tutorial5.service.*;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value = "/car/add/{dealerId}")
	private String addCar(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		//bikin arraylist baru buat mobil2 baru
		dealer.setListCar(new ArrayList<CarModel>());
		//biar ada 1 baris pas baru muncul
		dealer.getListCar().add(new CarModel());
		model.addAttribute("dealer", dealer);
		model.addAttribute("headerTitle", "Add Car");

		return "addCar";
	}
	
	@RequestMapping(value = "/car/add/{dealerId}",params = {"addRow"},method = RequestMethod.POST)
	private String addRow(@ModelAttribute DealerModel dealer , Model model) {
		dealer.getListCar().add(new CarModel());
		model.addAttribute("dealer", dealer);

		return "addCar";
	}

	@RequestMapping(value = "/car/add/{dealerId}",params = {"removeRow"},method = RequestMethod.POST)
	private String removeRow(@ModelAttribute DealerModel dealer , Model model,HttpServletRequest req) {
		int rowId = Integer.valueOf(req.getParameter("removeRow"));
		dealer.getListCar().remove(rowId);
		model.addAttribute("dealer", dealer);

		return "addCar";
	}
	
	@RequestMapping(value = "/car/add/{dealerId}",params = {"save"},method = RequestMethod.POST)
	private String saveAdd(@PathVariable(value="dealerId") Long id,@ModelAttribute DealerModel dealer , Model model) {
		//mengambil data dealer dari db
		DealerModel dealerBaru = dealerService.getDealerDetailById(id).get();
		//nambah mobil yang tadi diinput
		for(CarModel car : dealer.getListCar()) {
			car.setDealer(dealerBaru);
			carService.addCar(car);
		}

		return "add";
	}

	
	
	
	
	
	@RequestMapping(value = "/car/add", method = RequestMethod.POST)
	private String addCarSubmit(@ModelAttribute CarModel car) {
		carService.addCar(car);
		return "add";
	}
	
	@RequestMapping(value = "/car/delete", method = RequestMethod.POST)
	private String delete(@ModelAttribute DealerModel dealer,Model model) {
		for(CarModel car : dealer.getListCar()) {
			carService.deleteCar(car);
		}
		return "delete";
	}


	@RequestMapping(value = "/car/delete/{carId}", method = RequestMethod.GET)
	private String deleteDealerSubmit(@PathVariable (value = "carId",required = true) Long carId) {
		CarModel car = carService.getCarDetailById(carId).get();
		carService.deleteCar(car);
		return "delete";
	}

	@RequestMapping(value = "/car/update/{carId}", method = RequestMethod.GET)
	private String updateCar(@PathVariable (value = "carId",required = true) Long carId, Model model) {
		CarModel car = carService.getCarDetailById(carId).get();
		model.addAttribute("car",car);
		return "updateCar";
	}
	
	@RequestMapping(value = "/car/update/{carId}", method = RequestMethod.POST)
	private String updateCarSubmit(@PathVariable (value = "carId") long carId, @ModelAttribute CarModel car) {
		carService.updateCar(carId, car);
		return "update";
	}


}
