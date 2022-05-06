package com.terasofty.legiz.api.domain.service.implementation;

import com.terasofty.legiz.api.domain.models.Advice;
import com.terasofty.legiz.api.domain.models.CustomCase;
import com.terasofty.legiz.api.domain.models.LegalService;
import com.terasofty.legiz.api.domain.persistence.LegalServiceRepository;
import com.terasofty.legiz.api.domain.service.*;
import com.terasofty.legiz.api.forms.LegalServiceForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.terasofty.legiz.api.domain.enumerable.LegalServiceStatus.DRAFT;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LegalServicesServiceImpl implements LegalServicesService {
    private final LegalServiceRepository legalServiceRepository;
    private final ClientsService clientsService;
    private final LawyersService lawyersService;
    private final AdviceService adviceService;
    private final CustomCasesService customCasesService;
    @Override
    public List<LegalService> getLegalServices() {
        return legalServiceRepository.findAll();
    }

    @Override
    public LegalService getLegalService(Long id) {
        return legalServiceRepository.getById(id);
    }

    @Override
    public LegalService createLegalService(LegalService legalService) {
        return legalServiceRepository.save(legalService);
    }
    @Override
    public LegalService buildLegalService(LegalServiceForm legalServiceForm) {
        LegalService legalService = new LegalService(
                null,
                DRAFT,
                clientsService.getClient(legalServiceForm.getClientUsername()),
                lawyersService.getLawyer(legalServiceForm.getLawyerUsername()),
                null, null, new ArrayList<>()
        );
        if (Objects.equals(legalServiceForm.getType(), "advice")) {
            legalService.setAdvice(new Advice(null, legalServiceForm.getDescription(), null, null));
        } else if (Objects.equals(legalServiceForm.getType(), "custom")) {
            legalService.setCustomCase(new CustomCase(null, legalServiceForm.getDescription()));
        }
        return legalService;
    }
}
