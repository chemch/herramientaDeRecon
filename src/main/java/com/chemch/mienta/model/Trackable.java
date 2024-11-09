package com.chemch.mienta.model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 */
@Getter
public abstract class Trackable {
    @Expose @Setter
    private UUID trackId = UUID.randomUUID();;

    @Expose @Setter
    private LocalDateTime timestamp = LocalDateTime.now();
}