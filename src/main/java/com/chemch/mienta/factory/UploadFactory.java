package com.chemch.mienta.factory;

import com.chemch.mienta.model.upload.Upload;
import com.chemch.mienta.model.upload.UploadType;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface UploadFactory {
    Upload create(JsonArray jsonUpload, UploadType type);
}