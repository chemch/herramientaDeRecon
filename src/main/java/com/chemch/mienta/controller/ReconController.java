package com.chemch.mienta.controller;

import com.chemch.mienta.service.ReconService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/recon")
@Slf4j
public class ReconController {
    private final ReconService reconService;

    public ReconController(ReconService reconService) {
        this.reconService = reconService;
    }

    @PostMapping("run")
    @ResponseBody
    public ResponseEntity<JSONObject> runRecon() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}