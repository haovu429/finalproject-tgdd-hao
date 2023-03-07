package com.fsoft.finalproject.repository;

import com.fsoft.finalproject.entity.LaptopEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LaptopRepository extends JpaRepository<LaptopEntity, Long> {}
