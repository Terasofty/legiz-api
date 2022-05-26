package com.terasofty.legiz.api.domain.persistence;

import com.terasofty.legiz.api.domain.models.LegalService;
import com.terasofty.legiz.api.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LegalServiceRepository extends JpaRepository<LegalService, Long> {
    List<LegalService> findByClientUser(User user);
}
