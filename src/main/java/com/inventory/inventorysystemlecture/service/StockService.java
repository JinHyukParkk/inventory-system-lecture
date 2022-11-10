package com.inventory.inventorysystemlecture.service;

import com.inventory.inventorysystemlecture.domain.Stock;
import com.inventory.inventorysystemlecture.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)  // 항상 새로운 트랜잭션 실행 > 부모 트잭잭션에 별도 실행해야함
    public void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow();
//        Stock stock = stockRepository.findByIdWithPessimisticLock(id);
//        Stock stock = stockRepository.findByIdWithOptimisticLock(id);

        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
