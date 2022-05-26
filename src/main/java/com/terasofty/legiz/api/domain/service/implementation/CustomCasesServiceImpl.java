package com.terasofty.legiz.api.domain.service.implementation;

import com.terasofty.legiz.api.domain.models.CustomCase;
import com.terasofty.legiz.api.domain.persistence.CustomCaseRepository;
import com.terasofty.legiz.api.domain.service.CustomCasesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomCasesServiceImpl implements CustomCasesService {
    private final CustomCaseRepository customCaseRepository;
    @Override
    public CustomCase createCustomCase(CustomCase customCase) {
        return customCaseRepository.save(customCase);
    }
}
