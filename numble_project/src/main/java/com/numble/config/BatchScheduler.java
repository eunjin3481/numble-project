package com.numble.config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class BatchScheduler {
	
	@Autowired
    private JobLauncher jobLauncher;
	
	@Autowired
    private BatchConfig batchConfig;

	
    @Scheduled(cron = "* * * * *") // 매 분 실행
    public void runBatchJob() throws JobParametersInvalidException, JobExecutionException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();
        
        try {
			jobLauncher.run(batchConfig.stockPriceJob(), jobParameters);
		} catch (Exception e) {
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		Calendar calendar = Calendar.getInstance();
	    		Date date = calendar.getTime();
	    		System.out.println(String.format("ERRER TIME : %s", sdf.format(date)));;
		}
    }
}
