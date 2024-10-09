package com.chemch.mienta.manager;

import com.chemch.mienta.factory.UploadFactory;
import com.chemch.mienta.model.upload.Upload;
import com.chemch.mienta.model.upload.UploadType;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UploadManager {
    private final UploadFactory factory;

    public UploadManager(UploadFactory factory) {
        this.factory = factory;
    }

    private void validate(JsonArray upload) {
        // TODO define validations - Milestone 2
    }

    public Upload parse(JsonArray uploadJson, UploadType type) {
        // validate uploaded json
        validate(uploadJson);

        // register parsed upload
        return this.factory.create(uploadJson, type);
    };
}