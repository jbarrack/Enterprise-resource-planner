package com.inventory.Erp.Repository;

import com.inventory.Erp.model.GoodsReturned;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoodsReturnedRepository extends JpaRepository<GoodsReturned, Integer> {

    Optional<GoodsReturned> findById(int returnId);
}
