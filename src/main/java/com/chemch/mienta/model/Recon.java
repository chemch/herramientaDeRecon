package com.chemch.mienta.model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.Wither;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.UUID;

/**
 *
 */
@Node("Recon")
@Getter
public class Recon extends Trackable {
    /* neo4j id */
    @Id
    @GeneratedValue @Expose
    private UUID id;

    @Getter @Expose
    String breaks;
}