package com.chemch.mienta.service;

import com.chemch.mienta.calculator.RowDeltaCalculator;
import com.chemch.mienta.model.ReconConfig;
import com.chemch.mienta.model.dataset.Dataset;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ReconService {
    private final ReconConfigService reconConfigService;
    private final DatasetService datasetService;
    private final UploadService uploadService;

    public ReconService(ReconConfigService reconConfigService, DatasetService datasetService, UploadService uploadService) {
        this.reconConfigService = reconConfigService;
        this.datasetService = datasetService;
        this.uploadService = uploadService;
    }

    public JsonArray runRecon() throws IllegalAccessException {
        // get recon config
        ReconConfig activeConfig = reconConfigService.getActiveConfig();

        // get datasets in scope for reconciliation
        List<UUID> datasetIds = uploadService.getDatasetIdsByUploadIds(activeConfig.getUploadIds());
        List<Dataset> datasets =  datasetService.getDatasetsByIds(datasetIds);

        // perform recon
        return new RowDeltaCalculator(activeConfig, datasets).calculate();
    }
}