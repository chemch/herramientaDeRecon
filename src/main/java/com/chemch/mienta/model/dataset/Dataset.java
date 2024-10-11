package com.chemch.mienta.model.dataset;

import com.chemch.mienta.model.Trackable;
import com.chemch.mienta.model.upload.Upload;
import lombok.Getter;

/**
 *
 */
public abstract class Dataset extends Trackable {
    @Getter
    private final Upload upload;

    /**
     *
     * @param upload
     */
    public Dataset(Upload upload) {
        this.upload = upload;
    }
}