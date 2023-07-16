package com.numble.persistence;

import java.util.List;

import com.numble.domain.StockVO;

public interface StockDAO {
	
	public List<StockVO> readList() throws Exception;
}
