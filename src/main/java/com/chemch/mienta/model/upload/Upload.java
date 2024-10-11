package com.chemch.mienta.model.upload;


import com.chemch.mienta.model.Trackable;
import com.google.gson.JsonArray;
import lombok.Getter;

/**
 *
 */
public abstract class Upload extends Trackable implements Comparable<Upload>{
    @Getter
    private final JsonArray source;

    /**
     *
     * @param upload
     */
    protected Upload(JsonArray upload) {
        this.source = upload;
    }

    /**
     *
     * @param that the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Upload that) {
        return this.getTimestamp().compareTo(that.getTimestamp());
    }
}