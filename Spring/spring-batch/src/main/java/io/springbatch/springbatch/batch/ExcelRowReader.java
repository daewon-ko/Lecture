package io.springbatch.springbatch.batch;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.item.ItemStreamReader;

public class ExcelRowReader implements ItemStreamReader<Row> {
    private final String filePath;

}
