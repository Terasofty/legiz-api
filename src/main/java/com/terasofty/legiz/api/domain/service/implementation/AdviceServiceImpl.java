package com.terasofty.legiz.api.domain.service.implementation;

import com.terasofty.legiz.api.domain.models.Advice;
import com.terasofty.legiz.api.domain.persistence.AdviceRepository;
import com.terasofty.legiz.api.domain.service.AdviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdviceServiceImpl implements AdviceService {
    private final AdviceRepository adviceRepository;
    @Override
    public Advice createAdvice(Advice advice) {
        return adviceRepository.save(advice);
    }
}
