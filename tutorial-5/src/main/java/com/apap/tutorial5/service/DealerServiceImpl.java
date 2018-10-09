package com.apap.tutorial5.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.apap.tutorial5.model.*;
import com.apap.tutorial5.repository.DealerDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DealerServiceImpl implements DealerService {
	@Autowired
	private DealerDb dealerDb;
	
	@Override
	public Optional<DealerModel> getDealerDetailById(Long id) {
		return dealerDb.findById(id);
	}
	
	@Override
	public void addDealer(DealerModel dealer) {
		dealerDb.save(dealer);
	}
	
	@Override
	public List<DealerModel> getDealerList(){
		
		return dealerDb.findAll();
		
	}
	@Override
	public void deleteDealer(DealerModel dealer) {
		dealerDb.delete(dealer);
	}
	
	@Override
	public void updateDealer(Long id, DealerModel dealerBaru) {		
		DealerModel oldDealer = dealerDb.getOne(id);
		oldDealer.setAlamat(dealerBaru.getAlamat());
		oldDealer.setNoTelp(dealerBaru.getNoTelp());
		dealerDb.save(oldDealer);
	}	
	@Override
	public List<CarModel> getListCarOrderByPriceAsc(Long dealerId) {
		DealerModel tempDealer = dealerDb.findById(dealerId).get();
		List<CarModel> temp = tempDealer.getListCar();
		Collections.sort(temp, new CarPriceComparator());
		return temp;
	}




}
