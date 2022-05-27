package com.terasofty.legiz.api.domain.service;

import com.terasofty.legiz.api.domain.models.Advice;
import com.terasofty.legiz.api.domain.models.LegalService;
import com.terasofty.legiz.api.domain.persistence.LegalServiceRepository;
import com.terasofty.legiz.api.domain.service.implementation.AdviceServiceImpl;
import com.terasofty.legiz.api.domain.service.implementation.ClientsServiceImpl;
import com.terasofty.legiz.api.domain.service.implementation.LawyersServiceImpl;
import com.terasofty.legiz.api.domain.service.implementation.LegalServicesServiceImpl;
import com.terasofty.legiz.api.forms.LegalServiceForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LegalServicesServiceTest {
    @Mock
    private LegalServiceRepository legalServiceRepository;
    @Mock
    private ClientsServiceImpl clientsService;
    @Mock
    private LawyersServiceImpl lawyersService;
    @Mock
    private AdviceServiceImpl adviceService;
    @InjectMocks
    private LegalServicesServiceImpl legalServicesService;
    private LegalServiceForm data, data2;
    private LegalService service1, service2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        data = new LegalServiceForm("hyper", "john", "advice", "I need to make a payment contract");
        data2 = new LegalServiceForm("hyper", "john", "custom", "I need to make a payment contract");
        service1 = legalServicesService.buildLegalService(data);
        service2 = legalServicesService.buildLegalService(data);
    }
    @Test
    void getLegalServices() {
        when(legalServiceRepository.findAll()).thenReturn(List.of(service1, service2));
        List<LegalService> cases = legalServicesService.getLegalServices();
        assertNotNull(cases);
        assertEquals(cases.size(), 2);
    }
    @Test
    void createLegalService() {
        when(legalServiceRepository.save(Mockito.any(LegalService.class))).thenReturn(service1);
        LegalService legalService = legalServicesService.createLegalService(service1);
        assertNotNull(legalService);
    }
    @Test
    void buildAdviceLegalService() {
        LegalService legalService = legalServicesService.buildLegalService(data);
        assertNotNull(legalService.getAdvice());
        assertNull(legalService.getCustomCase());
        assertEquals(legalService.getAdvice().getDescription(), "I need to make a payment contract");
    }
    @Test
    void buildCustomCaseLegalAdvice() {
        LegalService legalService = legalServicesService.buildLegalService(data2);
        assertNotNull(legalService.getCustomCase());
        assertNull(legalService.getAdvice());
        assertEquals(legalService.getCustomCase().getDescription(), "I need to make a payment contract");
    }
}