package com.chemch.mienta.model.dataset;

import com.chemch.mienta.model.Trackable;
import com.chemch.mienta.model.upload.Upload;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Dataset extends Trackable {
    @Getter
    private final Upload upload;

    public Dataset(Upload upload) {
        this.upload = upload;
    }
}