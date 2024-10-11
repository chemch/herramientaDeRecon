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

/**
 *
 */
@Controller
@RequestMapping("/config")
@Slf4j
public class ReconConfigController {
    private final ReconConfigService reconConfigService;

    /**
     *
     * @param reconConfigService
     */
    public ReconConfigController(ReconConfigService reconConfigService) {
        this.reconConfigService = reconConfigService;
    }

    /**
     *
     * @param config
     * @return
     */
    @PutMapping("load")
    @ResponseBody
    public ResponseEntity<String> setConfig(@RequestBody String config) {
        try {
            JsonObject configJson = (JsonObject) JsonParser.parseString(config);
            reconConfigService.loadConfig(configJson);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "ids", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getConfigIds() {
        List<String> uploadIds = reconConfigService.getConfigIds();
        return new ResponseEntity<>(uploadIds, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping("set/{id}")
    @ResponseBody
    public ResponseEntity<String> activateConfig(@PathVariable String id) {
        try {
            reconConfigService.setActiveConfig(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param config
     * @param id
     * @return
     */
    @PutMapping("customize/{id}")
    @ResponseBody
    public ResponseEntity<String> customizeConfig(@RequestBody String config, @PathVariable String id) {
        try {
            JsonObject configJson = (JsonObject) JsonParser.parseString(config);
            reconConfigService.customizeConfig(id, configJson);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}