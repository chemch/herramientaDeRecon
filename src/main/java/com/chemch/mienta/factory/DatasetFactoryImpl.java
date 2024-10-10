package com.chemch.mienta.factory;

import com.chemch.mienta.model.dataset.Dataset;
import com.chemch.mienta.model.dataset.DatasetType;
import com.chemch.mienta.model.dataset.ExpenseDataset;
import com.chemch.mienta.model.dataset.RevenueDataset;
import com.chemch.mienta.model.upload.Upload;
import org.springframework.stereotype.Component;

@Component
public class DatasetFactoryImpl implements DatasetFactory {

    @Override
    public Dataset create(Upload upload, DatasetType type) {
        // create an instance of a supported dataset type
        return switch (type) {
            case REVENUE -> new RevenueDataset.Builder(upload).build();
            case EXPENSE -> new ExpenseDataset(upload);
        };
    }
}