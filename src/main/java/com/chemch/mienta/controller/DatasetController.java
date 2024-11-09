package com.chemch.mienta.controller;

import com.chemch.mienta.service.DatasetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 *
 */
@Controller
@RequestMapping("/dataset")
@Slf4j
public class DatasetController {
    private final DatasetService datasetService;

    /**
     *
     * @param datasetService
     */
    public DatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    /**
     *
     * @return
     */

    @GetMapping(value = "ids", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getDatasetIds() {
        List<String> uploadIds = datasetService.getDatasetIds();
        return new ResponseEntity<>(uploadIds, HttpStatus.OK);
    }
}