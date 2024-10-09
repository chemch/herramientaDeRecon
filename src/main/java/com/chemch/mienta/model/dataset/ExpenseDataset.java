package com.chemch.mienta.model.dataset;

import com.chemch.mienta.model.upload.Upload;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

public class ExpenseDataset extends Dataset {
    @Getter
    private final DatasetType type = DatasetType.EXPENSE;

    public ExpenseDataset(Upload upload) {
        super(upload);
    }
}