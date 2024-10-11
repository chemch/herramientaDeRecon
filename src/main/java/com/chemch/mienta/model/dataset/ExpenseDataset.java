package com.chemch.mienta.model.dataset;

import com.chemch.mienta.model.upload.Upload;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.*;

/**
 *
 */
public class ExpenseDataset extends Dataset {
    @Getter
    private final DatasetType type = DatasetType.EXPENSE;

    @Getter
    private final Set<Entry> entries = new TreeSet<>();

    @Override
    public Set<Map<String, Object>> getGenerifiedEntries(List<String> compareFields) throws IllegalAccessException {
        // convert set of entries to objects
        Set<Map<String, Object>> generifiedEntries = new HashSet<>();

        // for each entry, convert it to a hashmap
        for(ExpenseDataset.Entry entry : this.entries) {
            Map<String, Object> entryValueMap = new HashMap<>();
            Field[] entryFields = entry.getClass().getDeclaredFields();

            // parse fields on the object using reflection
            for (Field field: entryFields) {
                field.setAccessible(true);
                entryValueMap.put(field.getName(), field.get(entry));
            }

            // add entry map to entries set
            generifiedEntries.add(entryValueMap);
        }

        return generifiedEntries;
    };

    /**
     *
     * @param upload
     */
    public ExpenseDataset(Upload upload) {
        super(upload);

        // set simple field values
        this.getUpload().getSource().forEach(_row -> {
            // convert to json object
            JsonObject row = ((JsonObject) _row);

            // get simple, yet mandatory fields
            Integer unitCount = row.get("unitCost").getAsInt();
            Double unitCost = row.get("unitCost").getAsDouble();

            // get optional fields
            String account = row.has("account") ? row.get("account").getAsString() : "N/A";

            Entry entry = new Entry(account, unitCount, unitCost);
            this.entries.add(entry);
        });
    }

    public static class Entry implements Comparable<Entry> {
        @Getter
        private final String account;

        @Getter
        private final Integer unitCount;

        @Getter
        private final Double unitCost;

        @Getter
        private final Double totalCost;

        private Entry(String account, Integer unitCount, Double unitCost) {
            this.account = account;
            this.unitCount = unitCount;
            this.unitCost = unitCost;
            this.totalCost = unitCost * unitCount;
        }

        /**
         *
         * @param that the object to be compared.
         * @return
         */
        @Override
        public int compareTo(Entry that) {
            return this.hashCode() - that.hashCode();
        }
    }
}