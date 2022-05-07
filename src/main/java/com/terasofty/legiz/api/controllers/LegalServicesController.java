package com.terasofty.legiz.api.controllers;

import com.terasofty.legiz.api.domain.models.LegalService;
import com.terasofty.legiz.api.domain.service.LegalServicesService;
import com.terasofty.legiz.api.forms.LegalServiceForm;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/{caseId}")
    public ResponseEntity<LegalService> getCase(@PathVariable String caseId) {
        return ResponseEntity.ok().body(legalServicesService.getLegalService(Long.parseLong(caseId)));
    }

    @PostMapping
    public ResponseEntity<LegalService> createCase(@RequestBody LegalServiceForm payload) {
        LegalService legalService = legalServicesService.buildLegalService(payload);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/cases/create").toUriString());
        return ResponseEntity.created(uri).body(legalServicesService.createLegalService(legalService));
    }
}
