package com.chemch.mienta.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Trackable {
    @Getter
    private final UUID id = UUID.randomUUID();

    @Getter
    private final LocalDateTime timestamp = LocalDateTime.now();
}