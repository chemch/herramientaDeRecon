package com.chemch.mienta.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.*;

/**
 *
 */
public class ReconConfig extends Trackable {
    @Getter @Expose
    private final List<String> fields = new ArrayList<>();

    @Getter @Expose
    @SerializedName("uploads")
    private final List<UUID> uploadIds = new ArrayList<>();

    @Getter @Expose
    @SerializedName("datasets")
    private final List<UUID> datasetIds = new ArrayList<>();
}