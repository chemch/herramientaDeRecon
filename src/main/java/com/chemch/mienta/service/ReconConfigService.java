package com.chemch.mienta.service;

import com.chemch.mienta.model.ReconConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 */
@Service
@Slf4j
public class ReconConfigService {
    private final String NO_CONFIG_ID = "NOT_AVAILABLE";

    @Getter
    private ReconConfig activeConfig;

    @Getter
    private Map<UUID, ReconConfig> configs = new HashMap<>();

    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     *
     * @param configJson
     */
    public void loadConfig(JsonObject configJson) {
        ReconConfig config = gson.fromJson(configJson, ReconConfig.class);
        this.configs.put(config.getTrackId(), config);
    }

    public String getActiveConfigId() {
        ReconConfig activeReconConfig = getActiveConfig();
        return activeReconConfig != null ? activeReconConfig.getTrackId().toString(): NO_CONFIG_ID;
    }

    /**
     *
     * @param reconConfig
     */
    public void setActiveConfig(String reconConfig) {
        this.activeConfig = configs.get(UUID.fromString(reconConfig));
    }

    /**
     *
     * @return
     */
    public List<String> getConfigIds() {
        List<String> configIds = new ArrayList<>();
        this.configs.forEach((id, _) -> configIds.add(id.toString()));
        return configIds;
    }

    /**
     *
     * @param id
     * @param configJson
     */
    public void customizeConfig(String id, JsonObject configJson) {
        // TODO - Milestone 2 - customize field configuration parameters like acceptable margin
    }
}