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

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 *
 */
@Controller
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    private final UploadService uploadService;

    /**
     *
     * @param uploadService
     */
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    /**
     *
     * @param upload
     * @param datasetType
     * @return
     */

    @PutMapping(value = "dataset/{datasetType}")
    public ResponseEntity<String> uploadDataset(@RequestBody String upload, @PathVariable String datasetType) {
        try {
            JsonArray uploadJson = (JsonArray) JsonParser.parseString(upload);
            uploadService.uploadDataset(uploadJson, UploadType.JSON, DatasetType.valueOf(datasetType.toUpperCase()));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param upload
     * @return
     */

    @PutMapping(value = "test")
    public ResponseEntity<String> testEndpoint(@RequestBody String upload) {
        try {
            log.info("Test endpoint called with {}", upload);
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
    public ResponseEntity<List<String>> getUploadIds() {
        log.info("Getting upload ids...");
        List<String> uploadIds = uploadService.getUploadIds();
        return new ResponseEntity<>(uploadIds, HttpStatus.OK);
    }

    /**
     *
     * @param multiUpload
     * @param datasetType
     * @return
     */

    @PutMapping("multi-dataset/{datasetType}")
    @ResponseBody
    public ResponseEntity<String> uploadMultiDataset(@RequestBody String multiUpload, @PathVariable String datasetType) {
        try {
            JsonArray uploadJsonArray = (JsonArray) JsonParser.parseString(multiUpload);
            // pass full set of datasets to be parsed
            uploadService.uploadMultiDataset(uploadJsonArray, UploadType.JSON, DatasetType.valueOf(datasetType.toUpperCase()));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}