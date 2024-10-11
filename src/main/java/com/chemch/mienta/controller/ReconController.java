package com.chemch.mienta.controller;

import com.chemch.mienta.service.ReconService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/recon")
@Slf4j
public class ReconController {
    private final ReconService reconService;

    /**
     *
     * @param reconService
     */
    public ReconController(ReconService reconService) {
        this.reconService = reconService;
    }

    /**
     *
     * @return
     */
    @PostMapping("run")
    @ResponseBody
    public ResponseEntity<String> runRecon() {
        try {
            JsonArray reconJson = reconService.runRecon();
            return new ResponseEntity<>(reconJson.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}