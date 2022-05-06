package com.terasofty.legiz.api.domain.persistence;

import com.terasofty.legiz.api.domain.models.CustomCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomCaseRepository extends JpaRepository<CustomCase, Long> {

}
