package com.chemch.mienta.model.dataset;

import com.chemch.mienta.model.Trackable;
import com.chemch.mienta.model.upload.Upload;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public abstract class Dataset extends Trackable {
    @Getter
    private final Upload upload;

    /**
     * @return
     */
    public abstract Set<Map<String, Object>> getGenerifiedEntries(List<String> compareFields) throws IllegalAccessException;

    /**
     *
     * @param upload
     */
    public Dataset(Upload upload) {
        this.upload = upload;
    }
}