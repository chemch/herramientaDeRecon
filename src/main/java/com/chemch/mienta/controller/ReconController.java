package com.chemch.mienta.controller;

import com.chemch.mienta.service.ReconConfigService;
import com.chemch.mienta.service.ReconService;
import com.google.gson.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@Controller
@RequestMapping("/recon")
@Slf4j
public class ReconController {
    private final ReconService reconService;
    private final ReconConfigService reconConfigService;

    /**
     *
     * @param reconService
     */
    public ReconController(ReconService reconService, ReconConfigService reconConfigService) {
        this.reconService = reconService;
        this.reconConfigService = reconConfigService;
    }

    /**
     * @param configId
     * @return
     */
    @PostMapping("run/{configId}")
    @ResponseBody
    public ResponseEntity<String> runRecon(@PathVariable String configId) {
        try {
            // throw no active config is available or being provided
            if ((configId == null || configId.isEmpty()) &&
                    reconConfigService.getActiveConfig() == null)  {
                throw new Exception("No active config found");
            }  else if (!configId.isEmpty())
                reconConfigService.setActiveConfig(configId);

            // run recon
            JsonArray reconJson = reconService.runRecon();
            return new ResponseEntity<>(reconJson.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}