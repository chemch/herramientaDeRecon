package com.chemch.mienta.model;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 */
@Getter
public abstract class Trackable {
    @Expose
    private final UUID trackId = UUID.randomUUID();

    @Expose
    private final LocalDateTime timestamp = LocalDateTime.now();
}