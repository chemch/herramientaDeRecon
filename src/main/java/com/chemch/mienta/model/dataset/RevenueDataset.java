package com.chemch.mienta.model.dataset;

import com.chemch.mienta.model.upload.Upload;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.*;

/**
 *
 */
public class RevenueDataset extends Dataset {
    @Getter
    private final DatasetType type = DatasetType.REVENUE;

    @Getter
    private final Set<Entry> entries;

    @Override
    public Set<Map<String, Object>> getGenerifiedEntries(List<String> compareFields) throws IllegalAccessException {
        // convert set of entries to objects
        Set<Map<String, Object>> generifiedEntries = new HashSet<>();

        // for each entry, convert it to a hashmap
        for(Entry entry : this.entries) {
            Map<String, Object> entryValueMap = new HashMap<>();
            Field[] entryFields = entry.getClass().getDeclaredFields();

            // parse fields on the object using reflection
            for (Field field: entryFields) {
                // only include the field if it was specified as a compare field in the configuration
                if(compareFields.contains(field.getName())) {
                    field.setAccessible(true);
                    entryValueMap.put(field.getName(), field.get(entry));
                }
            }

            // add entry map to entries set
            generifiedEntries.add(entryValueMap);
        }

        return generifiedEntries;
    };

    private RevenueDataset(Builder builder) {
        // set upload
        super(builder.upload);

        // set dataset specific attributes
        this.entries = builder.entries;
    }

    @Getter
    public static class Entry implements Comparable<Entry> {
        private final String fund;

        private final Integer year;

        private final Double amount;

        private Double potentialHighAmount;

        private Double potentialLowAmount;

        private Entry(String fund, Integer year, Double amount, Double potentialHighAmount, Double potentialLowAmount) {
            this.fund = fund;
            this.year = year;
            this.amount = amount;
            this.potentialHighAmount = potentialHighAmount;
            this.potentialLowAmount = potentialLowAmount;
        }

        private void calculatePotentialHigh( JsonObject row) {
            Double multiplier = row.has("multiplier") ? row.get("multiplier").getAsDouble() : 1;
            this.potentialHighAmount = this.getAmount() * multiplier;
        }

        private void calculatePotentialLow(JsonObject row) {
            Double multiplier = row.has("multiplier") ? row.get("multiplier").getAsDouble() : 1;
            this.potentialLowAmount = this.getAmount() / multiplier;
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

    /**
     *
     */
    public static class Builder {
        private final Gson gson = new Gson();

        // mandatory parameters
        private final Upload upload;
        private final Set<Entry> entries = new TreeSet<>();

        public Builder(Upload upload) {
            // only true mandatory field
            this.upload = upload;
        }

        /**
         *
         * @return
         */
        public RevenueDataset build() {
            upload.getSource().forEach(_row -> {
                // convert json element to json object
                JsonObject row = ((JsonObject) _row);

                Entry entry = gson.fromJson(row, Entry.class);

                // set non-mandatory field values
                entry.calculatePotentialHigh(row);
                entry.calculatePotentialLow(row);

                this.entries.add(entry);
            });

            return new RevenueDataset(this);
        }
    }
}