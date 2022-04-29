package com.terasofty.legiz.controllers;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "hirings")
@Api( tags = "Hirings")
public class HiringsController {
    @GetMapping
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok("AAAA");
    }
}
