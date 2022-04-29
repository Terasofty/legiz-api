package com.terasofty.legiz.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiringsController {
    @GetMapping(value = "hiring")
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok("AAAA");
    }
}
