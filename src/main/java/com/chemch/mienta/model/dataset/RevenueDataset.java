package com.chemch.mienta.model.dataset;

import com.chemch.mienta.model.upload.Upload;
import com.google.gson.Gson;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RevenueDataset extends Dataset {
    @Getter
    private final DatasetType type = DatasetType.REVENUE;

    @Getter
    private final List<Entry> entries;

    private RevenueDataset(Builder builder) {
        // set upload
        super(builder.upload);

        // set dataset specific attributes
        this.entries = builder.entries;
    }

    private static class Entry {
        @Getter
        private final String fund;

        @Getter
        private final Integer year;

        @Getter
        private final Double amount;

        private Entry(String fund, Integer year, Double amount) {
            this.fund = fund;
            this.year = year;
            this.amount = amount;
        }
    }

    public static class Builder {
        // mandatory parameters
        private final Upload upload;
        private final List<Entry> entries = new ArrayList<>();

        public Builder(Upload upload) {
            // only true mandatory field
            this.upload = upload;
        }

        public RevenueDataset build() {
            Gson gson = new Gson();
            upload.getSource().forEach(row -> {
                Entry entry = gson.fromJson(row, Entry.class);
                this.entries.add(entry);
            });

            return new RevenueDataset(this);
        }
    }
}