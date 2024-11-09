package com.chemch.mienta.service;

import com.chemch.mienta.calculator.RowDeltaCalculator;
import com.chemch.mienta.model.Recon;
import com.chemch.mienta.model.ReconConfig;
import com.chemch.mienta.model.dataset.Dataset;
import com.chemch.mienta.persistence.ReconRepo;
import com.google.gson.*;
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
public class ReconService {
    private final ReconConfigService reconConfigService;
    private final DatasetService datasetService;
    private final UploadService uploadService;
    private final ReconRepo reconRepo;

    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     * @param reconConfigService
     * @param datasetService
     * @param uploadService
     */
    public ReconService(ReconConfigService reconConfigService, DatasetService datasetService, UploadService uploadService, ReconRepo reconRepo) {
        this.reconConfigService = reconConfigService;
        this.datasetService = datasetService;
        this.uploadService = uploadService;
        this.reconRepo = reconRepo;
    }

    /**
     * @return
     * @throws IllegalAccessException
     */
    public JsonArray runRecon() throws IllegalAccessException {
        // get recon config
        ReconConfig activeConfig = reconConfigService.getActiveConfig();

        // get datasets in scope for reconciliation
        List<UUID> datasetIds = uploadService.getDatasetIdsByUploadIds(activeConfig.getUploadIds());
        List<Dataset> datasets =  datasetService.getDatasetsByIds(datasetIds);

        // perform recon
        return new RowDeltaCalculator(activeConfig, datasets).calculate();
    }


    /**
     * @param reconJson json
     */
    public void saveRecon(JsonObject reconJson) {
        Recon recon = gson.fromJson(reconJson, Recon.class);
        reconRepo.save(recon);
    }


    /**
     * @param reconId
     */
    public void deleteRecon(String reconId) {
        UUID uuid = UUID.fromString(reconId);
        reconRepo.deleteById(uuid);
    }


    /**
     * @return
     */
    public JsonArray getAllReconIds() {
        // get iterable for all from rep
        Iterable<Recon> reconIter = reconRepo.findAll();

        // create rv for recons
        JsonArray recons = new JsonArray();

        // convert each to json and add to rv
        for (Recon recon : reconIter)
            recons.add(new JsonPrimitive(String.valueOf(recon.getId())));

         return recons;
    }
}