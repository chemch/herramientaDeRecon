package com.chemch.mienta.model.upload;

import com.google.gson.JsonArray;
import lombok.Getter;

/**
 *
 */
public class ExcelUpload extends Upload {
    @Getter
    private final UploadType type = UploadType.EXCEL;

    /**
     *
     * @param upload
     */
    public ExcelUpload(JsonArray upload) {
        super(upload);
    }
}