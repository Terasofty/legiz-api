package com.terasofty.legiz.api.domain.service;

import com.terasofty.legiz.api.domain.models.LegalService;
import com.terasofty.legiz.api.domain.models.User;
import com.terasofty.legiz.api.domain.service.implementation.LegalServicesServiceImpl;
import com.terasofty.legiz.api.forms.LegalServiceForm;

import java.util.List;

public interface LegalServicesService {
    List<LegalService> getLegalServices();
    List<LegalService> getCustomCases(User user);
    LegalService getLegalService(Long id);
    LegalService createLegalService(LegalService legalService);
    LegalService buildLegalService(LegalServiceForm legalServiceForm);

}
