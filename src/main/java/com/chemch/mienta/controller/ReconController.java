package com.chemch.mienta.controller;

import com.chemch.mienta.service.ReconConfigService;
import com.chemch.mienta.service.ReconService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * recon controller
 */
@Controller
@RequestMapping("/recon")
@Slf4j
public class ReconController {
    private final ReconService reconService;
    private final ReconConfigService reconConfigService;

    /**
     *
     * @param reconService dependency
     */
    public ReconController(ReconService reconService, ReconConfigService reconConfigService) {
        this.reconService = reconService;
        this.reconConfigService = reconConfigService;
    }

    /**
     * @param configId to use for run
     * @return deltas
     */
    @PostMapping("run/{configId}")
    @ResponseBody

    public ResponseEntity<String> runReconWithParam(@PathVariable(required = true) String configId) {
        try {
            // throw no active config is available or being provided
            if ((configId == null || configId.isEmpty()) &&
                    reconConfigService.getActiveConfig() == null)  {
                throw new Exception("No active config found");
            }  else if (configId != null && !configId.isEmpty())
                reconConfigService.setActiveConfig(configId);
            else
                log.info("No active config provided");

            // run recon
            JsonArray reconJson = reconService.runRecon();
            return new ResponseEntity<>(reconJson.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("runs")
    @ResponseBody

    public ResponseEntity<String> getRecons() {
        try {
           // get recons
            JsonArray reconIdsArr = reconService.getAllReconIds();
            return new ResponseEntity<>(reconIdsArr.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return deltas
     */
    @PostMapping("run")
    @ResponseBody

    public ResponseEntity<String> runReconWithoutParam() {
        try {
            // throw no active config is available or being provided
            if (reconConfigService.getActiveConfig() == null)
                throw new Exception("No active config found");

            // run recon
            JsonArray reconJson = reconService.runRecon();
            return new ResponseEntity<>(reconJson.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("save")
    @ResponseBody

    public ResponseEntity<String> saveRecon(@RequestBody String reconInput) {
        try {
            if (reconInput != null && !reconInput.isEmpty()) {
                JsonObject reconArray = (JsonObject) JsonParser.parseString(reconInput);
                reconService.saveRecon(reconArray);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{reconId}")
    @ResponseBody

    public ResponseEntity<String> deleteRecon(@PathVariable String reconId) {
        try {
            reconService.deleteRecon(reconId);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}