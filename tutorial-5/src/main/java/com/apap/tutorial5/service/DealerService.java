package com.apap.tutorial5.service;

import java.util.Optional;
import java.util.List;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.model.DealerModel;

public interface DealerService {
	Optional<DealerModel> getDealerDetailById(Long id);
	List<DealerModel> getDealerList();
	void addDealer(DealerModel dealer);
	void deleteDealer(DealerModel dealer);
	void updateDealer(Long id, DealerModel dealer);
	List<CarModel> getListCarOrderByPriceAsc(Long dealerId);
	
}
