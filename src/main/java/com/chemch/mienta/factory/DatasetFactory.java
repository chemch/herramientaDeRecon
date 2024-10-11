package com.chemch.mienta.factory;

import com.chemch.mienta.model.dataset.Dataset;
import com.chemch.mienta.model.dataset.DatasetType;
import com.chemch.mienta.model.upload.Upload;

/**
 *
 */
public interface DatasetFactory {

    /**
     *
     * @param upload
     * @param type
     * @return
     */
    Dataset create(Upload upload, DatasetType type);
}