package com.chemch.mienta.calculator;

import com.chemch.mienta.model.ReconConfig;
import com.chemch.mienta.model.dataset.Dataset;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RowDeltaCalculator {
    private final ReconConfig reconConfig;
    private final List<Dataset> datasets;

    public RowDeltaCalculator(ReconConfig reconConfig, List<Dataset> datasets) {
        this.reconConfig = reconConfig;
        this.datasets = datasets;
    }

    public JsonArray calculate() throws IllegalAccessException {
        // get fields to be compared on
        List<String> compareFields = reconConfig.getFields();
        List<Set<Map<String, Object>>> datasetEntryLists = new ArrayList<>();

        // convert objects to generified hashmaps for easy comparison that is available cross class
        for (Dataset dataset : datasets)
            datasetEntryLists.add(dataset.getGenerifiedEntries(compareFields));

        // create json array to hold breaks
        JsonArray rowDeltaArr = new JsonArray();

        // check rows for mismatches
        for ( Set<Map<String, Object>> thisDataset: datasetEntryLists) {
            for (Map<String, Object> thisRow : thisDataset) {
                for ( Set<Map<String, Object>> thatDataset: datasetEntryLists) {

                    // skip any self references
                    if(thisDataset == thatDataset)
                        continue;

                    // compare this row to all rows in the other datasets, deleting them when a match is found
                    boolean thisRowMatched = false;
                    for (Map<String, Object> thatRow : thatDataset) {
                        if (thisRow.equals(thatRow))
                            thisRowMatched = true;
                    }

                    // after looping through other datasets, add this as break if not matched
                    if (!thisRowMatched) {
                        rowDeltaArr.add(thisRow.toString());
                    }
                }
            }
        }

        return rowDeltaArr;
    }
}