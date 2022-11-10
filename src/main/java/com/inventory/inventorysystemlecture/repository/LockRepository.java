package com.inventory.inventorysystemlecture.repository;

import com.inventory.inventorysystemlecture.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LockRepository extends JpaRepository<Stock, Long> {

    @Query(value = "select get_lock(:key, 3000)", nativeQuery = true)
    void getLock(String key);

    @Query(value = "select release_lock(:key, 3000)", nativeQuery = true)
    void releaseLock(String key);
}