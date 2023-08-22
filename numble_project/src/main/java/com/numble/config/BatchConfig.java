package com.numble.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.numble.domain.Stock;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public SqlSessionFactory sqlSessionFactory; 
	
	private int CHUNK_SIZE=2;
	
	
	
	@Bean
	public Job stockPriceJob() throws Exception{
		
		return jobBuilderFactory.get("stockPriceJob")
				.start(step())
                .build();
	}
	
	@Bean
	@JobScope
	public Step step() throws Exception{
		return stepBuilderFactory.get("ChunkStep")
				.<Stock,Stock>chunk(CHUNK_SIZE)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
	
	@Bean
    @StepScope
    public MyBatisPagingItemReader<Stock> reader() throws Exception {
		System.out.println("itemreader start");
    	MyBatisPagingItemReader<Stock> reader = new MyBatisPagingItemReader<>();
    	reader.setPageSize(CHUNK_SIZE);
    	reader.setSqlSessionFactory(sqlSessionFactory);
    	reader.setQueryId("com.numble.StockMapper.selectAll");
    	
    	return reader;
    }
	
	@Bean
    @StepScope
    public ItemProcessor<Stock, Stock> processor(){

    	return new ItemProcessor<Stock, Stock>() {
            @Override
            public Stock process(Stock stock) throws Exception {
            	System.out.print(stock.getPrice());
            	stock.setPrice(stock.getPrice() + 1000);
            	System.out.println(stock.getPrice());

                return stock;
            }
        };
    }

	@Bean
    @StepScope
    public MyBatisBatchItemWriter<Stock> writer(){
    	MyBatisBatchItemWriter<Stock> writer = new MyBatisBatchItemWriter<>();
    	writer.setSqlSessionFactory(sqlSessionFactory);
    	writer.setStatementId("com.numble.StockMapper.update");
    	return writer;
    }
	
	
	
	
}

