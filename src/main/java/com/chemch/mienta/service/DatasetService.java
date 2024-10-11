package com.chemch.mienta.service;

import com.chemch.mienta.manager.DatasetManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        datasetManager.getDatasets().forEach((_, dataset) -> datasetIds.add(dataset.getId().toString()));
        return datasetIds;
    }
}