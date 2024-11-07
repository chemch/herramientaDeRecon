package com.chemch.mienta.manager;

import com.chemch.mienta.factory.UploadFactory;
import com.chemch.mienta.model.dataset.Dataset;
import com.chemch.mienta.model.upload.Upload;
import com.chemch.mienta.model.upload.UploadType;
import com.google.gson.JsonArray;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.zip.DataFormatException;
import static com.chemch.mienta.common.Constants.INVALID_DATA_MSG;

/**
 *
 */
@Component
@Slf4j
public class UploadManager {
    private final int MAX_LOG_CHAR_LIMIT = 15;

    @Getter
    private final Map<UUID, UUID> uploadToDatasetMap = new HashMap<>();

    private final UploadFactory factory;

    /**
     *
     * @param factory
     */
    public UploadManager(UploadFactory factory) {
        this.factory = factory;
    }

    private void validate(JsonArray upload) throws DataFormatException {
        log.info("Validating {}...", upload.toString().substring(0, Math.min(MAX_LOG_CHAR_LIMIT, upload.toString().length())));

        // throw if empty upload is provided
        if (upload.isEmpty())
            throw new DataFormatException(INVALID_DATA_MSG);
    }

    /**
     *
     * @param uploadJson
     * @param type
     * @return
     */
    public Upload parse(JsonArray uploadJson, UploadType type) throws DataFormatException {
        // validate uploaded json
        validate(uploadJson);

        // register parsed upload
        return this.factory.create(uploadJson, type);
    };

    /**
     * @param upload
     * @param dataset
     */
    public void register(Upload upload, Dataset dataset) {
        this.uploadToDatasetMap.put(upload.getTrackId(), dataset.getTrackId());
    }
}