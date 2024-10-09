package com.chemch.mienta.model.upload;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;

public class JsonUpload extends Upload {
    @Getter
    private final UploadType type = UploadType.JSON;

    public JsonUpload(JsonArray upload) {
        super(upload);
    }
}