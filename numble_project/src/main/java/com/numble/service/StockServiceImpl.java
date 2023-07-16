package com.numble.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numble.domain.StockVO;
import com.numble.persistence.StockDAO;

@Service
public class StockServiceImpl implements StockService{
	
	@Autowired
	private StockDAO stockDAO;
	
	public List<StockVO> readStockList() throws Exception{
		return stockDAO.readList();
	}

}
