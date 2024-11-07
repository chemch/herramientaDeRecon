package com.chemch.mienta.service;

import com.chemch.mienta.manager.DatasetManager;
import com.chemch.mienta.manager.UploadManager;
import com.chemch.mienta.model.dataset.Dataset;
import com.chemch.mienta.model.dataset.DatasetType;
import com.chemch.mienta.model.upload.Upload;
import com.chemch.mienta.model.upload.UploadType;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.DataFormatException;

import static com.chemch.mienta.common.Constants.INVALID_DATA_MSG;

/**
 *
 */
@Service
@Slf4j
public class UploadService {
    private final UploadManager uploadManager;
    private final DatasetManager datasetManager;

    /**
     *
     * @param uploadManager
     * @param datasetManager
     */
    public UploadService(UploadManager uploadManager, DatasetManager datasetManager) {
        this.uploadManager = uploadManager;
        this.datasetManager = datasetManager;
    }

    /**
     *
     * @param upload
     * @param uploadType
     * @param datasetType
     */
    public void uploadDataset(JsonArray upload, UploadType uploadType, DatasetType datasetType) throws DataFormatException {
        // parse upload
        Upload parsed = uploadManager.parse(upload, uploadType);

        // convert to dataset
        Dataset converted = datasetManager.convert(parsed, datasetType);

        // register upload to dataset for quick lookup
        uploadManager.register(parsed, converted);
    }

    /**
     *
     * @param uploadArr
     * @param uploadType
     * @param datasetType
     */
    public void uploadMultiDataset(JsonArray uploadArr, UploadType uploadType, DatasetType datasetType) throws DataFormatException {
        if(uploadArr.isEmpty())
            throw new DataFormatException(INVALID_DATA_MSG);

        for (JsonElement upload : uploadArr) {
            JsonArray uploadJsonArray = (JsonArray) upload;
            uploadDataset(uploadJsonArray, uploadType, datasetType);
        }
    }

    /**
     *
     * @return
     */
    public List<String> getUploadIds() {
        List<String> uploadIds = new ArrayList<>();
        datasetManager.getDatasets().forEach((_, dataset) -> uploadIds.add(dataset.getUpload().getTrackId().toString()));
        return uploadIds;
    }

    public List<UUID> getDatasetIdsByUploadIds(List<UUID> uploadIds) {
        List<UUID> datasetIds = new ArrayList<>();

        // pull datasetIds from upload mapping
        for (UUID uploadId : uploadIds)
            datasetIds.add(uploadManager.getUploadToDatasetMap().get(uploadId));

        return datasetIds;
    }
}