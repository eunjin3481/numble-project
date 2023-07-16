package com.numble.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.numble.domain.StockVO;
import com.numble.service.StockService;

@RestController
@RequestMapping(value="stocks")
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	// 주식 목록 조회 api
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> listStock() throws Exception{
		
		List<StockVO> stockList = stockService.readStockList();
		
		// 응답
		Map<String, Object> response = new HashMap<>();
		response.put("stocks", stockList);
		
		return ResponseEntity.ok(response);
	}
}
