package com.chemch.mienta.controller;

import com.chemch.mienta.model.dataset.DatasetType;
import com.chemch.mienta.model.upload.UploadType;
import com.chemch.mienta.service.UploadService;
import com.google.gson.JsonArray;
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
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PutMapping(value = "dataset/{datasetType}")
    public ResponseEntity<JsonObject> uploadDataset(@RequestBody String upload, @PathVariable String datasetType) {
        try {
            JsonArray uploadJson = (JsonArray) JsonParser.parseString(upload);
            uploadService.uploadDataset(uploadJson, UploadType.JSON, DatasetType.valueOf(datasetType.toUpperCase()));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "ids", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getUploadIds() {
        List<String> uploadIds = uploadService.getUploadIds();
        return new ResponseEntity<>(uploadIds, HttpStatus.OK);
    }

    @PutMapping("multi-dataset/{datasetType}")
    @ResponseBody
    public ResponseEntity<JsonObject> uploadMultiDataset(@RequestBody String multiUpload, @PathVariable String datasetType) {
        try {
            JsonArray uploadJsonArray = (JsonArray) JsonParser.parseString(multiUpload);
            // pass full set of datasets to be parsed
            uploadService.uploadMultiDataset(uploadJsonArray, UploadType.JSON, DatasetType.valueOf(datasetType.toUpperCase()));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}