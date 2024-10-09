package com.chemch.mienta.service;

import com.chemch.mienta.manager.DatasetManager;
import com.chemch.mienta.manager.UploadManager;
import com.chemch.mienta.model.dataset.DatasetType;
import com.chemch.mienta.model.upload.Upload;
import com.chemch.mienta.model.upload.UploadType;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UploadService {
    private final UploadManager uploadManager;
    private final DatasetManager datasetManager;

    public UploadService(UploadManager uploadManager, DatasetManager datasetManager) {
        this.uploadManager = uploadManager;
        this.datasetManager = datasetManager;
    }

    public void uploadDataset(JsonArray upload, UploadType uploadType, DatasetType datasetType) {
        Upload parsed = uploadManager.parse(upload, uploadType);
        datasetManager.convert(parsed, datasetType);
    }

    public void uploadMultiDataset(JsonArray uploads, UploadType uploadType, DatasetType datasetType) {
//        for (JsonArray)
//        for (JsonElement dataset : uploads) {
//            uploadDataset(dataset.getAsJsonObject(), uploadType, datasetType);
//        }
    }
}