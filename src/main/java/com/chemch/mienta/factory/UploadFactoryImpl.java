package com.chemch.mienta.factory;

import com.chemch.mienta.model.upload.ExcelUpload;
import com.chemch.mienta.model.upload.JsonUpload;
import com.chemch.mienta.model.upload.Upload;
import com.chemch.mienta.model.upload.UploadType;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UploadFactoryImpl implements  UploadFactory {
    @Override
    public Upload create(JsonArray upload, UploadType type) {
        // create an instance of a supported upload type
        return switch (type) {
            case EXCEL -> new ExcelUpload(upload);
            case JSON -> new JsonUpload(upload);
        };
    }
}