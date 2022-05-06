package com.terasofty.legiz.api.domain.persistence;

import com.terasofty.legiz.api.domain.models.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

}
