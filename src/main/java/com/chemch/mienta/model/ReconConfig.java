package com.chemch.mienta.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.*;

/**
 *
 */
@Getter
public class ReconConfig extends Trackable {
    @Expose
    private final List<String> fields = new ArrayList<>();

    @Expose
    @SerializedName("uploads")
    private final List<UUID> uploadIds = new ArrayList<>();

    @Expose
    @SerializedName("datasets")
    private final List<UUID> datasetIds = new ArrayList<>();
}