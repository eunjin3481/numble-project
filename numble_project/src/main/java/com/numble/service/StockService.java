package com.numble.service;

import java.util.List;

import com.numble.domain.StockVO;

public interface StockService {
	
	public List<StockVO> readStockList() throws Exception;
}
