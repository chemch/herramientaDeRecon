package com.chemch.mienta.model.upload;

import com.google.gson.JsonArray;
import lombok.Getter;

/**
 *
 */
public class JsonUpload extends Upload {
    @Getter
    private final UploadType type = UploadType.JSON;

    /**
     *
     * @param upload
     */
    public JsonUpload(JsonArray upload) {
        super(upload);
    }
}