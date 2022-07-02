package com.company.paydaytrade.repository;

import com.company.paydaytrade.entity.UserStocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserStocksRepository extends JpaRepository<UserStocks, Integer>, JpaSpecificationExecutor<UserStocks> {
    Optional<UserStocks> findUserStocksByUserId(Integer id);
}
