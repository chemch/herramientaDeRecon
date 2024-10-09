package com.chemch.mienta.model.upload;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;

public class ExcelUpload extends Upload {
    @Getter
    private final UploadType type = UploadType.EXCEL;

    public ExcelUpload(JsonArray upload) {
        super(upload);
    }
}