package com.chemch.mienta.manager;

import com.chemch.mienta.factory.DatasetFactory;
import com.chemch.mienta.model.dataset.Dataset;
import com.chemch.mienta.model.dataset.DatasetType;
import com.chemch.mienta.model.dataset.ExpenseDataset;
import com.chemch.mienta.model.upload.Upload;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@Component
public class DatasetManager {
    @Getter
    private final Map<UUID, Dataset> datasets = new HashMap<>();
    private final DatasetFactory factory;

    public DatasetManager(DatasetFactory factory) {
        this.factory = factory;
    }

    private void validate(Dataset dataset) {
        // TODO define dataset validations - Milestone 2
    }

    public Dataset convert(Upload upload, DatasetType type) {
        // convert upload to applicable dataset
        Dataset dataset = factory.create(upload, type);

        // validate and register newly converted dataset
        validate(dataset);
        this.datasets.put(dataset.getId(), dataset);

        return dataset;
    }
}