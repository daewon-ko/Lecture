package io.springbatch.springbatch.batch;

import io.springbatch.springbatch.entity.AfterEntity;
import io.springbatch.springbatch.entity.BeforeEntity;
import io.springbatch.springbatch.repository.AfterRepository;
import io.springbatch.springbatch.repository.BeforeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

@Configuration // SpringBoot 및 Batch에게 Configuration으로 등록되기 위해 설정
@RequiredArgsConstructor
public class FirstBatch {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final BeforeRepository beforeRepository;
    private final AfterRepository afterRepository;


    @Bean
    public Job firstBatchJob() {
        return new JobBuilder("first", jobRepository)
                .start(firstStep())
                .build();

    }

    @Bean
    public Step firstStep() {
        return new StepBuilder("firstStep", jobRepository)
                .<BeforeEntity, AfterEntity>chunk(10, platformTransactionManager)
                .reader(beforeReader())
                .processor(middleProcessor())
                .writer(afterWriter())
                .build();
    }

    @Bean
    public RepositoryItemReader<BeforeEntity> beforeReader() {
        return new RepositoryItemReaderBuilder<BeforeEntity>()
                .name("beforeReader")
                .pageSize(10)
                .methodName("findAll")
                .repository(beforeRepository)
                .sorts(Map.of("id", Sort.Direction.ASC))
                .build();
    }


    @Bean
    public ItemProcessor<BeforeEntity, AfterEntity> middleProcessor() {
        return new ItemProcessor<BeforeEntity, AfterEntity>() {
            @Override
            public AfterEntity process(BeforeEntity item) throws Exception {
                AfterEntity afterEntity = new AfterEntity();
                afterEntity.setName(item.getName());
                return afterEntity;
            }
        };
    }

    @Bean
    public RepositoryItemWriter<AfterEntity> afterWriter() {

        return new RepositoryItemWriterBuilder<AfterEntity>()
                .repository(afterRepository)
                .methodName("save")
                .build();

    }






}
