package com.terasofty.legiz.api.controllers;

import com.terasofty.legiz.api.domain.models.LegalService;
import com.terasofty.legiz.api.domain.models.User;
import com.terasofty.legiz.api.domain.service.LegalServicesService;
import com.terasofty.legiz.api.domain.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api( tags = "Custom Cases")
@RequestMapping("/api/custom_cases")
@RequiredArgsConstructor
public class CustomCasesController {
    private final LegalServicesService legalServicesService;
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<LegalService>> getCustomCases (@RequestHeader("Authorization") String authHeader) {
        User user = userService.getCurrentUser(authHeader);
        return ResponseEntity.ok().body(legalServicesService.getCustomCases(user));
    }
}
