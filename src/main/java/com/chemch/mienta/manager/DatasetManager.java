package com.chemch.mienta.manager;

import com.chemch.mienta.factory.DatasetFactory;
import com.chemch.mienta.model.dataset.Dataset;
import com.chemch.mienta.model.dataset.DatasetType;
import com.chemch.mienta.model.upload.Upload;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.zip.DataFormatException;

import static com.chemch.mienta.common.Constants.INVALID_DATA_MSG;

/**
 *
 */
@Component
@Slf4j
public class DatasetManager {
    @Getter
    private final Map<UUID, Dataset> datasets = new HashMap<>();

    private final DatasetFactory factory;

    /**
     *
     * @param factory
     */
    public DatasetManager(DatasetFactory factory) {
        this.factory = factory;
    }

    private void validate(Dataset dataset) throws DataFormatException {
        log.info("Validating dataset {}...", dataset);

        if (dataset == null || dataset.getUpload() == null)
            throw new DataFormatException(INVALID_DATA_MSG);
    }

    /**
     *
     * @param upload
     * @param type
     * @return
     */
    public Dataset convert(Upload upload, DatasetType type) throws DataFormatException {
        // convert upload to applicable dataset
        Dataset dataset = factory.create(upload, type);

        // validate newly formed dataset
        validate(dataset);

        // register new dataset
        this.datasets.put(dataset.getTrackId(), dataset);

        return dataset;
    }
}