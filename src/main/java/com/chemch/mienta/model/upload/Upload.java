package com.chemch.mienta.model.upload;


import com.chemch.mienta.model.Trackable;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;

public abstract class Upload extends Trackable implements Comparable<Upload>{
    @Getter
    private final JsonArray source;

    protected Upload(JsonArray upload) {
        this.source = upload;
    }

    @Override
    public int compareTo(Upload that) {
        return this.getTimestamp().compareTo(that.getTimestamp());
    }
}