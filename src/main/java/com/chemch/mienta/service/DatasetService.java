package com.chemch.mienta.service;

import com.chemch.mienta.manager.DatasetManager;
import com.chemch.mienta.model.dataset.Dataset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@Service
@Slf4j
public class DatasetService {
    private final DatasetManager datasetManager;

    /**
     *
     * @param datasetManager
     */
    public DatasetService(DatasetManager datasetManager) {
        this.datasetManager = datasetManager;
    }

    /**
     *
     * @return
     */
    public List<String> getDatasetIds() {
        List<String> datasetIds = new ArrayList<>();
        datasetManager.getDatasets().forEach((_, dataset) -> datasetIds.add(dataset.getTrackId().toString()));
        return datasetIds;
    }

    /**
     * @param datasetIds
     * @return
     */
    public List<Dataset> getDatasetsByIds(List<UUID> datasetIds) {
        List<Dataset> datasets = new ArrayList<>();
        datasetIds.forEach((id) -> datasets.add(datasetManager.getDatasets().get(id)));
        return datasets;
    }
}