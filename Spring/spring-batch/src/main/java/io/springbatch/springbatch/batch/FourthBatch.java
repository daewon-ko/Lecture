package io.springbatch.springbatch.batch;

import io.springbatch.springbatch.entity.AfterEntity;
import io.springbatch.springbatch.repository.AfterRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FourthBatch {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final AfterRepository afterRepository;


    @Bean
    public Job fourthJob() {
        return new JobBuilder("fourthJob", jobRepository)
                .start(fourthStep())
                .build();
    }

    @Bean
    public Step fourthSte() {
        return new StepBuilder("fourthStep", jobRepository)
                .<Row, AfterEntity> chunk(10, transactionManager)
                .reader(excelReader())
                .processor(fourthProcessor())
                .writer(foruthWriter())
                .build();
    }

    @Bean
    public ItemStreamReader<Row> excelReader() {
        try{
            return new ExcelRowReader("/Users/daewon/Desktop");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


}
