package com.terasofty.legiz.api.controllers;

import com.terasofty.legiz.api.domain.models.LegalService;
import com.terasofty.legiz.api.domain.service.LegalServicesService;
import com.terasofty.legiz.api.forms.LegalServiceForm;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api( tags = "Legal Services")
@RequestMapping("/api/cases")
@RequiredArgsConstructor
public class LegalServicesController {
    private final LegalServicesService legalServicesService;
    @GetMapping
    public ResponseEntity<List<LegalService>> getCases () {
        return ResponseEntity.ok().body(legalServicesService.getLegalServices());
    }

    @PostMapping
    public ResponseEntity<LegalService> createCase(@RequestBody LegalServiceForm payload) {
        LegalService legalService = legalServicesService.buildLegalService(payload);
        return ResponseEntity.ok().body(legalServicesService.createLegalService(legalService));
    }
}
