package com.numble.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numble.domain.Stock;
import com.numble.repository.StockRepository;

@Service
public class StockService {
	
	@Autowired
	private StockRepository stockRepository;
	
	public List<Stock> readStockList() throws Exception{
		return stockRepository.readList();
	}

}
