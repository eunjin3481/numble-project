package com.numble.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.numble.domain.StockVO;

import lombok.RequiredArgsConstructor;

/*
@Configuration
@RequiredArgsConstructor
public class BatchConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	
	@Bean
	public Job stockPriceJob() {
		
		return jobBuilderFactory.get("stockPriceJob")
				.incrementer(new RunIdIncrementer())
				.start(stockPriceStep())
                .build();
	}
	
	@Bean
	@JobScope
	public Step stockPriceStep() {
		return stepBuilderFactory.get("stockPriceStep")
				.<StockVO, StockVO>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
	}
	
	private MyBatisCursorItemReaderBuilder<StockVO> reader(){
		
		return new MyBatisCursorItemReaderBuilder<StockVO>()
				.sessionFactory(sqlSession)
				.queryId("com.numble.StockMapper.selectAll")
				.build();
	}
	
	private ItemProcessor<StockVO, StockVO> processor(){
		
	}
	
	private ItemWriter<StockVO> writer(){
		
	}
	
	
}
*/
