package com.numble.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.numble.domain.StockVO;

@Repository
public class StockDAOImpl implements StockDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.numble.StockMapper";
	
	public List<StockVO> readList() throws Exception{
		
		List<StockVO> stockList = sqlSession.selectList(namespace + ".selectAll");
		
		return stockList;
	}

}
