package com.inventory.inventorysystemlecture.service;

import com.inventory.inventorysystemlecture.repository.LockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NamedLockStockFacade {

    private final LockRepository lockRepository;

    private final StockService stockService;


    public void decrease(Long id, Long quantity) {
        try {
            lockRepository.getLock(id.toString());
            stockService.decrease(id, quantity);
        } finally {
            lockRepository.releaseLock(id.toString());
        }
    }
}
