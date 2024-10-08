package com.chemch.mienta.service;

import com.chemch.mienta.model.ReconConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ReconConfigService {
    @Getter
    private ReconConfig activeConfig;

    @Getter
    private Map<UUID, ReconConfig> configs = new HashMap<>();

    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public void loadConfig(JsonObject configJson) {
        ReconConfig config = gson.fromJson(configJson, ReconConfig.class);
        this.configs.put(config.getId(), config);
    }

    public void setActiveConfig(String reconConfig) {
        this.activeConfig = configs.get(UUID.fromString(reconConfig));
    }

    public List<String> getConfigIds() {
        List<String> configIds = new ArrayList<>();
        this.configs.forEach((id, _) -> configIds.add(id.toString()));
        return configIds;
    }

    public void customizeConfig(String id, JsonObject configJson) {
        // TODO - Milestone 2 - customize field configuration parameters like acceptable margin
    }
}