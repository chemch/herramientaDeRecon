package com.chemch.mienta.controller;

import com.chemch.mienta.service.ReconConfigService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@Controller
@RequestMapping("/config")
@Slf4j
public class ReconConfigController {
    private final ReconConfigService reconConfigService;

    public ReconConfigController(ReconConfigService reconConfigService) {
        this.reconConfigService = reconConfigService;
    }

    @PutMapping("load")
    @ResponseBody
    public ResponseEntity<JsonObject> setConfig(@RequestBody String config) {
        try {
            JsonObject configJson = (JsonObject) JsonParser.parseString(config);
            reconConfigService.loadConfig(configJson);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "ids", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getConfigIds() {
        List<String> uploadIds = reconConfigService.getConfigIds();
        return new ResponseEntity<>(uploadIds, HttpStatus.OK);
    }

    @PostMapping("set/{id}")
    @ResponseBody
    public ResponseEntity<JsonObject> activateConfig(@PathVariable String id) {
        try {
            reconConfigService.setActiveConfig(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("customize/{id}")
    @ResponseBody
    public ResponseEntity<JsonObject> customizeConfig(@RequestBody String config, @PathVariable String id) {
        try {
            JsonObject configJson = (JsonObject) JsonParser.parseString(config);
            reconConfigService.customizeConfig(id, configJson);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}