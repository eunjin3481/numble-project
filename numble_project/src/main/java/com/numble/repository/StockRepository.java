package com.numble.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.numble.domain.Stock;

@Repository
public class StockRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.numble.StockMapper";
	
	public List<Stock> readList() throws Exception{
		
		List<Stock> stockList = sqlSession.selectList(namespace + ".selectAll");
		
		return stockList;
	}

}
