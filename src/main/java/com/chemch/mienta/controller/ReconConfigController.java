package com.chemch.mienta.controller;

import com.chemch.mienta.service.ReconConfigService;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



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
    public ResponseEntity<JsonObject> loadConfig() {


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
