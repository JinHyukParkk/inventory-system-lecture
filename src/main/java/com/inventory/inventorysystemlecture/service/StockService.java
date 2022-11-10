package com.inventory.inventorysystemlecture.service;

import com.inventory.inventorysystemlecture.domain.Stock;
import com.inventory.inventorysystemlecture.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Transactional
    public void decrease(Long id, Long quantity) {
//        Stock stock = stockRepository.findById(id).orElseThrow();
//        Stock stock = stockRepository.findByIdWithPessimisticLock(id);
        Stock stock = stockRepository.findByIdWithOptimisticLock(id);

        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
