package com.chemch.mienta.calculator;

import com.chemch.mienta.model.ReconConfig;
import com.chemch.mienta.model.dataset.Dataset;
import com.google.gson.JsonArray;

import java.util.*;

/**
 * calculator that works at row level
 */
public class RowDeltaCalculator {
    private final ReconConfig reconConfig;
    private final List<Dataset> datasets;

    /**
     * @param reconConfig dependency
     * @param datasets to rec
     */
    public RowDeltaCalculator(ReconConfig reconConfig, List<Dataset> datasets) {
        this.reconConfig = reconConfig;
        this.datasets = datasets;
    }

    /**
     * @return array of breaks
     * @throws IllegalAccessException
     */
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
        for (Set<Map<String, Object>> thisDataset : datasetEntryLists) {
            for (Iterator<Map<String, Object>> thisRowIter = thisDataset.iterator(); thisRowIter.hasNext(); ) {
                Map<String, Object> thisRow = thisRowIter.next();

                for (Set<Map<String, Object>> thatDataset : datasetEntryLists) {
                    // skip any self references
                    if (thisDataset == thatDataset)
                        continue;

                    // compare this row to all rows in the other datasets, deleting them when a match is found
                    //for (Map<String, Object> thatRow : thatDatasetIter.next()) {
                    for (Iterator<Map<String, Object>> thatRowIter = thatDataset.iterator(); thatRowIter.hasNext(); ) {
                        Map<String, Object> thatRow = thatRowIter.next();

                        if (thisRow.equals(thatRow)) {
                            thisRowIter.remove();
                            thatRowIter.remove();
                        }
                    }
                }
            }
        }

        // add whatever rows remain as that is the delta of rows
        for ( Set<Map<String, Object>> deltaDataset: datasetEntryLists)
            if(!deltaDataset.isEmpty())
                rowDeltaArr.add(deltaDataset.toString());

        return rowDeltaArr;
    }
}