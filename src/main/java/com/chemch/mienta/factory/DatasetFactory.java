package com.chemch.mienta.factory;

import com.chemch.mienta.model.dataset.Dataset;
import com.chemch.mienta.model.dataset.DatasetType;
import com.chemch.mienta.model.upload.Upload;

public interface DatasetFactory {
    Dataset create(Upload upload, DatasetType type);
}