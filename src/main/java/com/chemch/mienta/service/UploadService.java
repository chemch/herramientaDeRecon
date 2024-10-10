package com.chemch.mienta.service;

import com.chemch.mienta.manager.DatasetManager;
import com.chemch.mienta.manager.UploadManager;
import com.chemch.mienta.model.dataset.DatasetType;
import com.chemch.mienta.model.upload.Upload;
import com.chemch.mienta.model.upload.UploadType;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void uploadMultiDataset(JsonArray uploadArr, UploadType uploadType, DatasetType datasetType) {
        for (JsonElement upload : uploadArr) {
            JsonArray uploadJsonArray = (JsonArray) upload;
            uploadDataset(uploadJsonArray, uploadType, datasetType);
        }
    }

    public List<String> getUploadIds() {
        List<String> uploadIds = new ArrayList<>();
        datasetManager.getDatasets().forEach((_, dataset) -> uploadIds.add(dataset.getUpload().getId().toString()));
        return uploadIds;
    }
}