package com.chemch.mienta.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
@RestController
@Slf4j
public class AuthController {

    /**
     *
     * @return
     */
    @PreAuthorize("permitAll()")
    @GetMapping(value = "/username")
    public String currentUserName(Principal principal) {
        log.info("UserName: " + principal.getName());
        return principal.getName();
    }
}