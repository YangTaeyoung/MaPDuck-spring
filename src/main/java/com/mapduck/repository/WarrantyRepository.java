package com.mapduck.repository;

import com.mapduck.domain.Product;
import com.mapduck.domain.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
    List<Warranty> findAllByPrId_PrIdAndMonth(Long prId, Integer month);
}
