package com.chemch.mienta.model;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Trackable {
    @Getter @Expose
    private final UUID id = UUID.randomUUID();

    @Getter @Expose
    private final LocalDateTime timestamp = LocalDateTime.now();
}